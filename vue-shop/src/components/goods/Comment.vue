<template>
    <div>
        <el-card id="box">
            <el-row :gutter="20" style="margin-bottom: 10px">
                <el-col :span="4" :offset="1">
                    <el-input
                            @clear="getTableList"
                            v-model="user_id"
                            placeholder="请输入用户id"
                            clearable>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-input
                            @clear="getTableList"
                            v-model="goods_id"
                            placeholder="请输入商品id"
                            clearable>
                    </el-input>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="el-icon-search" @click="getTableList">查找</el-button>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="el-icon-upload2" @click="exportExcel">导出</el-button>
                </el-col>
            </el-row>
            <!-------------第----------二-----------行------------->
            <el-row :gutter="20">
                <el-col :span="22" :offset="1">
                    <el-table :data="tableData" stripe border>
                        <el-table-column prop="userId" label="用户ID"></el-table-column>
                        <el-table-column prop="goodsId" label="商品ID"></el-table-column>
                        <el-table-column prop="scoring" label="打分"></el-table-column>
                        <el-table-column prop="text" label="评论内容"></el-table-column>
                        <el-table-column label="评论图片">
                            <template v-slot="scope">
                                <el-image style="width: 50px;height: 50px;" :src="ajaxUrl+scope.row.cImg"></el-image>
                            </template>
                        </el-table-column>
                        <el-table-column prop="createTime" label="时间"></el-table-column>
                        <el-table-column label="操作">
                            <template v-slot="scope">
                                <el-button type="primary" @click="dialogVisible = true" size="mini">回复</el-button>
                                <el-button type="danger" @click="delComment(scope.row.cId)" size="mini">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!---------------------分----------------页--------------------------------------->
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="pageNum"
                            :page-sizes="[1, 2, 5, 10]"
                            :page-size.sync="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total" background>
                    </el-pagination>
                    <!------------------------------------------------------------>
                    <el-dialog
                            title="回复"
                            :visible.sync="dialogVisible"
                            width="30%">
                        <el-col :offset="2" :span="5">
                            <b>回复内容</b>
                        </el-col>
                        <el-col :span="15" style="margin-bottom: 30px;">
                            <el-input type="textarea"></el-input>
                        </el-col>
                        <span slot="footer" class="dialog-footer">
                            <el-button @click="dialogVisible = false">取 消</el-button>
                            <el-button type="primary" @click="dialogClick">确 定</el-button>
                        </span>
                    </el-dialog>
                </el-col>
            </el-row>
        </el-card>
    </div>
</template>

<script>
  export default {
    name: 'Comment',
    data () {
      return {
        pageNum: 1,
        pageSize: 5,
        total: '',
        user_id: '',
        goods_id: '',
        tableData: [],
        ajaxUrl: '',
        dialogVisible: false
      }
    },
    created () {
      this.ajaxUrl = this.$http.defaults.baseURL
      this.getTableList()
    },
    methods: {
      async getTableList () {
        const userId = this.user_id
        const goodsId = this.goods_id
        if (Number.isNaN(Number(userId)) || Number.isNaN(Number(goodsId))) {
          this.$message.error('请输入正确ID！')
        } else {
          const { data: res } = await this.$http.get('/goods/comments/getAll',
            {
              params: {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                userId: this.user_id,
                goodsId: this.goods_id
              }
            })
          if (res.meta.status === 200) {
            this.tableData = res.data
            this.total = res.total
          }
        }
      },
      delComment (id) {
        this.$confirm('确认删除吗', { type: 'warning' }).then(_ => {
          this.$http.delete('/comment/delById', { params: { id: id } }).then(res => {
            if (res.data.meta.status === 200) {
              this.$message.success('删除成功')
              this.getTableList()
            } else {
              this.$message.error(('删除失败'))
            }
          })
        })
      },
      handleSizeChange () {
        this.getTableList()
      },
      handleCurrentChange () {
        this.getTableList()
      },
      dialogClick () {
        this.$notify.error({
          title: '提示',
          message: '该评论已回复'
        });
      },
      // 导出表格所用
      exportExcel () {
        import('../../plugins/Export2Excel').then(excel => {
          const tHeader = ['用户ID', '商品ID', '打分', '评论内容', '时间']
          const filterVal = ['userId', 'goodsId', 'scoring', 'text', 'createTime']
          excel.export_json_to_excel2(tHeader, this.tableData, filterVal, '商品评论表格')
        })
      }
    }
  }
</script>

<style scoped>
    #box {
        margin: 0 10px 0 10px;
    }
</style>