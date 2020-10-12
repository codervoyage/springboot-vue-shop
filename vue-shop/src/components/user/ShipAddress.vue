<template>
  <div>
    <el-card id="box">
      <!--============查询================-->
      <el-row :gutter="20">
        <el-col :span="4" :offset="1">
          <el-input
              placeholder="请输入用户ID"
              v-model="input"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-input
              placeholder="请输入收货人名称"
              v-model="input"
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
  name: "ShipAddress",
  data(){
    return{
      input:'',
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
    /* 获取菜单栏数据 */
    async getTable (currentPage,pageSize) {
      const { data } = await this.$http.get('limitAddress',{params:{
        currentPage,pageSize
        }})
      this.tableData = data.data
    },
    /*获取总条数*/
    async gitNumber(){
      const { data } = await this.$http.get('getNumber')
      this.listMunber = data;
    }
  }
}
</script>

<style scoped>
#box{
  margin : 0 10px 0 10px;
}
</style>