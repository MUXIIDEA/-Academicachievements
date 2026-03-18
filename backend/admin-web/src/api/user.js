/**
 * 用户管理API接口
 * 
 * 功能说明：
 * - 用户CRUD操作
 * - 密码管理
 * - 状态管理
 * 
 * @author School System
 * @version 1.0.0
 */
import request from '@/utils/request'

/**
 * 获取用户列表(分页)
 * @param {Object} params - 查询参数
 * @returns {Promise}
 */
export function getUserList(params) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params
  })
}

/**
 * 获取用户详情
 * @param {Number} userId - 用户ID
 * @returns {Promise}
 */
export function getUserDetail(userId) {
  return request({
    url: `/system/user/${userId}`,
    method: 'get'
  })
}

/**
 * 新增用户
 * @param {Object} data - 用户数据
 * @returns {Promise}
 */
export function createUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data
  })
}

/**
 * 修改用户
 * @param {Number} userId - 用户ID
 * @param {Object} data - 用户数据
 * @returns {Promise}
 */
export function updateUser(userId, data) {
  return request({
    url: `/system/user/${userId}`,
    method: 'put',
    data
  })
}

/**
 * 删除用户
 * @param {Number} userId - 用户ID
 * @returns {Promise}
 */
export function deleteUser(userId) {
  return request({
    url: `/system/user/${userId}`,
    method: 'delete'
  })
}

/**
 * 修改用户密码
 * @param {Number} userId - 用户ID
 * @param {Object} data - 密码数据
 * @returns {Promise}
 */
export function updatePassword(userId, data) {
  return request({
    url: `/system/user/${userId}/password`,
    method: 'put',
    data
  })
}

/**
 * 重置用户密码
 * @param {Number} userId - 用户ID
 * @returns {Promise}
 */
export function resetPassword(userId) {
  return request({
    url: `/system/user/${userId}/resetPassword`,
    method: 'put'
  })
}

/**
 * 修改用户状态
 * @param {Number} userId - 用户ID
 * @param {Number} status - 状态
 * @returns {Promise}
 */
export function updateUserStatus(userId, status) {
  return request({
    url: `/system/user/${userId}/status`,
    method: 'put',
    params: { status }
  })
}
