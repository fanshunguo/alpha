import VueSideBar from './components/VueSideBar.vue'
import SidebarItem from './components/SidebarItem.vue'
import SideBarMainContent from './components/SidebarMainContent.vue'
import Loading from './components/Loading.vue'
import CollapsiblePanel from './components/CollapsiblePanel.vue'
import MainSearch from './components/MainSearch.vue'
import AccountPanel from './components/AccountInfoPanel.vue'

const SideBar = {
  install (Vue) {
    Vue.component('vue-side-bar', VueSideBar)
    Vue.component('sidebar-item', SidebarItem)
    Vue.component('sidebar-main-content', SideBarMainContent)
    Vue.component('loading', Loading)
    Vue.component('collapse', CollapsiblePanel)
    Vue.component('main-search', MainSearch)
    Vue.component('account-panel', AccountPanel)
  }
}

// Automatic installation if Vue has been added to the global scope.
if (typeof window !== 'undefined' && window.Vue) {
  window.VueSideBar = SideBar
  window.Vue.use(SideBar)
}

export default SideBar
export {
  VueSideBar,
  SidebarItem,
  SideBarMainContent
}
