<template>
    <div>
        <el-card class="card_class">
            <el-row><h3>商品介绍</h3></el-row>
            <el-row>
                <el-form :model="form" :rules="rules" ref="rulesForm" label-width="150px">
                    <el-form-item label="商品编号" prop="num">
                        <el-input v-model="form.num"></el-input>
                    </el-form-item>
                    <el-form-item label="商品名称" prop="name">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="市场售价">
                        <el-input type="number" placeholder="0.0" v-model="form.money">
                            <span slot="append">元</span>
                        </el-input>
                    </el-form-item>
                    <el-form-item label="是否新品">
                        <el-radio v-model="form.state.is_new" label="1">新品</el-radio>
                        <el-radio v-model="form.state.is_new" label="2">非新品</el-radio>
                    </el-form-item>
                    <el-form-item label="是否热卖">
                        <el-radio v-model="form.state.is_hot" label="1">热卖</el-radio>
                        <el-radio v-model="form.state.is_hot" label="2">非热卖</el-radio>
                    </el-form-item>
                    <el-form-item label="是否在售">
                        <el-radio v-model="form.state.is_sell" label="1">在售</el-radio>
                        <el-radio v-model="form.state.is_sell" label="2">未售</el-radio>
                    </el-form-item>
                    <el-form-item label="商品图片">
                        <el-upload
                                :limit="1"
                                list-type="picture-card"
                                action="http://localhost:8081/goods/addPic"
                                :show-file-list="false"
                                :on-success="uploadPic"
                                :before-upload="beforeUpload">
                            <el-image v-if="form.upload_pic" :src="form.upload_pic" class="avatar"></el-image>
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="宣传画廊">
                        <el-upload
                                :limit="7"
                                multiple
                                action="#"
                                list-type="picture-card"
                                :http-request="uploadGallery"
                                :show-file-list="true"
                                :before-upload="beforeUpload"
                                :file-list="form.upload_gallery">
                            <i slot="default" class="el-icon-plus"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="商品单位">
                        <el-input v-model="form.Company" placeholder="件 / 个 / 盒"></el-input>
                    </el-form-item>
                    <el-form-item label="关键字">
                        <el-tag @close="key_word_closed(tag)" closable style="margin-right: 20px"
                                v-for="(tag,index) in form.keywords" :key="index">{{tag}}
                        </el-tag>
                        <el-button v-if="is_show" @click="incr_btn_cli" type="primary">+ 增加</el-button>
                        <el-input ref="key_word" v-model="key_word" @blur="key_word_blur" style="width: 90px"
                                  v-else></el-input>
                    </el-form-item>
                    <el-form-item label="所属分类">
                        <el-cascader
                                v-model="form.category"
                                :options="category_cascader_options"
                                :props="{ expandTrigger: 'hover',label:'typeName',value:'id' }"
                                @change="cascaderChange"></el-cascader>
                    </el-form-item>
                    <el-form-item label="所属品牌商">
                        <el-select v-model="form.brand">
                            <el-option v-for="brand in brand_select_optins" :key="brand.bId" :label="brand.name"
                                       :value="brand.bId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品简介">
                        <el-input v-model="form.text"></el-input>
                    </el-form-item>
                    <el-form-item label="商品详细介绍">
                        <editor v-model="form.detail" :init="editorInit"></editor>
                    </el-form-item>
                </el-form>
            </el-row>
        </el-card>

        <!----------------------****库存****规格****-------------------->
        <goods_stock v-on:guige="get_guige_data" v-on:stock="get_stock_data"></goods_stock>

        <!--------------------------****参数****------------------------>
        <el-card class="card_class">
            <el-row>
                <h3>商品参数</h3>
            </el-row>
            <el-row>
                <el-button type="primary" @click="dialogVisible = true">添加</el-button>
            </el-row>
            <el-table :data="table_data" stripe>
                <el-table-column prop="name" label="商品参数名称"></el-table-column>
                <el-table-column prop="value" label="商品参数值" align="center"></el-table-column>
                <el-table-column label="操作" align="right">
                    <template v-slot="scope">
                        <el-button type="danger" size="mini" @click="del_cli(scope)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-dialog
                    title="添加商品参数"
                    :visible.sync="dialogVisible"
                    width="50%">
                <el-col :offset="2">
                    <el-col :span="13">
                        <el-form v-model="form3" label-width="100px">
                            <el-form-item label="商品参数名称">
                                <el-input v-model="form3.name"></el-input>
                            </el-form-item>
                            <el-form-item label="商品参数值">
                                <el-input v-model="form3.value"></el-input>
                            </el-form-item>
                        </el-form>
                    </el-col>
                </el-col>
                <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                 <el-button type="primary" @click="dialogVisible_click">确 定</el-button>
            </span>
            </el-dialog>
        </el-card>

        <!---->
        <el-col :span="10" class="card_class">
            <el-button>取消</el-button>
            <el-button type="primary" @click="Goods_Add">上架</el-button>
        </el-col>

    </div>
