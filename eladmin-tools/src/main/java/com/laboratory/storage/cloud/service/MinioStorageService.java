/*
package com.laboratory.storage.cloud.service;

import cn.hutool.core.util.ObjectUtil;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.laboratory.domain.LocalStorage;
import com.laboratory.service.LocalStorageService;
import com.laboratory.storage.cloud.config.MinioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;

*/
/**
 * Minio 文件服务
 **//*

@Component
@Slf4j
public class MinioStorageService implements InitializingBean {

    @Autowired
    private MinioConfig minIoProperties;

    private MinioClient client;

    private HashSet<String> localOverSet = new HashSet<String>();

    private final String BUCKET_POLICY="read-write";
    */
/**
     * 桶占位符
     *//*

    private static final String BUCKET_PARAM = "${bucket}";
    */
/**
     * bucket权限-只读
     *//*

    private static final String READ_ONLY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "/*\"]}]}";
    */
/**
     * bucket权限-只读
     *//*

    private static final String WRITE_ONLY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "/*\"]}]}";
    */
/**
     * bucket权限-读写
     *//*

    private static final String READ_WRITE = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "/*\"]}]}";

    @Autowired
    private LocalStorageService localStorageService;
    */
/**
     * 初始化minio配置
     *
     * @param :
     * @return: void
     * @date : 2020/8/16 20:56
     *//*

    @PostConstruct
    public void init() {
        try {
            client = MinioClient.builder().endpoint(minIoProperties.getEndpoint()).credentials(minIoProperties.getAccessKey(), minIoProperties.getSecretKey()).build();
            makeBucket(minIoProperties.getDefaultBucketName());
            // setBucketPolicy(minIoProperties.getDefaultBucketName(), BUCKET_POLICY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
/**
     * 文件url前半段
     *    示例  http://127.0.0.1:9000/test/Snipaste_2020-12-10_12-53-31.png
     * @param bucket 桶
     * @return 前半段
     *//*

    private String getObjectPrefixUrl(String bucket) {
        return String.format("%s/%s/", minIoProperties.getEndpoint(), bucket);
    }

    */
/**
     * 创建桶
     *
     * @param bucket 桶
     *//*

    public void makeBucket(String bucket) throws Exception {
        // 判断桶是否存在
        boolean isExist = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!isExist) {
            // 新建桶
            client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            // 设置策略
            setBucketPolicy(bucket,BUCKET_POLICY);
        }
    }

    */
/**
     * 更新桶权限策略
     *
     * @param bucket 桶
     * @param policy 权限
     *//*

    public void setBucketPolicy(String bucket, String policy) throws Exception {
        switch (policy) {
            case "read-only":
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(READ_ONLY.replace(BUCKET_PARAM, bucket)).build());
                break;
            case "write-only":
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(WRITE_ONLY.replace(BUCKET_PARAM, bucket)).build());
                break;
            case "read-write":
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(READ_WRITE.replace(BUCKET_PARAM, bucket)).build());
                break;
            case "none":
            default:
                break;
        }
    }

    */
/**
     * 上传本地文件
     *
     * @param bucket    桶
     * @param objectKey 文件key
     * @param filePath  文件路径
     * @return 文件url
     *//*

    public String uploadFile(String bucket, String objectKey, String filePath) throws Exception {
        client.uploadObject(UploadObjectArgs.builder().bucket(bucket).object(objectKey).filename(filePath).contentType("image/png").build());
        return getObjectPrefixUrl(bucket) + objectKey;
    }

    */
/**
     * 流式上传文件
     *
     * @param bucket      桶
     * @param objectKey   文件key  如果有分隔符号则会创建文件夹如 "demo/123.png";
     * @param file 文件输入流
     *//*

    public LocalStorage upload(String bucket, String objectKey, MultipartFile file) throws Exception {
        if(ObjectUtil.isNotNull(bucket)){
            if(!localOverSet.contains(bucket)){
                makeBucket(bucket);
                localOverSet.add(bucket);
            }
        }
        LocalStorage localStorage = localStorageService.insert(objectKey,file,bucket);
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        client.putObject(PutObjectArgs.builder().bucket(bucket).object(localStorage.getRealName()).stream(inputStream, inputStream.available(), -1).contentType(contentType).build());
        inputStream.close();
        return localStorage;
    }

    */
/**
     * 下载文件
     *
     * @param bucket    桶
     * @param objectKey 文件key
     * @return 文件流
     *//*

    public InputStream download(String bucket, String objectKey) throws Exception {
        return client.getObject(GetObjectArgs.builder().bucket(bucket).object(objectKey).build());
    }

    */
/**
     * 文件复制
     *
     * @param sourceBucket    源桶
     * @param sourceObjectKey 源文件key
     * @param bucket          桶
     * @param objectKey       文件key
     * @return 新文件url
     *//*

    public String copyFile(String sourceBucket, String sourceObjectKey, String bucket, String objectKey) throws Exception {
        CopySource source = CopySource.builder().bucket(sourceBucket).object(sourceObjectKey).build();
        client.copyObject(CopyObjectArgs.builder().bucket(bucket).object(objectKey).source(source).build());
        return getObjectPrefixUrl(bucket) + objectKey;
    }

    */
/**
     * 删除文件
     *
     * @param bucket    桶
     * @param objectKey 文件key
     *//*

    public void deleteFile(String bucket, String objectKey) throws Exception {
        client.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectKey).build());
    }

    */
/**
     * 获取文件签名url
     *
     * @param bucket    桶
     * @param objectKey 文件key
     * @param expires   签名有效时间  单位秒
     * @return 文件签名地址
     *//*

    public String getSignedUrl(String bucket, String objectKey, int expires) throws Exception {
        //return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucket).object(objectKey).expiry(expires).build());
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucket).object(objectKey).build());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Bucket> list = client.listBuckets();
        if(ObjectUtil.isNull(list)){
            return;
        }
        list.stream().forEach(k->{
            System.out.println("---------------------------");
            System.out.println("---------------------------");
            System.out.println(k.name());
            localOverSet.add(k.name());
            System.out.println("---------------------------");
            System.out.println("---------------------------");
        });
    }
}
*/
