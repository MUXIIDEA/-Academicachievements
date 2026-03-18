/**
 * 班牌展示API接口
 * 
 * @author School System
 * @version 1.0.0
 */
import request from '@/utils/request'

/**
 * 获取班牌展示数据
 * @param {Number} roomId - 教室ID
 * @returns {Promise}
 */
export function getClassBoardData(roomId) {
  return request({
    url: '/classboard/data',
    method: 'get',
    params: { roomId }
  })
}

/**
 * 根据设备ID获取班牌数据
 * @param {String} deviceId - 设备ID
 * @returns {Promise}
 */
export function getClassBoardDataByDevice(deviceId) {
  return request({
    url: `/classboard/data/device/${deviceId}`,
    method: 'get'
  })
}

/**
 * 获取今日课表
 * @param {Number} classId - 班级ID
 * @returns {Promise}
 */
export function getTodaySchedule(classId) {
  return request({
    url: '/classboard/schedule/today',
    method: 'get',
    params: { classId }
  })
}

/**
 * 获取当前课程
 * @param {Number} classId - 班级ID
 * @returns {Promise}
 */
export function getCurrentLesson(classId) {
  return request({
    url: '/classboard/schedule/current',
    method: 'get',
    params: { classId }
  })
}

/**
 * 获取下一节课
 * @param {Number} classId - 班级ID
 * @returns {Promise}
 */
export function getNextLesson(classId) {
  return request({
    url: '/classboard/schedule/next',
    method: 'get',
    params: { classId }
  })
}

/**
 * 获取通知公告
 * @param {Number} classId - 班级ID
 * @returns {Promise}
 */
export function getNotices(classId) {
  return request({
    url: '/classboard/notices',
    method: 'get',
    params: { classId }
  })
}
