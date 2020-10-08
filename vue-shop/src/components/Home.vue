<template>
  <el-container id="ce">
    <!--========侧边栏===========-->
    <el-aside :width="isCollapse ? '64px' : '210px' ">
      <el-row class="tac">
        <el-menu
            background-color="#304156"
            text-color="#fff"

            router
            active-text-color="#ffd04b"
            :collapse="isCollapse"
            :collapse-transition="false"
            :default-active="this.$route.path"

        >
          <div v-for="item in menuList">
            <div v-if="item.id===1">
              <el-menu-item :key='item.id' :index="item.path">
                <template slot="title">
                  <span>{{ item.menu }}</span>
                </template>
              </el-menu-item>
            </div>
            <div v-else>
              <el-submenu :key="item.id" :index="item.id+''">
                <template slot="title">
                  <span>{{ item.menu }}</span>
                </template>
                <el-menu-item v-for="subItem in item.children" :key='subItem.id' :index="subItem.path">
                  <template slot="title">
                    <span>{{ subItem.menu }}</span>
                  </template>
                </el-menu-item>
              </el-submenu>
            </div>
          </div>

        </el-menu>
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
  name: 'Home',
  data () {
    return {
      /*侧边栏数据*/
      menuList: [],
      /* 左侧图标 */
      icons: ['el-icon-user-solid', 'el-icon-s-operation', 'el-icon-s-goods', 'el-icon-s-order', 'el-icon-notebook-2'],
      /* 控制菜单栏折叠的开关 */
      isCollapse: false
    }
  },
  created () {
    // 页面加载时 自动运行此方法
    this.getMenuList()
  },
  methods: {
    /* 获取菜单栏数据 */
    async getMenuList () {
      const { data } = await this.$http.get('getMenuList')
      this.menuList = data.data
      console.log(this.menuList)
    }
  }
}
</script>

<style scoped>
#ce {
  height: 100%;
}

.el-aside {
  background-color: #304156;
}

.el-menu {
  border-right: none;
}

</style>