<template>
  <div id="app">
    <vue-side-bar :minimize="minimized"
                  position="left"
                  ref="sidebar">
      <sidebar-item title="用户查找"
                    icon="ti-search">
        <sidebar-item to="/base_search" title="Base Search link"></sidebar-item>
        <sidebar-item to="page2" title="Second link"/>
        <sidebar-item to="/" title="Third link" />
      </sidebar-item>

      <sidebar-item title="效果反馈"
                    icon="ti-hand-point-up">
        <sidebar-item to="/" title="First link"/>
        <sidebar-item to="page2" title="Second link"/>
        <sidebar-item to="/" title="Third link" />
      </sidebar-item>
      <sidebar-item title="权限管理"
                    icon="ti-lock">
        <sidebar-item to="/" title="First link"/>
        <sidebar-item to="page2" title="Second link"></sidebar-item>
        <sidebar-item to="/" title="Third link" />
      </sidebar-item>
      <sidebar-item title="帮助中心"
                    icon="ti-help">
        <sidebar-item to="page4" title="常见问题 link"></sidebar-item>
      </sidebar-item>

      <sidebar-main-content slot="content">
      </sidebar-main-content>
    </vue-side-bar>
    <div class="main-panel">

      <!--<div>-->
        <!--<button class="btn" style="float:right;" @click="toggleSideBar">Minimize on desktop</button>-->
        <!--<button class="btn" style="float:right;" @click="openMobileMenu">Open mobile menu</button>-->
      <!--</div>-->
      <router-view>
      </router-view>
    </div>
  </div>
</template>

<script>
import SidebarItem from './components/SidebarItem.vue'

export default {
  components: {SidebarItem},
  name: 'app',
  data () {
    return {
      minimized: false,
      mobileOpen: false
    }
  },
  methods: {
    firstLinkClick () {
      alert('Hey you clicked me!')
    },
    toggleSideBar () {
      this.minimized = !this.minimized
    },
    openMobileMenu () {
      this.mobileOpen = !this.mobileOpen
    }
  }
}
</script>

<style lang="scss">
  .sidebar-open {
    .main-panel{

    }
  }

</style>
