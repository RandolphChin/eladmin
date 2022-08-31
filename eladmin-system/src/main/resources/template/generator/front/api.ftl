import request from '@/utils/request'

export function list(params) {
  return request({
    url: 'api/${changeClassName}',
    method: 'get',
    params: params
  })
}

export function get(id) {
  return request({
    url: 'api/${changeClassName}/' + id,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/${changeClassName}',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/${changeClassName}/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/${changeClassName}',
    method: 'put',
    data
  })
}

export default { list, get, add, edit, del }
