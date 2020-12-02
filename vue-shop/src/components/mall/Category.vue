<template>
<div>
  <el-card id="box">
    <!--============添加================-->
    <el-row :gutter="20" style="margin-bottom: 10px">
      <el-col :span="2" :offset="1">
        <el-button type="primary" v-on:click="dialog=true" icon="el-icon-edit">添加</el-button>
      </el-col>
    </el-row>
    <!-- ----------收货地址表格---------------- -->
    <el-row :gutter="20">
      <el-col :span="22" :offset="1">
        <el-table
            :data="tableData"
            style="width: 100%;margin-bottom: 20px;"
            row-key="id"
            border
            :tree-props="{children: 'children'}">
          <el-table-column prop="id" label="类目ID" width="100"></el-table-column>
          <el-table-column prop="typeName" label="类目名称" width="140"></el-table-column>

          <el-table-column  prop="typeLcon" label="类目图标">
            <template v-slot:="scope">
              <el-image style="width:40px;height:40px;border:none;" :src="imgSrc+scope.row.typeLcon" class="head_pic"></el-image>

            </template>
          </el-table-column>
          <el-table-column  prop="typePicture" label="类目图片">
            <template v-slot:="scope">
              <el-image style="width:40px;height:40px;border:none;" :src="imgSrc+scope.row.typePicture" class="head_pic"></el-image>
            </template>
          </el-table-column>
          <el-table-column prop="typeKeyword" label="关键字" width="140"></el-table-column>
          <el-table-column prop="typeContent" label="类目简介" width="150"></el-table-column>
          <el-table-column prop="typeGrade" label="类目级别" width="140">
            <template v-slot="scope">
              <el-tag v-if="scope.row.typeGrade === 0">一级类目</el-tag>
              <el-tag v-if="scope.row.typeGrade === 1" type="success">二级类目</el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="160">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="update(scope.row)">编辑</el-button>
              <el-button type="danger"  size="mini" @click="delcate(scope.row.typeGrade,scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--============添加模态框==================-->
    <el-dialog title="添加类目" :visible.sync="dialog"width="40%" center @close="closelog1">
      <el-form :model="dialogFrom" label-width="80px" style="width: 400px">
        <el-form-item label="类目名称">
          <el-input v-model="dialogFrom.typeName" autocomplete="off"  size="mini"></el-input>
        </el-form-item>
        <el-form-item label="关键字">
          <el-input v-model="dialogFrom.typeKeyword" autocomplete="off"  size="mini"></el-input>
        </el-form-item>
        <el-form-item label="级别">
          <el-select v-model="dialogFrom.typeGrade" size="mini" placeholder="请选择级别" v-on:change="selectflm()">
            <el-option label="一级类目" value=0></el-option>
            <el-option label="二级类目" value=1></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-show="dgflm" label="父类目">
          <el-select v-model="dialogFrom.fid" size="mini" placeholder="请选择父类目">
            <template v-for="flm in dategoryflm">
              <el-option :label="flm.typeName" :value=flm.id></el-option>
            </template>
          </el-select>
        </el-form-item>
        <el-form-item label="类目图标">
          <el-upload
              :limit=1
              action="http://localhost:8081//mall/category/lcon"
              :show-file-list= "true"
              list-type="picture-card"
              :on-success="addlcon"
              :before-upload="beforeAvatarUpload"
              :file-list="dialogFrom.fileList1">
            <i class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="类目图片">
          <el-upload
              :limit=1
              action="http://localhost:8081//mall/category/lcon"
              :show-file-list= "true"
              list-type="picture-card"
              :on-success="addpicture"
              :before-upload="beforeAvatarUpload"
              :file-list="dialogFrom.fileList2">
            <i class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="类目简介">
          <el-input v-model="dialogFrom.typeContent" autocomplete="off"  size="mini"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">取 消</el-button>
        <el-button type="primary" @click="submitDialog()">确 定</el-button>
      </div>
    </el-dialog>
    <!--============修改模态框==================-->
    <el-dialog title="修改类目" :visible.sync="dialog2"width="40%" center :before-close="beforeclose">
      <el-form v-model="dialogFrom" label-width="80px" style="width: 400px">
        <el-form-item label="类目名称">
          <el-input v-model="dialogFrom.typeName" autocomplete="off"  size="mini"></el-input>
        </el-form-item>
        <el-form-item label="关键字">
          <el-input v-model="dialogFrom.typeKeyword" autocomplete="off"  size="mini"></el-input>
        </el-form-item>
        <el-form-item label="类目图标">
          <el-upload
              :limit=1
              action="http://localhost:8081//mall/category/lcon"
              :show-file-list= "true"
              list-type="picture-card"
              :on-success="addlcon"
              :before-upload="beforeAvatarUpload"
              :file-list="dialogFrom.fileList1">
            <el-image :src="imgSrc+dialogFrom.typeLcon" v-if="dialogFrom.fileList1!==''&&dialogFrom.fileList1!==null" style="width: 148px;height: 148px"></el-image>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="类目图片">
          <el-upload
              :limit=1
              action="http://localhost:8081//mall/category/lcon"
              :show-file-list= "true"
              list-type="picture-card"
              :on-success="addpicture"
              :before-upload="beforeAvatarUpload"
              :file-list="dialogFrom.fileList2">
            <el-image :src="imgSrc+dialogFrom.typePicture" v-if="dialogFrom.fileList2!==''&&dialogFrom.fileList2!==null" style="width: 148px;height: 148px"></el-image>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="类目简介">
          <el-input v-model="dialogFrom.typeContent" autocomplete="off"  size="mini"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closedialog2()">取 消</el-button>
        <el-button type="primary" @click="updatediaFrom()">确 定</el-button>
      </div>
    </el-dialog>
  </el-card>
