<template>

  <div>
    <el-card id="box">
    <div>
      <el-row :gutter="20"  >
          <el-col :span="3"> <el-input v-model="goodsId" placeholder="请输入商品ID" clearable></el-input></el-col>
          <el-col :span="3">   <el-input v-model="classificId" placeholder="请输入商品编号"></el-input></el-col>
          <el-col :span="3">   <el-input v-model="goodsName" placeholder="请输入商品名称"></el-input></el-col>
        <el-col :span="9">
          <el-button type="primary" icon="el-icon-search" @click="getTableData">查询</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="insertNewGoods">添加</el-button>
        <el-button type="primary" icon="el-icon-download" @click="OutPutGoods">导出</el-button></el-col>
      </el-row>



    </div>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="商品编号">
              <span>{{ props.row.goodsId }}</span>
            </el-form-item>
            <el-form-item label="宣传画廊">
              <span>{{ props.row.goodsPropagate}}</span>
            </el-form-item>
            <el-form-item label="商品介绍">
              <span>{{ props.row.goodsPro }}</span>
            </el-form-item>
            <el-form-item label="商品单位">
              <span>{{ props.row.goodsUnit }}</span>
            </el-form-item>
            <el-form-item label="关键字">
              <span>{{ props.row.keyword }}</span>
            </el-form-item>
            <el-form-item label="类目ID">
              <span>{{ props.row.mallCategory.id}}</span>
            </el-form-item>
            <el-form-item label="品牌商ID">
              <span>{{ props.row.mallBrand.bId }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          label="商品 ID"
          prop="goodsId">
      </el-table-column>
      <el-table-column
          label="商品名称"
          prop="goodsName">
      </el-table-column>

      <el-table-column
          label="图片"
          prop="goodsPic"
        >
        <template  slot-scope="props">

          <el-image v-if="props.row.goodsPic!=null" :src="baseimgSrc+props.row.goodsPic" ></el-image>
        </template>

      </el-table-column>
      <el-table-column
          label="分享图"
          prop="sharePic">
      </el-table-column>
      <el-table-column
          label="详情"
          prop="goodsDetail">

      </el-table-column>
      <el-table-column
          label="市场售价"
          prop="marketPrice">
      </el-table-column>
      <el-table-column
          label="当前价格"
          prop="goodsPrice">
      </el-table-column>

      <el-table-column
          label="是否新品"
          prop="isNew">
        <template v-slot="scope">
        <el-tag v-if="scope.row.isNew==1">新品</el-tag>
        <el-tag v-if="scope.row.isNew==0">非新品</el-tag>
        </template>
      </el-table-column>

      <el-table-column
          label="是否热品"
          prop="isHot">
        <template v-slot="scope">
          <el-tag v-if="scope.row.isHot==1">热卖</el-tag>
          <el-tag v-if="scope.row.isHot==0">非热卖</el-tag>
        </template>
      </el-table-column>
      <el-table-column
          label="是否在售"
          prop="isSells">
        <template v-slot="scope">
          <el-tag v-if="scope.row.isSells==1">在售</el-tag>
          <el-tag v-if="scope.row.isSells==0">非在售</el-tag>
        </template>
      </el-table-column>
        <el-table-column label="操作">
        <template v-slot="scope">
          <button type="button" class="el-button el-button--danger el-button--mini" @click="deleteInfo(scope.row.goodsId)">
            <span>删除</span>
          </button>
        </template>
        </el-table-column>

    </el-table>
  </div>
    <!--=============分页================-->

    <el-row :gutter="20">
      <el-col :span="24" :offset="1">
        <div class="block">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              @prev-click="prevClick"
              @next-click="nextClick"
              :current-page=currentPage
              :page-sizes="[1,4, 6, 8, 10]"
              :page-size=pageSize
              layout="total, sizes, prev, pager, next, jumper"
              :total="totalData">
          </el-pagination>


        </div>
          </el-col>
        </el-row>

    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Goods',
  data () {
    return {

      tableData: [],
      goodsId: '',
      classificId:'',
      goodsName:'',
      currentPage:1,
      pageSize:4,
      totalData:0,
      baseimgSrc:''
    }
  },
  created () {

    this.getTableData(this.currentPage,this.pageSize);
    this.baseimgSrc = this.$http.defaults.baseURL;
  },
  methods:{
    /*======全查、模糊差=======*/
   async getTableData(currentPage,pageSize){

    const goodsId= Number(this.goodsId);
    const clssificId=Number(this.classificId);
     if(Number.isNaN(goodsId) || Number.isNaN(clssificId)){
        this.$notify.warning({
          title:'提示',
          message:'请输入数字'
        })
     }else{
       const {data}=await this.$http.get("goods/list",{params:{'goodsId':this.goodsId,
                                                                          'classificId':this.classificId,
                                                                          'goodsName':this.goodsName,'currentPage':currentPage,'pageSize':pageSize}});
       console.log(data.data)
       this.tableData = data.data;
       this.totalData = data.totalData;
       console.log(this.tableData);
       this.classificId='';
       this.goodsId='';
       this.goodsName='';

     }


   },
    /*============添加商品===========*/
    insertNewGoods(){
      this.$router.push('/goods/create')
    },
    /*=========POI导出商品到本地==========*/
    OutPutGoods(){

    },
    /*===每页显示条数发生改变时触发===*/
    handleSizeChange(val){
      this.pageSize=val;
      this.getTableData(this.currentPage,val);
    },
    /*当前页发生改变时触发*/
    handleCurrentChange(val){
      this.getTableData(val,this.pageSize);
    },
    /*上一页*/
    prevClick(val){
      this.getTableData(val,this.pageSize);
    },
    /*下一页*/
    nextClick(val){
      this.getTableData(val,this.pageSize);
    },
   async deleteInfo(goodsId){
     const {data:res}=await this.$http.delete("goods/delete",{params:{'goodsId' : goodsId }})
     if(res.meta.status === 200){
       this.$message.success('删除成功')
       this.getTableData();
     }else{
       this.$message.error('删除失败')
     }
   }
  }
}
</script>

<style scoped>
#box {
  margin: 0 10px 0 10px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}
#box {
  margin: 0 10px 0 10px;
}
</style>