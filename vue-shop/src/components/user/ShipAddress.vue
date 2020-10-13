<template>
  <div>
    <el-card id="box">
      <!--============查询================-->
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="4" :offset="1">
          <el-input
              placeholder="请输入收货人ID"
              v-model="userID"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-input
              placeholder="请输入收货人名称"
              v-model="userName"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" v-on:click="selectOne(userID,userName,currentPage,pageSize)" icon="el-icon-search">查找</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" v-on:click="exportExcel()" icon="el-icon-upload2">导出</el-button>
        </el-col>
      </el-row>
      <!-- ----------收货地址表格---------------- -->
      <el-row :gutter="20">
        <el-col :span="22" :offset="1">
          <el-table
              id="table"
              :data="tableData"
              border>
            <el-table-column prop="id" label="地址ID" width="180"></el-table-column>
            <el-table-column prop="uid" label="收货人ID" width="180">  </el-table-column>
            <el-table-column prop="user.userName" label="收货人姓名"></el-table-column>
            <el-table-column prop="user.userPhone" label="收货人电话"></el-table-column>
            <el-table-column prop="area" label="地址"></el-table-column>
            <el-table-column prop="address" label="详细地址"></el-table-column>
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
                :current-page=currentPage
                :page-sizes="[4, 6, 8, 10]"
                :page-size=pageSize
                layout="total, sizes, prev, pager, next, jumper"
                :total="listMunber">
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
  name: "ShipAddress",
  data(){
    return{
      userID:'',
      userName:'',
      tableData:[],
      currentPage:1,
      pageSize:4,
      listMunber:0
    }
  },
  created() {
    this.getTable(this.currentPage,this.pageSize)
    this.gitNumber()
  },
  methods:{
    /*根据给出的关键词去精确查找*/
    async selectOne(userID,userName,currentPage,pageSize){
      const { data } = await this.$http.get('selectOne',{params:{
          userID,userName,currentPage,pageSize
        }})
        this.tableData=data.data
        this.userID=''
        this.userName=''
        this.listMunber=data.same

    },
  /*===每页显示条数发生改变时触发===*/
    handleSizeChange(val){
      this.pageSize=val;
      this.getTable(this.currentPage,val);
    },
    /*当前页发生改变时触发*/
    handleCurrentChange(val){
      this.getTable(val,this.pageSize);
    },
    /*上一页*/
    prevClick(val){
      this.getTable(val,this.pageSize);
    },
    /*下一页*/
    nextClick(val){
      this.getTable(val,this.pageSize);
    },
    /* 获取列表栏数据 */
    async getTable (currentPage,pageSize) {
      const { data } = await this.$http.get('limitAddress',{params:{
        currentPage,pageSize
        }})
      this.tableData = data.data
      console.log(data.data)
    },
    /*获取总条数*/
    async gitNumber(){
      const { data } = await this.$http.get('getNumber')
      this.listMunber = data;
    },
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
            name + '订单地址' + '.xlsx'
        )
      } catch (e) {
        if (typeof console !== 'undefined') console.log(e, wbout)
      }
      return wbout
    }
  }
}
</script>

<style scoped>
#box{
  margin : 0 10px 0 10px;
}
</style>