</div>
</template>


<script>
export default {
  name: 'Category',
  data(){
    return{
      //表单数据存放
      tableData:[],
      //父类目名字和id
      dategoryflm:{},
      //添加显示父类目
      dgflm:false,
      //添加的数据存放处
      dialogFrom:{
        id:'',
        typeName:'',
        typeKeyword:'',
        typeGrade:'',
        fid:'',
        typeLcon:'',
        typePicture:'',
        typeContent:'',
        fileList1:[],
        fileList2:[]
      },
      //添加模态框
      dialog:false,
      //修改模态框
      dialog2:false,
      //图片根路径
      imgSrc:'',
    }
  },
  created() {
    this.getTable()
    this.imgSrc = this.$http.defaults.baseURL
  },
  methods:{
    //获取列表栏数据
    async getTable () {
      const { data } = await this.$http.get('/mall/category')
      var list = data.data
      this.tableData = list
      console.log(list)
    },
    //添加
    selectflm(){
       if (this.dialogFrom.typeGrade!=='0'){
          console.log(this.dialogFrom.typeGrade)
          this.$http.get("/mall/category/getCateGoryflm")
         .then(res => {
            this.dategoryflm = res.data
         })
         this.dgflm=true
       }else{
         this.dgflm=false
         this.dialogFrom.fid=''
       }
    },
    //当我关闭添加模态框时做的事
    closelog1(){
      this.dialogFrom={
        id:'',
        typeName:'',
        typeKeyword:'',
        typeGrade:'',
        fid:'',
        typeLcon:'',
        typePicture:'',
        typeContent:'',
        fileList1:[],
        fileList2:[]
      }
      this.dgflm=false
    },
    //图标上传成功后返回的新文件名
    addlcon:function (response){
      this.dialogFrom.typeLcon=response
    },
    //图片上传成功后返回的新文件名
    addpicture:function (response){
      this.dialogFrom.typePicture=response
    },
    //限制上传图片的大小
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 10MB!');
      }
      return isLt2M;
    },
    //提交添加的from表单
    async submitDialog(){
      console.log(this.dialogFrom)
      const { data } = await this.$http.post("/mall/category/addCateGory",this.dialogFrom)
      if (data==1){
        this.dialog=false
        this.$message.success("添加成功！")
        this.getTable()
      }else{
        this.dialog=false
        this.$message.error("添加失败!")
      }
    },
    //删除前判断是否为子节点，或父节点内没有子节点
    async delcate(fid,id){
       if(fid===0){
          const { data } = await this.$http.get('/mall/category/selectSon',{
            params:{
              id:id
            }})
         if (data!==0){
           this.$message.error("删除失败!原因:存在子类目")
         }else{
           this.deleteOne(id)
         }
       }else{
         this.deleteOne(id)
       }
    },
    //真正删除走后端
    async deleteOne(id){
      const { data } = await this.$http.get('/mall/category/delCate',{
        params:{
          id:id
        }})
        this.$message.success("删除成功"+data)
        this.getTable()
    },
    //修改弹框
    update(val){
      this.dialogFrom.id=val.id
      this.dialogFrom.fid=val.fid
      this.dialogFrom.typeName=val.typeName
      this.dialogFrom.typeKeyword=val.typeKeyword
      this.dialogFrom.typeLcon=val.typeLcon
      this.dialogFrom.typePicture=val.typePicture
      this.dialogFrom.typeContent=val.typeContent
      this.dialogFrom.fileList1=val.fileList1
      this.dialogFrom.fileList2=val.fileList2
      this.dialogFrom.typeGrade=val.typeGrade
      this.dialog2=true
    },
    //dialog2点击关闭触发事件
    closedialog2(){
      this.dialog2 = false
      this.dialogFrom={
        id:'',
        typeName:'',
        typeKeyword:'',
        typeGrade:'',
        fid:'',
        typeLcon:'',
        typePicture:'',
        typeContent:'',
        fileList1:[],
        fileList2:[]
      }
    },
    //dalog关闭前回调
    beforeclose(){
      this.$confirm('确认关闭？')
              .then(_ => {
                this.dialogFrom={
                  id:'',
                  typeName:'',
                  typeKeyword:'',
                  typeGrade:'',
                  fid:'',
                  typeLcon:'',
                  typePicture:'',
                  typeContent:'',
                  fileList1:[],
                  fileList2:[]
                }
                this.dialog2 = false
              })
              .catch(_ => { });
    },//提交修改的from表单
    async updatediaFrom(){
      console.log(this.dialogFrom)
      const { data } = await this.$http.post("/mall/category/updateCateGory",this.dialogFrom)
      if (data==1){
        this.dialog2=false
        this.$message.success("修改成功！")
        this.getTable()
      }else{
        this.dialog=false
        this.$message.error("修改失败!")
      }
    },

  }
}
</script>

<style scoped>

</style>