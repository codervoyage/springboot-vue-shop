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
            :collapse-transition="true"
            :default-active="this.$route.path"
            unique-opened
        >
          <template v-for="item in menuList">
            <el-menu-item v-if="item.id===1" :key='item.id' :index="item.path">
              <template slot="title">
                <i class="el-icon-house"></i>
                <span>{{ item.menu }}</span>
              </template>
            </el-menu-item>
            <el-submenu v-else :key="item.id" :index="item.id+''">
              <template slot="title">
                <i class="el-icon-s-data"></i>
                <span>{{ item.menu }}</span>
              </template>
              <el-menu-item v-for="subItem in item.children" :key='subItem.id' :index="subItem.path">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>{{ subItem.menu }}</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </template>

        </el-menu>
      </el-row>
    </el-aside>
    <!--=============主题部分========-->
    <el-container>
      <!--=========头部导航条=========-->
      <el-header>
        <div id="cardHeader">
          <div id="cardHeaderLeft">
            <i @click="isCollapseSwitch" id="lefticon" style="font-size:30px" :class="icon"></i>
            <el-breadcrumb>
              <el-breadcrumb-item>首页</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div id="cardHeaderRight">
            <el-tooltip class="item" effect="dark" content="全屏" placement="bottom">
              <i class="el-icon-full-screen" @click="screenfullClick" id="righticon" style="font-size:30px"></i>
            </el-tooltip>
            <el-dropdown>
              <span class="el-dropdown-link">
                <img id="cardHeaderRightImg" src="../assets/topRightLogo.gif"/>
                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>首页</el-dropdown-item>
                <el-dropdown-item>GitHub</el-dropdown-item>
                <el-dropdown-item>修改密码</el-dropdown-item>
                <el-dropdown-item>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>


          </div>
        </div>

      </el-header>
      <!--========主界面============-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import screenfull from 'screenfull'

export default {
  name: 'Home',
  data () {
    return {
      /*侧边栏数据*/
      menuList: [],
      /* 控制菜单栏折叠的开关 */
      isCollapse: false,
      icon: 'el-icon-s-fold',
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
    },//控制左侧菜单栏是否折叠
    isCollapseSwitch () {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.icon = 'el-icon-s-unfold'
      } else {
        this.icon = 'el-icon-s-fold'
      }
    },
    //点击进入全屏
    screenfullClick () {
      screenfull.request()
    }
  }
}
</script>

<style scoped>
#cardHeaderRightImg {
  width: 50px;
  height: 50px;
}

#cardHeaderLeft {
  display: flex;
  align-items: center;
  //border: 1px solid rebeccapurple;
}

#cardHeaderRight {
  display: flex;
  align-items: center;
  //border: 1px solid rebeccapurple;
}

#cardHeader {
  padding: 10px;
  display: flex;
  justify-content: space-between;
}

#righticon {
  cursor: pointer;
  margin-right: 20px;
}

#lefticon {
  cursor: pointer;
  margin-right: 10px;

}

.el-header {
  padding: 0;
}

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