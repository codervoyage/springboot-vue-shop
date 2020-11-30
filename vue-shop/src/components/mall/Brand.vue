<template>
  <div>
    <el-card id="box">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="4" :offset="1">
          <el-input
              @clear="getTableData"
              v-model="brandId"
              placeholder="输入品牌商ID"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-input
              @clear="getTableData"
              v-model="brandName"
              placeholder="输入品牌商名称"
              clearable>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-search" @click="getTableData">查找</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-search" @click="dialogVisible=true">添加</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" icon="el-icon-upload2" @click="exportExcel">导出</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="22" :offset="1">
          <el-table id="table"
                    :data="tableData"
                    border>
            <el-table-column prop="bId" label="品牌商ID"></el-table-column>
            <el-table-column prop="name" label="品牌商名称"></el-table-column>
            <el-table-column prop="img" label="品牌商图片">
              <template v-slot="scope">
                <img id="img" :src="imgSrc+scope.row.img"/>
              </template>
            </el-table-column>
            <el-table-column prop="test" label="介绍" width="400"></el-table-column>
            <el-table-column prop="low" label="底价"></el-table-column>
            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button type="primary" size="mini" @click="showBrandByDiaLog(scope.row)">编辑</el-button>
                <el-button type="danger" @click="deleteInfo(scope.row.id)" size="mini">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-card>
    <el-dialog
        title="创建"
        :visible.sync="dialogVisible"
        width="50%"
    >
      <el-form :model="addForm" :rules="addFormRules" label-width="150px" style="width: 500px">
        <el-form-item label="品牌商名称" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="介绍">
          <el-input v-model="addForm.test"></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-upload
              :limit="addUploadLimit"
              ref="addUpload"
              action=""
              :http-request="addUploadAction"
              :file-list="addForm.img"
              list-type="picture-card"
              :auto-upload="false">
            <i slot="default" class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="底价">
          <el-input v-model="addForm.low" type="number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addBrand">确 定</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="编辑"
        :visible.sync="updateDialogVisible"
        width="50%"
        @close="updateDialogClosed"
    >
      <el-form :model="updateForm" :rules="addFormRules" label-width="150px" style="width: 500px">
        <el-form-item label="品牌商名称" prop="name">
          <el-input v-model="updateForm.name"></el-input>
        </el-form-item>
        <el-form-item label="介绍">
          <el-input v-model="updateForm.test"></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-upload
              :limit="addUploadLimit"
              ref="upload"
              :http-request="updateUploadAction"
              :file-list="updateForm.img"
              action=""
              list-type="picture-card"
              :auto-upload="false"
          >
            <i slot="default" class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="底价">
          <el-input v-model="updateForm.low" type="number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="updateDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateBrand">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Brand',
  data () {
    return {
      tableData: [],
      imgSrc: '',
      brandId: '',
      brandName: '',
      dialogVisible: false,
      updateDialogVisible: false,
      addForm: {
        name: '',
        test: '',
        img: [],
        low: ''
      },
      // 添加=》图片上传限制1个
      addUploadLimit: 1,
      updateForm: {
        id: 0,
        name: '',
        test: '',
        img: [],
        low: 0
      },
      addFormRules: {
        name: [
          {
            required: true,
            message: '品牌商名称不能为空',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created () {
    this.getTableData()
    this.imgSrc = this.$http.defaults.baseURL
  },
  methods: {
    async getTableData () {
      const brandId = Number(this.brandId)
      if (!Number.isNaN(brandId)) {
        const { data: res } = await this.$http.get('/mall/brand', {
          params: {
            'id': brandId,
            'name': this.brandName
          }
        })
        if (res.meta.status === 200) {
          this.tableData = res.data
          console.log(res);
        }
      }
    },
    //删除方法
    async deleteInfo (id) {
      const { data: res } = await this.$http.delete('/mall/brand/delete', { params: { 'id': id } })
      if (res.meta.status === 200) {
        this.$message.success('删除成功')
        this.getTableData()
      } else {
        this.$message.error('删除失败')
      }
    },
    showBrandByDiaLog (brand) {
      //点击编辑按钮  把需要回显的数据加载到dialog上
      this.updateForm.id = brand.id
      this.updateForm.name = brand.name
      this.updateForm.test = brand.test
      this.updateForm.img.push({ url: this.imgSrc + brand.img })
      this.updateForm.low = brand.low
      this.updateDialogVisible = true
    },
    updateDialogClosed () {
      this.updateForm.img.splice(0, 1)
    },
    addBrand () {
      this.$refs['addUpload'].submit()
    },
    updateBrand () {
      this.$refs['upload'].submit()

    },
    async addUploadAction (uploadParams) {
      const low = Number(this.addForm.low)
      if (!Number.isNaN(low)) {
        const formData = new FormData()
        formData.append('file', uploadParams.file)
        formData.append('name', this.addForm.name)
        formData.append('test', this.addForm.test)
        formData.append('low', this.addForm.low)
        const config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        const { data: res } = await this.$http.post('/brand/upload/add', formData, config)
        if (res.meta.status === 200) {
          this.dialogVisible = false
          this.$message.success('添加成功')
          this.getTableData()
        }
      }
    },
    async updateUploadAction (uploadParams) {
      alert(1)
      const low = Number(this.updateForm.low)
      if (!Number.isNaN(low)) {
        const formData = new FormData()
        formData.append('file', uploadParams.file)
        formData.append('id', this.updateForm.id)
        formData.append('name', this.updateForm.name)
        formData.append('test', this.updateForm.test)
        formData.append('low', this.updateForm.low)
        const config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        const { data: res } = await this.$http.post('/brand/upload/update', formData, config)
        if (res.meta.status === 200) {
          this.updateDialogVisible = false
          this.$message.success('更新成功')
          this.getTableData()
        } else {
          this.updateDialogVisible = false
          this.$message.success('更新失败')
        }
      }
    },
    // 导出表格所用
    exportExcel () {
      import('../../plugins/Export2Excel').then(excel => {
        const tHeader = ['品牌商ID', '品牌商名称', '介绍', '底价']
        const filterVal = ['bId', 'name', 'test', 'low']
        excel.export_json_to_excel2(tHeader, this.tableData, filterVal, '品牌制造商信息')
      })

    }
  }
}
</script>

<style scoped>
#box {
  margin: 0 10px 0 10px;
}

#img {
  width: 100px;
  height: 50px;
}
</style>