</template>

<script>
  import Editor from '@tinymce/tinymce-vue'
  import Goods_stock from './Goods_stock'
  import axios from 'axios'

  export default {
    name: 'Goods_add',
    components: {
      Editor,
      Goods_stock
    },
    created () {
      this.get_bradn_list()
      this.get_category_list()
    },
    data () {
      return {
        rules: {
          name: [
            {
              required: true,
              message: '请输入商品编号',
              trigger: 'blur'
            }
          ],
          num: [
            {
              required: true,
              message: '请输入商品名称',
              trigger: 'blur'
            },
            {
              min: 3,
              max: 5,
              message: '长度在 3 到 5 个字符',
              trigger: 'blur'
            }
          ]
        },
        form: {
          num: '', /*编号*/
          name: '',/*名字*/
          money: '',/*售价*/
          state: {
            is_new: '2', /*是否新品*/
            is_hot: '2', /*是否热卖*/
            is_sell: '2' /*是否在售*/
          },
          upload_pic: '', /*商品图片*/
          upload_gallery: [],/*宣传画廊*/
          Company: '',/*商品单位*/
          keywords: [],/*关键字数组*/
          category: [],/*商品类目*/
          brand: '',  /*品牌制造商*/
          text: '', /*商品简介*/
          detail: '',/*富文本*/

        },
        is_show: true,
        key_word: '',/*关键字input输入双向绑定*/
        category_cascader_options: [],/*类目级联选择器数据*/
        brand_select_optins: [],/*品牌制造商下拉菜单数据*/
        editorInit: {
          language: 'zh_CN',
          height: 500,
          convert_urls: false,
          plugins: ['advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount'],
          toolbar: ['searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample', 'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'],
          images_upload_handler: function (blobInfo, success, failure) {
            const formData = new FormData()
            formData.append('file', blobInfo.blob())
            axios.post('/goods/add_fwb', formData).then(res => {
              success('http://localhost:8081/' + res.data)
            }).catch(() => {
              failure('上传失败，请重新上传')
            })
          }
        },

        /*商品规格与库存=======================================*/
        _stock: [], /*商品规格*/
        stock_: [
          {
            value: ['标准'],
            price: 0,
            num: 0,
            pic: ''
          }
        ],  /*商品库存*/
        /*参数组件的-----------------------------------------*/
        /*商品参数数据*/
        table_data: [],
        dialogVisible: false,
        form3: {
          name: '',
          value: ''
        }
      }
    },
    methods: {
      get_stock_data (data) {
        this.stock_ = []
        this.stock_ = data
      },
      get_guige_data (data) {
        this._stock = data
      },
      // 商品图片
      uploadPic (response) {
        const http_url = 'http://localhost:8081/'
        this.form.upload_pic = http_url + response
      },
      beforeUpload (pic) {
        const isJPG = pic.type === 'image/jpeg'
        const isLt10M = pic.size / 1024 / 1024 < 10
        if (!isJPG) {
          this.$message.error('该图片格式不支持！')
        }
        if (!isLt10M) {
          this.$message.error('上传图片大小不可超过10M！')

        }
        return isLt10M && isJPG
      },
      uploadGallery (param) {
        const formData = new FormData()
        formData.append('file', param.file)
        this.$http.post('http://localhost:8081/goods/addGallery', formData).then(res => {
          const http_url = 'http://localhost:8081/'
          const pics = { url: http_url + res.data }
          this.form.upload_gallery.push(pics)
        })
      },
      /*关键字_input聚焦*/
      incr_btn_cli () {
        this.is_show = false
        this.$nextTick(_ => {
          this.$refs['key_word'].focus()
        })
      },
      /*失焦之后_[]push*/
      key_word_blur () {
        let flag = true
        this.form.keywords.forEach(item => {
          if (item === this.key_word) {
            flag = false
            this.$message.warning('关键词已存在!')
            this.$refs['key_word'].focus()
          }
        })
        if (this.key_word === '') {
          flag = false
          this.$message.warning('关键词需要非空!')
          this.$refs['key_word'].focus()
        }
        if (flag) {
          this.form.keywords.push(this.key_word)
          this.key_word = ''
          this.is_show = true
        }
      },
      /*删除tag,触发*/
      key_word_closed (tag) {
        this.form.keywords.splice(this.form.keywords.indexOf(tag), 1)
      },
      async get_bradn_list () {
        const { data: res } = await this.$http.get('/mall/brand')
        if (res.meta.status === 200) {
          this.brand_select_optins = res.data
        } else {
          this.$message.error('制造商数据获取失败!')
        }
      },
      async get_category_list () {
        const { data } = await this.$http.get('/mall/category')
        this.category_cascader_options = data.data
      },
      cascaderChange (node) {
        this.category = node
      },

      /*参数区 方法---------------------------------------------*/
      del_cli (obj) {
        const par_name = obj.row.name
        this.table_data.forEach((item, index) => {
          if (item.name === par_name) {
            this.table_data.splice(index, 1)
          }
        })
      },
      dialogVisible_click () {
        this.table_data.push({ ...this.form3 })
        this.form3 = {}
        this.dialogVisible = false
      },
      Goods_Add () {
        this.$refs['rulesForm'].validate(valid => {
          if (!valid) {
            this.$message.warning('必要参数未添加!')
            return
          }
          const isNew = this.form.state.is_new
          const isHot = this.form.state.is_hot
          const isSell = this.form.state.is_sell
          const img = this.form.upload_pic
          const company = this.form.Company
          const keywords = this.form.keywords.toString()
          const categoryId = this.form.category[this.form.category.length - 1]
          const brandId = this.form.brand
          const object = { ...this.form }
          const images = []
          for (let i in object.upload_gallery) {
            images.push(object.upload_gallery[i].url + '')
          }
          /*---------------------规格----------------------------*/

          if (this._stock === undefined) {
            object.goodsSpec = []
            object.goodsSpec.push({
              name: '规格',
              value: '标准',
              img: ''
            })
          } else {
            object.goodsSpec = []
            object.goodsSpec = this._stock
            for (let i in object.goodsSpec) {
              object.goodsSpec[i].img = object.goodsSpec[i].pic
              delete object.goodsSpec[i].pic
            }
          }
          /*---------------------库存----------------------------*/
          object.goodsStocks = []
          object.goodsStocks = this.stock_
          for (let i in object.goodsStocks) {
            object.goodsStocks[i].stockValue = object.goodsStocks[i].value.toString()
            object.goodsStocks[i].stockMoney = object.goodsStocks[i].price
            object.goodsStocks[i].img = object.goodsStocks[i].pic
            object.goodsStocks[i].stockNum = object.goodsStocks[i].num
            delete object.goodsStocks[i].value
            delete object.goodsStocks[i].price
            delete object.goodsStocks[i].pic
            delete object.goodsStocks[i].num
          }
          /*---------------------参数----------------------------*/
          object.goodsParas = []
          object.goodsParas = this.table_data
          /*---------------------商品----------------------------*/
          object.isNew = isNew
          object.isHot = isHot
          object.isSell = isSell
          object.company = company
          object.img = img
          object.images = images.toString()
          object.keywords = keywords
          object.categoryId = categoryId
          object.brandId = brandId
          /* 发送网络请求 */
          this.$http.post('/goods_add', object).then(res => {
            if (res.data.meta.status === 200) {
              this.$message.success('商品添加成功!!')
            } else {
              this.$message.success('商品添加失败!!')
            }
          })
          console.log(object)
        })

      }
    }
  }
</script>

<style scoped>
    .card_class {
        margin: 0 0 10px 10px;
    }
</style>