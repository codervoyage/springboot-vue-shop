<template>
    <div>
        <el-card id="box">
            <!--<el-table :data="tableData" style="width: 100%" stripe border>
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-table :data="props.row.children" style="width: 100%" :show-header=false>
                    <el-table-column type="expand">
                      <template slot-scope="p">
                        <el-table :data="p.row.children" style="width: 100%" :show-header=false>
                          <el-table-column
                              prop="areaName">
                          </el-table-column>
                          <el-table-column>
                            <template v-slot="scope">
                              <el-tag v-if="scope.row.level === 1">省</el-tag>
                              <el-tag v-if="scope.row.level === 2" type="success">市</el-tag>
                              <el-tag v-if="scope.row.level === 3" type="warning">区</el-tag>
                            </template>
                          </el-table-column>
                          <el-table-column
                              prop="areaCode">
                          </el-table-column>
                        </el-table>
                      </template>
                    </el-table-column>
                    <el-table-column
                        prop="areaName">
                    </el-table-column>
                    <el-table-column>
                      <template v-slot="scope">
                        <el-tag v-if="scope.row.level === 1">省</el-tag>
                        <el-tag v-if="scope.row.level === 2" type="success">市</el-tag>
                        <el-tag v-if="scope.row.level === 3" type="warning">区</el-tag>
                      </template>
                    </el-table-column>
                    <el-table-column
                        prop="areaCode">
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column
                  label="区域名称"
                  prop="areaName">
              </el-table-column>
              <el-table-column label="区域类型">
                <template v-slot="scope">
                  <el-tag v-if="scope.row.level === 1">省</el-tag>
                  <el-tag v-if="scope.row.level === 2" type="success">市</el-tag>
                  <el-tag v-if="scope.row.level === 3" type="warning">区</el-tag>
                </template>
              </el-table-column>
              <el-table-column
                  label="区域编码"
                  prop="areaCode">
              </el-table-column>
            </el-table>-->
            <el-table
                    stripe
                    border
                    :data="lazyTableList"
                    row-key="areaId"
                    lazy
                    :load="getLazyList"
            >
                <el-table-column
                        label="区域名称"
                        prop="areaName">
                </el-table-column>
                <el-table-column
                        label="区域类型">
                    <template v-slot="scope">
                        <el-tag v-if="scope.row.level === 1">省</el-tag>
                        <el-tag v-if="scope.row.level === 2" type="success">市</el-tag>
                        <el-tag v-if="scope.row.level === 3" type="warning">区</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        label="区域编码"
                        prop="areaCode">
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
  export default {
    name: 'Area',
    data () {
      return {
        tableData: [],
        lazyTableList: []
      }
    },
    created () {
      //this.getTableData()
      this.getLazyTableList()
    },
    methods: {
      async getTableData () {
        const { data: res } = await this.$http.get('/mall/getArea')
        if (res.meta.status === 200) {
          this.tableData = res.data
        } else {
          this.$message.error('后端获取数据失败')
        }
      },
      async getLazyTableList () {
        const { data: res } = await this.$http.get('/mall/getArea')
        if (res.meta.status === 200) {
          res.data.forEach(item => {
            if (item.level !== 3) {
              item.hasChildren = true
            }
          })
          this.lazyTableList = res.data
        } else {
          this.$message.error('后端获取数据失败')
        }
      },
      async getLazyList (row, treeNode, resolve) {
        const { data: res } = await this.$http.get('/mall/getArea/by_id', { params: { id: row.areaId } })
        if (res.meta.status === 200) {
          res.data.forEach(item => {
            if (item.level !== 3) {
              item.hasChildren = true
            }
          })
          resolve(res.data)
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