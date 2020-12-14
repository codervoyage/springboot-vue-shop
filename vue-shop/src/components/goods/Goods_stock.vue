<template>
    <div>
        <el-card class="card_class">
            <el-row>
                <h3>商品规格</h3>
            </el-row>
            <el-row>
                <el-col :span="5">
                    <el-radio-group v-model="radio_btn" @change="radio_chang">
                        <el-radio-button label="默认标准规格"></el-radio-button>
                        <el-radio-button label="多规格支持"></el-radio-button>
                    </el-radio-group>
                </el-col>
                <el-col :offset="5" :span="14">
                    <el-button @click="set_dia_btn_click" type="primary" plain v-show="many_table_btn">添加
                    </el-button>
                </el-col>
            </el-row>
            <el-row>
                <!-----------------------------默认规格的表格-->
                <el-table :data="default_table" v-if="pecification_table_is_show" stripe>
                    <el-table-column prop="name" label="规格名"></el-table-column>
                    <el-table-column prop="value" label="规格值">
                        <template v-slot="scope">
                            <el-tag>{{scope.row.value}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="规格图片"></el-table-column>
                </el-table>
                <!------------------------------自定义规格的表格--------------->
                <el-table :data="set_form_data" v-else stripe>
                    <el-table-column prop="name" label="规格名">
                    </el-table-column>
                    <el-table-column label="规格值">
                        <template v-slot="scope">
                            <el-tag>{{scope.row.value}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="规格图片">
                        <template v-slot="scope">
                            <el-image style="width: 50px; height: 50px;" v-if="scope.row.pic"
                                      :src="scope.row.pic"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template v-slot="scope">
                            <el-button @click="set_form_del(scope.row)" type="danger" size="mini">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-row>
        </el-card>
        <!---------------------分-----------------------隔--------------------------线-------------->
        <el-card class="card_class">
            <el-row>
                <h3>商品库存</h3>
            </el-row>
            <el-row>
                <el-table :data="default_stock_table" v-if="pecification_table_is_show" stripe>
                    <el-table-column label="商品规格" align="left">
                        <template v-slot="scope">
                            <el-tag :key="i" v-for="(tag,i) in scope.row.value">{{tag}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="price" label="货品售价" align="center"></el-table-column>
                    <el-table-column prop="num" label="货品数量" align="center"></el-table-column>
                    <el-table-column label="货品图片" align="center">
                        <template v-slot="scope">
                            <el-image v-if="scope.row.pic" :src="scope.row.pic"
                                      style="width: 50px;height: 50px"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" align="center">
                        <el-button @click="def_stock_dialogVisible = true" type="primary" size="mini">设置</el-button>
                    </el-table-column>
                </el-table>
                <!-----------------------------自定义规格的库存------------------------------------->
                <el-table :data="res_all" v-else stripe>
                    <el-table-column label="货品规格">
                        <template v-slot="scope">
                            <el-tag style="margin-right: 10px" v-for="(item,index) in scope.row.value" :key="index">
                                {{item}}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="price" label="货品售价"></el-table-column>
                    <el-table-column prop="num" label="货品数量"></el-table-column>
                    <el-table-column label="货品图片">
                        <template v-slot="scope">
                            <el-image style="width: 50px;height: 50px" v-if="scope.row.pic"
                                      :src="scope.row.pic"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template v-slot="scope">
                            <el-button @click="set_stock_form_show(scope.row)" type="primary" size="mini">设置</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-row>
        </el-card>
        <!--*-*-*-*-*-*-----DiaLog 展示框----------------********************************-->
        <el-dialog
                title="添加货品"
                :visible.sync="def_stock_dialogVisible"
                width="50%">
            <el-col :offset="2">
                <el-col :span="14">
                    <el-form v-model="def_stock_form" label-width="100px">
                        <el-form-item label="货品规格列">
                            <el-tag>{{def_stock_form.state}}</el-tag>
                        </el-form-item>
                        <el-form-item label="货品售价">
                            <el-input type="number" v-model="def_stock_form.price"></el-input>
                        </el-form-item>
                        <el-form-item label="货品数量">
                            <el-input type="number" v-model="def_stock_form.num"></el-input>
                        </el-form-item>
                        <el-form-item label="货品图片">
                            <el-upload
                                    list-type="picture-card"
                                    action="http://localhost:8081/goods/addHuopin"
                                    :show-file-list="false"
                                    :on-success="def_stock_upload">
                                <el-image v-if="def_stock_form.pic" :src="def_stock_form.pic"></el-image>
                                <i v-else slot="default" class="el-icon-plus"></i>
                            </el-upload>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-col>
            <span slot="footer" class="dialog-footer">
                    <el-button @click="def_stock_dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="def_stock_dia_cli">确 定</el-button>
             </span>
        </el-dialog>

        <el-dialog
                title="设置规格"
                :visible.sync="set_dialogVisible"
                width="50%">
            <el-col :offset="2">
                <el-col :span="14">
                    <el-form v-model="set_form" label-width="100px">
                        <el-form-item label="规格名">
                            <el-input v-model="set_form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="规格值">
                            <el-input v-model="set_form.value"></el-input>
                        </el-form-item>
                        <el-form-item label="规格图片">
                            <el-upload
                                    list-type="picture-card"
                                    action="http://localhost:8081/goods/addGuige"
                                    :on-success="set_from_upload"
                                    :show-file-list="false"
                            >
                                <el-image v-if="set_form.pic" :src="set_form.pic"></el-image>
                                <i v-else slot="default" class="el-icon-plus"></i>
                            </el-upload>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-col>
            <span slot="footer" class="dialog-footer">
                    <el-button @click="set_dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="set_dia_cli">确 定</el-button>
             </span>
        </el-dialog>

        <el-dialog
                title="添加货品"
                :visible.sync="set_stock_dialogVisible"
                width="50%">
            <el-col :offset="2">
                <el-col :span="14">
                    <el-form v-model="set_stock_form" label-width="100px">
                        <el-form-item label="货品规格列">
                            <el-tag style="margin-right: 10px" v-for="(item,index) in set_stock_form.value"
                                    :key="index">{{item}}
                            </el-tag>
                        </el-form-item>
                        <el-form-item label="货品售价">
                            <el-input v-model="set_stock_form.price"></el-input>
                        </el-form-item>
                        <el-form-item label="货品数量">
                            <el-input v-model="set_stock_form.num"></el-input>
                        </el-form-item>
                        <el-form-item label="货品图片">
                            <el-upload
                                    list-type="picture-card"
                                    action="http://localhost:8081/goods/addHuopin"
                                    :on-success="set_stock_from_upload"
                                    :show-file-list="false"
                            >
                                <el-image v-if="set_stock_form.pic" :src="set_stock_form.pic"></el-image>
                                <i v-else slot="default" class="el-icon-plus"></i>
                            </el-upload>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-col>
            <span slot="footer" class="dialog-footer">
                    <el-button @click="set_stock_dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="set_stock_cli">确 定</el-button>
             </span>
        </el-dialog>
    </div>
</template>

<script>
  export default {
    name: 'Goods_stock',
    data () {
      return {
        /* *************默认规格相关**************** */
        radio_btn: '默认标准规格',
        pecification_table_is_show: true,
        many_table_btn: false,
        default_table: [
          {
            name: '规格',
            value: '标准'
          }
        ],
        /* *************默认库存相关**************** */
        default_stock_table: [
          {
            value: ['标准'],
            price: 0,
            num: 0,
            pic: ''
          }
        ],
        def_stock_dialogVisible: false,
        // 设置默认规格的库存
        def_stock_form: {
          state: '标准',
          price: 0,
          num: 0,
          pic: ''
        },
        /* ***************手动设置规格******************** */
        set_dialogVisible: false,
        set_form: {
          name: '',
          value: '',
          pic: ''
        },
        set_form_data: [],
        /**/
        result: [],
        res_all: [],
        index: 0,
        /**/

        set_stock_dialogVisible: false,
        set_stock_form: {
          value: [],
          price: 0,
          num: 0,
          pic: '',
          flag: 100
        }
      }
    },
    methods: {
      /*判断是默认规格还是自定义规格,控制显示与反转*/
      radio_chang (radio_label) {
        if (radio_label !== '默认标准规格') {
          this.pecification_table_is_show = false
          this.many_table_btn = true
        } else {
          this.pecification_table_is_show = true
          this.many_table_btn = false
        }
      },
      /*1. 点击默认库存的设置按钮 2. 默认库存的表单_添加图片*/
      def_stock_upload (response) {
        const http_url = 'http://localhost:8081/'
        this.def_stock_form.pic = http_url + response
      },
      /*3. 默认规格中_设置标准库存数量_并且控制form表单关闭*/
      def_stock_dia_cli () {
        const obj = { ...this.def_stock_form }
        const arr = []
        obj.value = arr
        obj.value.push(obj.state)
        this.default_stock_table.splice(0, 1, obj)
        // 以下两种赋值方式都不会触发Vue的DOM刷新,未知原因
        // this.$forceUpdate() 强制刷新也不行 ????
        // this.default_stock_table[0] = this.def_stock_form
        // this.default_stock_table[0] = { ... this.def_stock_form}
        this.def_stock_dialogVisible = false
        this.$emit('guige', this.default_table)
        this.$emit('stock', this.default_stock_table)
      },
      /*1. 点击添加按钮展开表单,此表单是手动添加规格的表单*/
      set_dia_btn_click () {
        this.set_form.name = ''
        this.set_form.value = ''
        this.set_form.pic = ''
        this.set_dialogVisible = true
      },
      /*2. 手动添加规格的图片请求方法*/
      set_from_upload (response) {
        const http_url = 'http://localhost:8081/'
        /*const obj = { url: http_url + response }
        let test = []
        this.set_form.pic = test
        this.set_form.pic.push(obj)*/
        this.set_form.pic = http_url + response
      },
      /*3. 手动添加规格的form表单的提交*/
      set_dia_cli () {
        const formData = this.set_form
        if (formData.name === '' || formData.value === '' || formData.name === undefined || formData.value === undefined) {
          this.$message.error('规格名或者规格值是必须的!')
          this.set_dialogVisible = false
        } else {
          let flag = true
          this.set_form_data.forEach(item => {
            if (item.value === this.set_form.value) {
              this.set_dialogVisible = false
              this.$message.warning('已经存在的规格值: ' + this.set_form.value)
              flag = false
            }
          })
          if (flag) {
            this.set_form_data.push({ ...this.set_form })
            this.set_dialogVisible = false
            this.group_data()
          }
        }
        //向父组件传递数据
        this.$emit('guige', this.set_form_data)
        this.$emit('stock', this.res_all)
      },
      /*封装,为了适用在删除方法上_*/
      group_data () {
        let n = []
        let s1 = []
        let s2 = []
        let s3 = []
        this.set_form_data.forEach(item => {
          if (n.indexOf(item.name) === -1) {
            n.push(item.name)
          }
        })
        this.set_form_data.forEach(item => {
          if (n[0] === item.name) {
            //console.log(1)
            s1.push(item.value)
          } else if (n[1] === item.name) {
            //console.log(2)
            s2.push(item.value)
          } else {
            //console.log(3)
            s3.push(item.value)
          }
        })
        // console.log(s1)
        // console.log(s2)
        // console.log(s3)
        let list = []
        if (s1.length > 0) {
          list.push(s1)
          //console.log(1)
        }
        if (s2.length > 0) {
          //console.log(2)
          list.push(s2)
        }
        if (s3.length > 0) {
          list.push(s3)
          //console.log(3)
        }

        let res = []
        //console.log(list)
        this.res_all = []
        this.recursion_test(list, 0, res)
        //console.log(this.res_all)
        //console.log(this.set_form_data)
      },
      /* 递归排列 */
      /* 参考链接 : https://blog.csdn.net/tyhj_sf/article/details/53893125 */
      recursion_test (list, beginIndex, result) {
        if (beginIndex == list.length) {
          //在这里进行你自己的处理，比如打印组合的结果
          this.result = []
          for (let i of result) {
            this.result.push(i)
          }
          const obj = {
            price: 0,
            num: 0,
            pic: '',
            value: this.result
          }
          this.res_all.push(obj)
          return
        }
        for (let c of list[beginIndex]) {
          result[beginIndex] = c
          this.recursion_test(list, beginIndex + 1, result)
        }
      },
      /*手动添加规格的删除按钮*/
      set_form_del (row) {
        this.set_form_data.forEach((item, index) => {
          if (item.value === row.value) {
            this.set_form_data.splice(index, 1)
          }
        })
        if (this.set_form_data.length < 1) {
          this.res_all = []
          this.$emit('guige', this.default_table)
          this.$emit('stock', this.default_stock_table)
        } else {
          this.group_data()
          this.$emit('guige', this.set_form_data)
          this.$emit('stock', this.res_all)
        }

      },
      set_stock_form_show (row) {
        this.set_stock_dialogVisible = true
        this.set_stock_form = { ...row }
        /* 查询是哪个数据需要设置 保存一下下标值 */
        this.res_all.forEach((item, index) => {
          if (item.value.toString() === this.set_stock_form.value.toString()) {
            this.set_stock_form.flag = index
          }
        })
      },
      set_stock_from_upload (rep) {
        const http_url = 'http://localhost:8081/'
        this.set_stock_form.pic = http_url + rep
      },
      set_stock_cli () {
        this.set_stock_dialogVisible = false
        /* 如果这个下标值不是初始值100的话 说明找到了要修改的位置 , 赋值即可*/
        if (this.set_stock_form.flag !== 100) {
          /*我们修改一个数组其中的一个值，或者添加一条数据时，不管用。
          *  使用 this.$set(要修改的数组,下标,新值)
          * */
          //this.res_all[this.set_stock_form.flag] = { ...this.set_stock_form }
          this.$set(this.res_all, this.set_stock_form.flag, { ...this.set_stock_form })
          //console.log(this.res_all)
          this.$emit('guige', this.set_form_data)
          this.$emit('stock', this.res_all)
        } else {
          this.$message.error('警告! 设置自定义库存时出现错误! ')
        }

      }

    }
  }
</script>

<style scoped>
    .card_class {
        margin: 0 0 10px 10px;
    }
</style>