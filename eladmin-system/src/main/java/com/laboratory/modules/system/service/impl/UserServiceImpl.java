package com.laboratory.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.laboratory.modules.system.service.dto.*;
import com.laboratory.utils.*;
import lombok.AllArgsConstructor;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.config.FileProperties;
import com.laboratory.exception.BadRequestException;
import com.laboratory.exception.EntityExistException;
import com.laboratory.modules.system.domain.User;
import com.laboratory.modules.system.domain.UsersJobs;
import com.laboratory.modules.system.domain.UsersRoles;
import com.laboratory.modules.system.service.dto.*;
import com.laboratory.modules.system.service.mapper.UsersJobsMapper;
import com.laboratory.modules.system.service.mapper.UsersRolesMapper;
import com.laboratory.modules.security.service.OnlineUserService;
import com.laboratory.modules.security.service.UserCacheClean;
import com.laboratory.modules.system.service.mapper.UserMapper;
import com.laboratory.utils.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.laboratory.modules.system.service.DeptService;
import com.laboratory.modules.system.service.UserService;
import com.laboratory.modules.system.service.UsersJobsService;
import com.laboratory.modules.system.service.UsersRolesService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
*
* @date 2020-09-25
*/
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends CommonServiceImpl<UserMapper, User> implements UserService {

    private final FileProperties properties;
    private final RedisUtils redisUtils;
    private final UserCacheClean userCacheClean;
    private final OnlineUserService onlineUserService;

    private final UserMapper userMapper;
    private final DeptService deptService;
    private final UsersRolesService usersRolesService;
    private final UsersJobsService usersJobsService;
    private final UsersRolesMapper usersRolesMapper;
    private final UsersJobsMapper usersJobsMapper;

    @Override
    //@Cacheable
    public PageInfo<UserDto> queryAll(UserQueryParam query, Pageable pageable) {
        IPage<User> page = PageUtil.toMybatisPage(pageable);
        IPage<User> pageData = userMapper.selectPage(page, QueryHelpMybatisPlus.getPredicate(query));
        List<UserDto> userDtos = ConvertUtil.convertList(pageData.getRecords(), UserDto.class);
        if (pageData.getTotal() > 0) {
            Map<Long, DeptDto> deptMap = deptService.queryAll().parallelStream()
                    .collect(Collectors.toMap(DeptDto::getId, Function.identity(), (x,y) -> x));

            Map<Long, Set<UsersRoles>> usersRolesMap = usersRolesService.lambdaQuery()
                    .in(UsersRoles::getUserId, userDtos.stream().map(UserDto::getId).collect(Collectors.toSet()))
                    .list()
                    .stream()
                    .collect(Collectors.groupingBy(UsersRoles::getUserId, Collectors.toSet()));

            Map<Long, List<UsersJobs>> usersJobsMap = usersJobsService.lambdaQuery()
                    .in(UsersJobs::getUserId, userDtos.stream().map(UserDto::getId).collect(Collectors.toList()))
                    .list()
                    .stream()
                    .collect(Collectors.groupingBy(UsersJobs::getUserId));

            userDtos.forEach(user -> {
                user.setDept(ConvertUtil.convert(deptMap.get(user.getDeptId()), DeptSmallDto.class));
                if (usersRolesMap.containsKey(user.getId())) {
                    user.setRoles(usersRolesMap.get(user.getId()).stream().map(ur -> {
                        RoleSmallDto role = new RoleSmallDto();
                        role.setId(ur.getRoleId());
                        return role;
                    }).collect(Collectors.toSet()));
                }
                if (usersJobsMap.containsKey(user.getId())) {
                    user.setJobs(usersJobsMap.get(user.getId()).stream().map(uj -> {
                        JobSmallDto job = new JobSmallDto();
                        job.setId(uj.getJobId());
                        return job;
                    }).collect(Collectors.toSet()));
                }
            });
        }
        return new PageInfo<>(pageData.getTotal(), userDtos);
    }

    @Override
    //@Cacheable
    public List<UserDto> queryAll(UserQueryParam query){
        return ConvertUtil.convertList(userMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), UserDto.class);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    public UserDto findById(Long id) {
        return ConvertUtil.convert(getById(id), UserDto.class);
    }

    @Override
    public User getByUsername(String userName) {
        User user = lambdaQuery().eq(User::getUsername, userName).one();
        /*if (user == null) {
            throw new EntityNotFoundException(User.class, "username", userName);
        }*/
        return user;
    }

    @Override
    public UserDto findByName(String userName) {
        UserDto dto = ConvertUtil.convert(getByUsername(userName), UserDto.class);
        if (dto == null) {
            return dto;
        }
        dto.setDept(new DeptSmallDto(dto.getDeptId(), deptService.findById(dto.getDeptId()).getName()));
        //dto.setRoles();
        //dto.setJobs();
        return dto;
    }

    private User getByEmail(String email) {
        return lambdaQuery().eq(User::getEmail, email).one();
    }
    private User getByPhone(String phone) {
        return lambdaQuery().eq(User::getPhone, phone).one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserDto resources) {
        User user = getByUsername(resources.getUsername());
        if (user != null) {
            throw new EntityExistException(User.class, "username", user.getUsername());
        }
        user = getByEmail(resources.getEmail());
        if (user != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        user = getByPhone(resources.getPhone());
        if (user != null) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }

        user = ConvertUtil.convert(resources, User.class);
        if (resources.getDept() != null) {
            user.setDeptId(resources.getDept().getId());
        }
        int ret = userMapper.insert(user);
        final Long userId = user.getId();
        if (CollectionUtils.isNotEmpty(resources.getRoles())) {
            resources.getRoles().forEach(role -> {
                UsersRoles ur = new UsersRoles();
                ur.setUserId(userId);
                ur.setRoleId(role.getId());
                usersRolesMapper.insert(ur);
            });
        }
        if (CollectionUtils.isNotEmpty(resources.getJobs())) {
            resources.getJobs().forEach(job -> {
                UsersJobs uj = new UsersJobs();
                uj.setUserId(userId);
                uj.setJobId(job.getId());
                usersJobsMapper.insert(uj);
            });
        }
        return ret > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(UserDto resources) throws Exception {
        User user = getById(resources.getId());
        User user1 = getByUsername(user.getUsername());
        User user2 = getByEmail(user.getEmail());
        User user3 = getByPhone(user.getPhone());
        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "username", user.getUsername());
        }
        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", user.getEmail());
        }
        if (user3 != null && !user.getId().equals(user3.getId())) {
            throw new EntityExistException(User.class, "phone", user.getPhone());
        }

        //usersRolesService.getUsersRoleList(resources.getId());
        // 如果用户的角色改变
        //if (!resources.getRoles().equals(xxxx.getRoles())) {
            redisUtils.del(CacheKey.DATA_USER + resources.getId());
            redisUtils.del(CacheKey.MENU_USER + resources.getId());
            redisUtils.del(CacheKey.ROLE_AUTH + resources.getId());
        //}

        // 如果用户名称修改
        if(!resources.getUsername().equals(user.getUsername())){
            throw new BadRequestException("不能修改用户名");
        }
        // 如果用户被禁用，则清除用户登录信息
        if(!resources.getEnabled()){
            onlineUserService.kickOutForUsername(resources.getUsername());
        }
        if (CollectionUtils.isNotEmpty(resources.getRoles())) {
            usersRolesService.removeByUserId(resources.getId());
            resources.getRoles().stream().forEach(role -> {
                UsersRoles ur = new UsersRoles();
                ur.setUserId(resources.getId());
                ur.setRoleId(role.getId());
                usersRolesMapper.insert(ur);
            });
        }

        if (CollectionUtils.isNotEmpty(resources.getJobs())) {
            usersJobsService.removeByUserId(resources.getId());
            resources.getJobs().stream().forEach(job -> {
                UsersJobs uj = new UsersJobs();
                uj.setUserId(resources.getId());
                uj.setJobId(job.getId());
                usersJobsMapper.insert(uj);
            });
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        if (resources.getDept() != null) {
            user.setDeptId(resources.getDept().getId());
        } else {
            user.setDeptId(null);
        }
        user.setPhone(resources.getPhone());
        user.setGender(resources.getGender());

        delCaches(user.getId(), user.getUsername());
        return userMapper.updateById(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String encryptPassword) {
        User user = new User();
        user.setPassword(encryptPassword);
        user.setPwdResetTime(new Date());
        lambdaUpdate().eq(User::getUsername, username).update(user);
        flushCache(username);
    }

    @Override
    public void updateEmail(String username, String email) {
        User user = getByUsername(username);
        User user2 = getByEmail(email);
        if (ObjectUtil.notEqual(user.getId(), user2.getId())) {
            throw new EntityExistException(User.class, "email", email);
        }
        User userUpdate = new User();
        userUpdate.setEmail(email);
        lambdaUpdate().eq(User::getUsername, username).update(userUpdate);
    }

    @Override
    public void updateCenter(User resources) {
        /*
        User user2 = getByPhone(resources.getPhone());
        if (ObjectUtil.isNotNull(user2)) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        */
        User userUpdate = new User();
        userUpdate.setGender(resources.getGender());
        if(ObjectUtil.isNotNull(resources.getEmail())){
            userUpdate.setEmail(resources.getEmail());
        }
        if(ObjectUtil.isNotNull(resources.getPhone())){
            userUpdate.setPhone(resources.getPhone());
        }
        lambdaUpdate().eq(User::getId, resources.getId()).update(userUpdate);
        redisUtils.del("user::username:" + resources.getUsername());
        flushCache(resources.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Set<Long> ids){
        for (Long id: ids) {
            User user = getById(id);
            delCaches(user.getId(), user.getUsername());
            usersRolesService.removeByUserId(id);
            usersJobsService.removeByUserId(id);
        }
        return userMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    @Transactional
    public boolean removeById(Long id){
        Set<Long> ids = new HashSet<>(1);
        ids.add(id);
        return this.removeByIds(ids);
    }

    @Override
    public void download(List<UserDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (UserDto user : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("部门名称", user.getDeptId());
              map.put("用户名", user.getUsername());
              map.put("性别", user.getGender());
              map.put("手机号码", user.getPhone());
              map.put("邮箱", user.getEmail());
              map.put("密码", user.getPassword());
              map.put("是否为admin账号", user.getIsAdmin());
              map.put("状态：1启用、0禁用", user.getEnabled());
              map.put("创建者", user.getCreateBy());
              map.put("更新着", user.getUpdateBy());
              map.put("修改密码的时间", user.getPwdResetTime());
              map.put("创建日期", user.getCreateTime());
              map.put("更新时间", user.getUpdateTime());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }

    /**
     * 清理缓存
     *
     * @param id /
     */
    public void delCaches(Long id, String username) {
        redisUtils.del(CacheKey.USER_ID + id);
        flushCache(username);
    }

    /**
     * 清理 登陆时 用户缓存信息
     *
     * @param username /
     */
    private void flushCache(String username) {
        //userCacheClean.cleanUserCache(username);
        userCacheClean.cleanAll();
    }
}
