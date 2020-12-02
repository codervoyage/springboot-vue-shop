<template>
    <div>
        <el-card id="box">
            <el-row :gutter="20" style="margin-bottom: 10px">
                <el-col :span="4" :offset="1">
                    <el-input
                            @clear=""
                            v-model="user_id"
                            placeholder="请输入用户id"
                            clearable>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-input
                            @clear=""
                            v-model="goods_id"
                            placeholder="请输入商品id"
                            clearable>
                    </el-input>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="el-icon-search" @click="">查找</el-button>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="el-icon-upload2" @click="">导出</el-button>
                </el-col>
            </el-row>
            <el-row :gutter="20">
                <el-col :span="22" :offset="1">
                    <el-table :data="tableData" stripe border>

                    </el-table>
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
        user_id: undefined,
        goods_id: undefined,
        tableData: []
      }
    }
  }
</script>

<style scoped>
    #box {
        margin: 0 10px 0 10px;
    }
</style>