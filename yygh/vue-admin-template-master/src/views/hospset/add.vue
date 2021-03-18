<template>
  <div class="app-container">
    添加医院设置
    <el-form label-width="120px">
      <el-form-item label="医院名称">
        <el-input v-model="hospitalSet.hosname" />
      </el-form-item>
      <el-form-item label="医院编号">         
        <el-input v-model="hospitalSet.hoscode" />    
      </el-form-item>
      <el-form-item label="api基础路径">      
        <el-input v-model="hospitalSet.apiUrl" />   
      </el-form-item>
      <el-form-item label="联系人姓名">
        <el-input v-model="hospitalSet.contactsName" />
      </el-form-item>
      <el-form-item label="联系人手机">
        <el-input v-model="hospitalSet.contactsPhone" />    
      </el-form-item>
      <el-form-item>
         <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>   
  </div>
</template>
<script>
import hospset from '@/api/hospset'
export default {
  data() {
    return {
      hospitalSet: {},
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      this.getHospSet(id)
    }else{
      this.hospset ={}
    }
  },
  methods: {
    save(hospitalSet) {
      hospset.saveHospSet(this.hospitalSet).then((respones) => {
        this.$message({
          type: 'success',
          message: '添加成功!',
        })
        this.$router.push({ path: '/hospSet/list' })
      })
    },
    update(hospitalSet) {
      hospset.updateHospSet(this.hospitalSet).then((respones) => {
        this.$message({
          type: 'success',
          message: '修改成功!',
        })
        this.$router.push({ path: '/hospSet/list' })
      })
    },
    saveOrUpdate(hospitalSet) {
      if (!this.hospitalSet.id) {
        this.save(hospitalSet)
      } else {
        this.update(hospitalSet)
      }
    },
    getHospSet(id) {
      hospset.getHospSet(id).then((respones) => {
        this.hospitalSet = respones.data
      })
    },
  },
}
</script>