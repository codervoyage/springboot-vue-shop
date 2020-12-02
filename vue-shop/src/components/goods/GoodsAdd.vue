<template>
    <div>
      <el-card id="box">
      <h4>商品介绍</h4>
        <el-form :model="GoodsForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="商品编号" prop="goodsId">
            <el-input v-model="GoodsForm.goodsId"></el-input>
          </el-form-item>
          <el-form-item label="商品名称" prop="goodsName">
            <el-input v-model="GoodsForm.goodsName"></el-input>
          </el-form-item>
          <el-form-item label="商品价格" prop="marketPrice">
            <el-input v-model="GoodsForm.marketPrice" placeholder="0.00"></el-input>
          </el-form-item>
          <el-form-item label="是否新品" prop="isNew">
            <el-radio-group v-model="GoodsForm.isNew">
              <el-radio  :label="1" >新品</el-radio>
              <el-radio :label="0" >非新品</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="是否热卖" prop="isHot">
            <el-radio-group v-model="GoodsForm.isHot">
              <el-radio  :label="1" >新品</el-radio>
              <el-radio :label="0" >非新品</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="是否在售" prop="isSells">
            <el-radio-group v-model="GoodsForm.isSells">
              <el-radio  :label="1" >新品</el-radio>
              <el-radio :label="0" >非新品</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="goodsPic" label="商品图片">
          <el-upload
              action="http://localhost:8079//goods/addPic"
              :limit="1"
              list-type="picture-card"
              :show-file-list="true"
              :on-success="addprotogoods"
              :before-upload="beforeUpload">
            <i class="el-icon-plus"></i>
          </el-upload>
          </el-form-item>
          <el-form-item prop="goodsPropagate" label="宣传画廊">
            <el-upload
                action="http://localhost:8079//goods/addGoodsPropagate"
                :limit="10"
                list-type="picture-card"
                :show-file-list="true"
                :on-success="addprotogoods2"
                :before-upload="beforeUpload2">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item prop="keyword" label="关键字">
            <el-tag
                :key="tag"
                v-for="tag in GoodsForm.keyword"
                closable
                :disable-transitions="false"
                @close="handleClose(tag)"
                style="margin-right:5px;"
            >
              {{tag}}
            </el-tag >
            <el-input  ref="newKeywordInput" v-model="tagName" type="text" v-if="isShowInput" style="width: 100px" @blur="uploadValue" @keyup.enter.native="uploadValue" ></el-input>
            <el-button v-if="isShowButton" type="primary" icon="el-icon-plus" @click="showInput">添加</el-button>
          </el-form-item>


        </el-form>
      </el-card>
    </div>
</template>

<script>
export default {
name: "GoodsAdd",
  data() {
    return {
      isShowInput:false,
      isShowButton:true,
      tagName:'',
      GoodsForm: {
        goodsId: '',
        goodsName: '',
        marketPrice: '',
        keyword:[],
        isNew:1,
        isHot:1,
        isSells:1,
        goodsPic:'',
        goodsPropagate:'',
      },
     rules: {
        goodsId: [
          { required: true, message: '请输入商品编号', trigger: 'blur' },
          { min: 3, max: 7, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        goodsName: [
          { required: true, message: '请输入商品名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        marketPrice: [
          { required: true, message: '请输入商品价格', trigger: 'blur' },
          { type: 'number', message: '请输入数字', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
   submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
   /*================图片上传======================*/
    /*添加返回的后端存放照片的地址到页面的对象中*/
    addprotogoods(res){
      this.GoodsForm.goodsPic=res;
    },
    /*在图片上传前做的图片审核功能*/
    beforeUpload(pic){
      alert(pic.type)
      const isJPG= pic.type === 'image/jpeg'
      const isLt10M = pic.size /1024 /1024<10
      if(!isJPG){
        this.$message.error('该图片格式不支持！')
      }
      if(!isLt10M){
        this.$message.error('上传图片大小不可超过10M！')

      }
      return isLt10M && isJPG;
    },
    addprotogoods2(res){
      this.GoodsForm.goodsPropagate=res;
    },
    beforeUpload2(pic){
      alert(pic.type)
      const isJPG= pic.type === 'image/jpeg'
      const isLt10M = pic.size /1024 /1024<10
      if(!isJPG){
        this.$message.error('该图片格式不支持！')
      }
      if(!isLt10M){
        this.$message.error('上传图片大小不可超过10M！')

      }
      return isLt10M && isJPG;
    },
    /*================关键字添加======================*/
    showInput(){
      this.isShowInput=!this.isShowInput
      this.isShowButton=!this.isShowButton
      this.$nextTick(_ => {
        this.$refs.newKeywordInput.focus()
      })

    },
    uploadValue(){
      this.isShowInput=!this.isShowInput
      this.isShowButton=!this.isShowButton
      if(this.tagName!=''){
          this.GoodsForm.keyword.push(this.tagName)
          this.tagName=''
      }

    },
    handleClose(tag){
      const keyArr=this.GoodsForm.keyword
      keyArr.splice(keyArr.indexOf(tag),1)
    }
  }
}
</script>

<style scoped>
.el-tag + .el-tag {
  margin-left: 10px;
}
</style>