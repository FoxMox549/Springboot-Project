import vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
vue.use(Vuex)

export default new Vuex.Store({
    state: {
        menu: []
    },
    mutations: {
        setMenu(state, menuList) {
            state.menu = menuList
        },
        /*setRouter(state, menuList) {
            addRouters(menuList)
        }*/
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    plugins: [createPersistedState()],
})