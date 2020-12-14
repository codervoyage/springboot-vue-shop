<template>
    <el-card class="card_class">
        <el-row :gutter="20" style="margin-bottom: 10px">
            <el-col :span="4">
                <el-input
                        @clear="getTableList"
                        v-model="goods_id"
                        placeholder="请输入商品id"
                        clearable>
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-input
                        @clear="getTableList"
                        v-model="goods_num"
                        placeholder="请输入商品编号"
                        clearable>
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-input
                        @clear="getTableList"
                        v-model="goods_name"
                        placeholder="请输入商品名称"
                        clearable>
                </el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" icon="el-icon-search" @click="getTableList">查找</el-button>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" icon="el-icon-edit" @click="to_create">添加</el-button>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" icon="el-icon-upload2" @click="goods_exp">导出</el-button>
            </el-col>
        </el-row>
        <!-------------------------商品table区域-------------------------------------->
        <el-table :data="table_list" border>
            <el-table-column type="expand">
                <template v-slot="scope">
                    <el-form label-position="left" class="table-expand">
                        <el-form-item label="商品编号">
                            <span>{{scope.row.num}}</span>
                        </el-form-item>
                        <el-form-item label="宣传画廊">
                            <el-image v-if="scope.row.images"
                                      v-for="(img,i) in scope.row.images"
                                      :key="i"
                                      :src="img"
                                      style="width: 100px;height: 100px; margin-right: 20px"
                            ></el-image>
                        </el-form-item>
                        <el-form-item label="商品介绍">
                            <span>{{scope.row.text}}</span>
                        </el-form-item>
                        <el-form-item label="商品单位">
                            <span>{{scope.row.company}}</span>
                        </el-form-item>
                        <el-form-item label="关键字">
                            <el-tag v-if="scope.row.keywords"
                                    v-for="(tag,i) in scope.row.keywords"
                                    :key="i" style="margin-right: 10px">{{tag}}
                            </el-tag>
                        </el-form-item>
                        <el-form-item label="类目ID">
                            <span>{{scope.row.categoryId}}</span>
                        </el-form-item>
                        <el-form-item label="品牌商ID">
                            <span>{{scope.row.brandId}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="商品ID" prop="goodsId">
            </el-table-column>
            <el-table-column label="名称" prop="name">
            </el-table-column>
            <el-table-column label="图片">
                <template v-slot="scope">
                    <el-image style="width: 100px;height: 50px" v-if="scope.row.img" :src="scope.row.img"></el-image>
                </template>
            </el-table-column>
            <el-table-column label="分享图">
            </el-table-column>
            <el-table-column label="详情">
                <template v-slot="scope">
                    <el-dialog :visible.sync="detailDialogVisible" title="商品详情">
                        <div class="goods-detail-box" v-html="detail"></div>
                    </el-dialog>
                    <el-button @click="show_btn_cli(scope.row.detail)" type="primary" size="mini">查看</el-button>
                </template>
            </el-table-column>
            <el-table-column label="市场售价" prop="money">
            </el-table-column>
            <el-table-column label="当前价格" prop="">
                <template v-slot="scope">
                    <span>{{scope.row.goodsStocks[0].stockMoney === undefined ? 0 :scope.row.goodsStocks[0].stockMoney}}</span>
                </template>
            </el-table-column>
            <el-table-column label="是否新品">
                <template v-slot="scope">
                    <el-tag :type="scope.row.isNew === 1 ? 'success' : 'danger'">{{is_new[scope.row.isNew]}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="是否热品">
                <template v-slot="scope">
                    <el-tag :type="scope.row.isHot === 1 ? 'success' : 'danger'">{{is_hot[scope.row.isHot]}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="是否在售">
                <template v-slot="scope">
                    <el-tag :type="scope.row.isSell === 1 ? 'success' : 'danger'">{{is_sell[scope.row.isSell]}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150px">
                <template v-slot="scope">
                    <el-button type="primary" size="mini">编辑</el-button>
                    <el-button type="danger" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage"
                :page-sizes="[2, 5, 10, 20]"
                :page-size.sync="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </el-card>
</template>

<script>
  export default {
    name: 'Goods_list',
    data () {
      return {
        /* 数据源 */
        table_list: [],
        is_new: ['', '新品', '非新品'],
        is_hot: ['', '热品', '非热品'],
        is_sell: ['', '在售', '未售'],
        detailDialogVisible: false,
        /* 富文本 */
        detail: '',
        /* 分页 */
        currentPage: 1,
        pageSize: 2,
        total: 0,
        /* 模糊查询字段 */
        goods_id: '',
        goods_num: '',
        goods_name: ''

      }
    },
    created () {
      this.getTableList()
    },
    methods: {
      async getTableList () {
        const params = {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          goodsId: this.goods_id,
          num: this.goods_num,
          name: this.goods_name
        }
        const { data: res } = await this.$http.get('/goods/list', { params: params })
        this.table_list = res.data
        this.total = res.total
        this.table_list.forEach(item => {
          item.keywords = item.keywords.split(',')
          item.images = item.images.split(',')
        })

      },
      show_btn_cli (detail) {
        this.detail = detail
        this.detailDialogVisible = true
      },
      to_create () {
        this.$router.push('/goods/create')
      },
      handleCurrentChange () {
        this.getTableList()
      },
      handleSizeChange () {
        this.getTableList()
      },
      goods_exp () {
        import('../../plugins/Export2Excel').then(excel => {
          const tHeader = ['商品ID', '商品编号', '名称', '专柜价格','是否新品','是否热品','是否在售','宣传列表','商品介绍','详细介绍','关键字','类目ID','品牌商id']
          const filterVal = ['goodsId', 'num', 'name', 'money','isNew','isHot','isSell','images','text','company','keywords','categoryId','brandId']
          excel.export_json_to_excel2(tHeader, this.table_list, filterVal, '商品列表信息')
        })
      }
    }
  }
</script>

<style>
    .card_class {
        margin: 0 0 10px 10px;
    }

    .table-expand {
        font-size: 0;
    }

    .table-expand label {
        width: 100px;
        color: #99a9bf;
    }

    .table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
    }

    .goods-detail-box img {
        width: 100%;
    }
</style>