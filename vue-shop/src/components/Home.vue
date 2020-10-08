<template>
  <el-container id="ce">
    <!--========侧边栏===========-->
    <el-aside width="210px">
      <el-row class="tac">
        <el-col :span="12">
          <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#304156"
              text-color="#fff"
              router="router"
              active-text-color="#ffd04b">
          </el-menu>
            <template v-for="(item,key) in menuList" slot="title">
              <el-submenu index="1">

              </el-submenu>

            </template>
        </el-col>
      </el-row>
    </el-aside>
    <!--=============主题部分========-->
    <el-container>
      <!--=========头部导航条=========-->
      <el-header>Header</el-header>
      <!--========主界面============-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data(){
    return {
      menuList:[]
    }
  },
  created() {
    // 页面加载时 自动运行此方法
    getMenuList();
  },
  methods: {
    /* 获取菜单栏数据 */
    async getMenuList() {
      const {data} = await this.$http.get('menu')
      this.menuList = data.data
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
#ce{
  height: 100%;
}
.el-aside{
  background-color: #304156;
}
</style>