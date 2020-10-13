<template>
  <div>
    <el-card id="box">
      <el-table :data="tableData" style="width: 100%" stripe border>
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
                        <el-tag v-else-if="scope.row.level === 2" type="success">市</el-tag>
                        <el-tag v-else type="warning">县</el-tag>
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
                  <el-tag v-else-if="scope.row.level === 2" type="success">市</el-tag>
                  <el-tag v-else type="warning">县</el-tag>
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
            <el-tag v-else-if="scope.row.level === 2" type="success">市</el-tag>
            <el-tag v-else type="warning">县</el-tag>
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
      tableData: []
    }
  },
  created () {
    this.getTableData()
  },
  methods:{
    async getTableData () {
      const { data: res } = await this.$http.get('/mall/getArea')
      if (res.meta.status === 200) {
        this.tableData = res.data
        console.log(res.data)
      } else {
        this.$message.error('后端获取数据失败')
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