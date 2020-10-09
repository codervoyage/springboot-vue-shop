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
              <el-menu-item @click="menuChange(item.menu,subItem.menu,subItem.path)" v-for="subItem in item.children"
                            :key='subItem.id' :index="subItem.path">
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
      <el-header style="height: auto">
        <div id="cardHeader">
          <div id="cardHeaderLeft">
            <i @click="isCollapseSwitch" id="lefticon" style="font-size:30px" :class="icon"></i>
            <el-breadcrumb
                id="cardHeaderLeftBreadCrumb"
                separator-class="el-icon-arrow-right"

            >
              <div>
                <el-breadcrumb-item>首页</el-breadcrumb-item>
              </div>
              <div v-if="this.$route.path !== '/dashboard'">
                <el-breadcrumb-item></el-breadcrumb-item>
                <el-breadcrumb-item>{{ breadcrumbFirst }}</el-breadcrumb-item>
                <el-breadcrumb-item>{{ breadcrumbSecond }}</el-breadcrumb-item>
              </div>
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
                <el-dropdown-item v-on:click.native="dropdownMenuGoHome">首页</el-dropdown-item>
                <el-dropdown-item v-on:click.native="dropdownMenuGoGitHub">GitHub</el-dropdown-item>
                <el-dropdown-item v-on:click.native="dropdownMenuGoPwd">修改密码</el-dropdown-item>
                <el-dropdown-item v-on:click.native="dropdownMenuGoLogin">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>


          </div>
        </div>
        <!--<el-tabs style="margin-left: 10px;"
                 v-model="editableTabsValue"
                 type="card"
                 closable
                 @tab-remove="removeTab"
                 @tab-click="tabsClick"
        >
          <el-tab-pane
              v-for="(item, index) in editableTabs"
              :key="item.name"
              :label="item.title"
              :name="item.name"
          >
          </el-tab-pane>
        </el-tabs>-->
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
      breadcrumbFirst: '',
      breadcrumbSecond: '',
      /*// Tabs标签页
      editableTabsValue: '1',
      editableTabs: [{
        title: '首页',
        name: '1',
        currentPath:'/dashboard'
      }],
      tabIndex: 1,
      flag: false*/

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
    },
    // 点击回到主页
    dropdownMenuGoHome () {
      if (this.$route.path !== '/dashboard') {
        this.$router.push('/home')
      }
    },
    // 点击去往github项目界面
    dropdownMenuGoGitHub () {
      window.location.href = 'https://github.com/codervoyage/springboot-vue-shop'
    },
    dropdownMenuGoPwd () {
      this.$router.push('/profile/password')
      this.breadcrumbFirst = '信息管理'
      this.breadcrumbSecond = '密码修改'
    },
    // 退出登录
    dropdownMenuGoLogin () {
      this.$router.push('/login')
    },
    // 通过ui导航组件的点击事件传过来面包屑导航条所需要的（用户管理，会员管理）并赋值到属性上
    menuChange (first, second, secondPath) {
      this.breadcrumbFirst = first
      this.breadcrumbSecond = second
      this.currentPath = secondPath
      /*//点击二级导航菜单时添加tab标签页（）
      this.addTab(second, secondPath)*/
    },
    /*// tab标签页添加方法
    addTab (tabName, currentPath) {
      this.editableTabs.forEach(item => {
        if (item.title === tabName) {
          //如果要添加的tab已有，则改变标记
          this.flag = true
        }
      })// 当标记为false可以add
      if (!this.flag) {
        let newTabName = ++this.tabIndex + ''
        this.editableTabs.push({
          title: tabName,
          name: newTabName,
          currentPath: currentPath
        })
        this.editableTabsValue = newTabName
      }
    },
    //tabs标签页删除方法
    removeTab (targetName) {
      if (targetName !== '1') {
        let tabs = this.editableTabs
        let activeName = this.editableTabsValue
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1]
              if (nextTab) {
                activeName = nextTab.name
              }
            }
          })
        }
        this.editableTabsValue = activeName
        this.editableTabs = tabs.filter(tab => tab.name !== targetName)
        this.$router.push('/dashboard')
      } else {
        this.$message.info('想关闭首页请联系管理员')
      }

    },//tabs标签页点击事件
    tabsClick (tab) {
      this.editableTabs.forEach(item => {
        if (item.title === tab.label) {
          if (this.$route.path !== item.currentPath) {
            this.$router.push(item.currentPath)
          }
        }
      })
    }*/
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
}

#cardHeaderLeftBreadCrumb {
  display: flex;
  justify-content: center;
}

#cardHeaderRight {
  display: flex;
  align-items: center;
}

#cardHeader {
  padding-left: 10px;
  padding-top: 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
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

.el-main {
  padding: 0;
}
</style>