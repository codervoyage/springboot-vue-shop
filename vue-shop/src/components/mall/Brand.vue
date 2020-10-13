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
            <el-table-column prop="id" label="品牌商ID"></el-table-column>
            <el-table-column prop="name" label="品牌商名称"></el-table-column>
            <el-table-column prop="img" label="品牌商图片">
              <template v-slot="scope">
                <img id="img" :src="getImgUrl(scope.row.img)" />
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
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="介绍">
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-upload
              action="#"
              list-type="picture-card"
              :auto-upload="false">
            <i slot="default" class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="底价">
          <el-input type="number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="创建"
        :visible.sync="updateDialogVisible"
        width="50%"
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
              action="#"
              list-type="picture-card"
              :auto-upload="false">
            <i slot="default" class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="底价">
          <el-input v-model="updateForm.low" type="number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="updateDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateDialogVisible = false">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

export default {
  name: 'Brand',
  data () {
    return {
      tableData: [],
      brandId: '',
      brandName: '',
      dialogVisible: false,
      updateDialogVisible: false,
      addForm: {},
      updateForm: {
        name: '',
        test: '',
        img: '',
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
      },
      imgUrl: require('../../assets/img/mall/WMF.png')
    }
  },
  created () {
    this.getTableData()
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
      this.updateForm.name = brand.name
      this.updateForm.test = brand.test
      this.updateForm.img = brand.img
      this.updateForm.low = brand.low
      this.updateDialogVisible = true
    },//获取图片路径
    getImgUrl (img) {
      return require('@/assets/img/mall/'+img)
    },
    // 导出表格所用
    exportExcel () {
      // 设置当前日期
      let time = new Date()
      let year = time.getFullYear()
      let month = time.getMonth() + 1
      let day = time.getDate()
      let name = year + '' + month + '' + day
      /* generate workbook object from table */
      let xlsxParam = { raw: true } //转换成excel时，使用原始的格式
      /* 从表生成工作簿对象 */
      let wb = XLSX.utils.table_to_book(
          document.querySelector('#table'),  //elementui 表格的id
          xlsxParam
      )
      /* 获取二进制字符串作为输出 */
      var wbout = XLSX.write(wb, {
        bookType: 'xlsx',
        bookSST: true,
        type: 'array'
      })
      try {
        FileSaver.saveAs(
            //Blob 对象表示一个不可变、原始数据的类文件对象。
            //Blob 表示的不一定是JavaScript原生格式的数据。
            //File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
            //返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
            new Blob([wbout], { type: 'application/octet-stream' }),
            //设置导出文件名称
            name + '商品制造商' + '.xlsx'
        )
      } catch (e) {
        if (typeof console !== 'undefined') console.log(e, wbout)
      }
      return wbout
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