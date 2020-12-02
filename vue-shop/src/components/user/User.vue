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
                <el-tag v-if="scope.row.userGrade === 0">超级管理员</el-tag>
                <el-tag type="info" v-else-if="scope.row.userGrade === 1">Vip用户</el-tag>
                <el-tag type="warning" v-else-if="scope.row.userGrade === 2">普通用户</el-tag>
                <el-tag type="warning" v-else-if="scope.row.userGrade === 3">新进用户</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="状态">
              <template v-slot="scope">
                <el-tag type="success" v-if="scope.row.userState === 1">可用</el-tag>
                <el-tag type="danger" v-else>不可用</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button type="primary" size="mini" @click=" selectAndUpdate(scope.row) ">详情</el-button>
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
      <!--==================dialog弹出框======================-->
      <el-dialog
          title="用户编辑"
          :visible.sync="dialogVisible"
          width="40%"
      >

        <el-form :model="form" label-width="80px" style="width: 400px">

          <el-form-item label="用户名">
            <el-input v-model="form.userAccount" :disabled="true"></el-input>
          </el-form-item>


          <el-form-item label="用户昵称">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>

          <el-form-item label="用户手机">
            <el-input v-model="form.userPhone"></el-input>
          </el-form-item>

          <el-form-item label="用户性别">
            <el-select v-model="form.userSex" placeholder="用户性别">
              <el-option
                  v-for="item in sex"
                  :key="item.value"
                  :label="item.label"
                  :value="item.label">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="用户等级">
            <el-select v-model="form.userGrade" placeholder="用户等级">
              <el-option
                  v-for="item in grade"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="状态">
            <el-select v-model="form.userState" placeholder="状态">
              <el-option
                  v-for="item in state"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitDialog(form)">确 定</el-button>
        </span>
      </el-dialog>
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
      inputUserName: '',
      // 控制dialog开关
      dialogVisible: false,
      //dialog的form表单数据
      form: {
        userName: '',
        userAccount: '',
        userPhone: '',
        userSex: '',
        userGrade: '',
        userState: ''
      },
      //dialog->from->几个下拉菜单的数据
      sex: [{
        value: 0,
        label: '男'
      }, {
        value: 1,
        label: '女'
      },],
      grade: [/*{
        value: 0,
        label: '超级管理员'
      },*/ {
        value: 1,
        label: 'vip用户'
      }, {
        value: 2,
        label: '普通用户'
      }, {
        value: 3,
        label: '新进用户'
      }],
      state: [{
        value: 0,
        label: '不可用'
      }, {
        value: 1,
        label: '可用'
      }]
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
    },
    /*查看详情*/
    selectAndUpdate (val) {
      this.dialogVisible = true
      this.form.userName = val.userName
      this.form.userAccount = val.userAccount
      this.form.userPhone = val.userPhone
      this.form.userSex = val.userSex
      this.form.userGrade = val.userGrade
      this.form.userState = val.userState
    },
    /*提交dialog内form表单*/
    async submitDialog (val) {
      this.dialogVisible = false
      console.log(val)
      if (val == null) {
        this.$notify.warning({
          title: '提示',
          message: '修改信息不正确'
        })
      } else {
        const { data } = await this.$http.get('/updateUserOne', {
          params: {
            userName: val.userName,
            userAccount: val.userAccount,
            userPhone: val.userPhone,
            userSex: val.userSex,
            userGrade: val.userGrade,
            userState: val.userState
          }
        })
        this.$notify.success({
          title: '提示',
          message: '修改成功'
        })
        this.getUserList()
      }
    }
  }
}
</script>

<style scoped>
#box {
  margin: 0 10px 0 10px;
}
</style>