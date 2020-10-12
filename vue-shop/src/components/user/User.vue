<template>
  <div>
    <el-card id="box">
      <!--============查询================-->
      <el-row :gutter="20">
        <el-col :span="4" :offset="1">
          <el-input
              placeholder="请输入用户ID"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-input
              placeholder="请输入收货人名称"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-search">查找</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-upload2">导出</el-button>
        </el-col>
      </el-row>
      <!-- ----------收货地址表格---------------- -->
      <el-row :gutter="20">
        <el-col :span="22" :offset="1">
          <el-table
              :data="tableData"
              border>
            <el-table-column prop="userId" label="用户ID" width="180"></el-table-column>
            <el-table-column prop="userName" label="用户昵称" width="180"></el-table-column>
            <el-table-column prop="userPhone" label="phone"></el-table-column>
            <el-table-column prop="userSex" label="性别"></el-table-column>
            <el-table-column label="等级">
              <template v-slot="scope">
                <el-tag  v-if="scope.row.userGrade === 1">超级管理员</el-tag>
                <el-tag type="info" v-else-if="scope.row.userGrade === 2">Vip用户</el-tag>
                <el-tag type="warning" v-else>普通用户</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="状态">
              <template v-slot="scope">
                <el-tag type="success" v-if="scope.row.userState === 1">可用</el-tag>
                <el-tag type="danger" v-else>不可用</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <!--==============分页===================-->
      <el-row :gutter="20">
        <el-col :span="24" :offset="1">
          <div class="block">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                @prev-click="prevClick"
                @next-click="nextClick"
                :current-page="currentPage"
                :page-sizes="[4, 6, 8, 10]"
                :page-size="1"
                layout="total, sizes, prev, pager, next, jumper"
                :total="400">
            </el-pagination>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'User',
  data () {
    return {
      //数据
      tableData: [],
      //当前页数and页面大小
      currentPage: 1,
      pageSize: 4
    }
  },
  created () {
    this.getUserList()
  },
  methods: {
    async getUserList () {
      const { data: res } =
          await this.$http.get
          ('/getUserList', {
            params: {
              'currentPage': this.currentPage,
              'pageSize': this.pageSize
            }
          })
      this.tableData = res.data
      console.log(this.tableData)
    },
    /*===每页显示条数发生改变时触发===*/
    handleSizeChange (val) {
      this.pageSize = val
      this.getUserList()
    },
    /*当前页发生改变时触发*/
    handleCurrentChange (val) {
      this.currentPage = val
      this.getUserList()
    },
    /*上一页*/
    prevClick (val) {
      this.currentPage = val
      this.getUserList()
    },
    /*下一页*/
    nextClick (val) {
      this.currentPage = val
      this.getUserList()
    }
  }
}
</script>

<style scoped>
#box {
  margin: 0 10px 0 10px;
}
</style>