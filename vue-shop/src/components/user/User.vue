<template>
  <div>
    <el-card id="box">
      <!--============查询================-->
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="4" :offset="1">
          <el-input
              @clear="getUserList"
              v-model="inputUserId"
              placeholder="请输入用户ID"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="4" style="padding: 0">
          <el-input
              @clear="getUserList"
              v-model="inputUserName"
              placeholder="请输入用户名"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-input
              @clear="getUserList"
              v-model="inputPhone"
              placeholder="请输入手机号"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-search" @click="getLikeList">查找</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-upload2" @click="exportExcel">导出</el-button>
        </el-col>
      </el-row>
      <!-- ----------收货地址表格---------------- -->
      <el-row :gutter="20">
        <el-col :span="22" :offset="1">
          <el-table id="table"
                    :data="tableData"
                    border>
            <el-table-column prop="userId" label="用户ID" width="180"></el-table-column>
            <el-table-column prop="userName" label="用户昵称" width="180"></el-table-column>
            <el-table-column prop="userPhone" label="phone"></el-table-column>
            <el-table-column prop="userSex" label="性别"></el-table-column>
            <el-table-column label="等级">
              <template v-slot="scope">
                <el-tag v-if="scope.row.userGrade === 1">超级管理员</el-tag>
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
                :total="total">
            </el-pagination>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

export default {
  name: 'User',
  data () {
    return {
      //数据
      tableData: [],
      //当前页数and页面大小
      currentPage: 1,
      pageSize: 4,
      total: 0,
      //搜索的查询字段
      inputUserId: '',
      inputPhone: '',
      inputUserName: ''
    }
  },
  created () {
    this.getUserList()
  },
  methods: {
    async getUserList () {
      const userid = Number(this.inputUserId)
      const phone = Number(this.inputPhone)
      if (Number.isNaN(userid) || Number.isNaN(phone)) {
        this.$notify.warning({
          title: '提示',
          message: '非法数字'
        })
      } else {
        const { data: res } =
            await this.$http.get
            ('/getUserList', {
              params: {
                'currentPage': this.currentPage,
                'pageSize': this.pageSize,
                'userId': userid,
                'username': this.inputUserName,
                'phone': phone
              }
            })
        this.tableData = res.data
        this.total = res.total
      }
    },
    getLikeList () {
      this.currentPage = 1
      this.pageSize = 4
      this.getUserList()
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
    },
    // 导出表格所用
    exportExcel () {
      // 设置当前日期
      let time = new Date()
      let year = time.getFullYear()
      let month = time.getMonth() + 1
      let day = time.getDate()
      let name = year + '' + month + '' + day
      /* generate workbook object from table */
      let xlsxParam = { raw: true } //转换成excel时，使用原始的格式
      /* 从表生成工作簿对象 */
      let wb = XLSX.utils.table_to_book(
          document.querySelector('#table'),  //elementui 表格的id
          xlsxParam
      )
      /* 获取二进制字符串作为输出 */
      var wbout = XLSX.write(wb, {
        bookType: 'xlsx',
        bookSST: true,
        type: 'array'
      })
      try {
        FileSaver.saveAs(
            //Blob 对象表示一个不可变、原始数据的类文件对象。
            //Blob 表示的不一定是JavaScript原生格式的数据。
            //File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
            //返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
            new Blob([wbout], { type: 'application/octet-stream' }),
            //设置导出文件名称
            name + '会员管理' + '.xlsx'
        )
      } catch (e) {
        if (typeof console !== 'undefined') console.log(e, wbout)
      }
      return wbout
    }
    ,
    //
  }
}
</script>

<style scoped>
#box {
  margin: 0 10px 0 10px;
}
</style>