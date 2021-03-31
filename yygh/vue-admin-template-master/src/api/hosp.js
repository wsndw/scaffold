import request from "@/utils/request";

export default {
  getHospList(page, limit, serachObj) {//医院列表
    return request({
      url: `/admin/hosp/hospital/list/${page}/${limit}`,
      method: 'get',
      params: serachObj
    })
  },
  //根据dictcode查询所有子节点（所有省）
  findByDictCode(dictCode) {
    return request({
      url: `/admin/cmn/dict/findByDictCode/${dictCode}`,
      method: 'get'
    })
  },
  //根据省查市
  findChildId(id) {
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
      method: 'get'
    })
  },
  updateStatus(id, status) {
    return request({
      url: `/admin/hosp/hospital/updateStatus/${id}/${status}`,
      method: 'get'
    })
  },
  //查看医院详情
  getHospById(id) {
    return request({
      url: `/admin/hosp/hospital/show/${id}`,
      method: 'get'
    })
  }
}