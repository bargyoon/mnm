import Vue from 'vue';
import Router from 'vue-router';
import MainHeader from '../layouts/MnmHeader.vue';
import MainFooter from '../layouts/MnmFooter.vue';

Vue.use(Router);

export const routes = [
    {
        name: 'Index',
        path: '/index',
        redirect: '/'
    },
    {
        name: 'Root',
        path: '/',
        components: {
            default: () => import('../pages/Index.vue'),
            header: MainHeader,
            footer: MainFooter,
        }
    },
    {
        name: 'MentorBoard',
        path: '/board/mentor',
        components: {
            default: () => import('../pages/board/MentorBoard.vue'),
            header: MainHeader,
            footer: MainFooter,
        }
    },
    {
        name: 'BoardForm',
        path: '/board/form',
        components: {
            default: () => import('../pages/board/BoardForm.vue'),
            header: MainHeader,
            footer: MainFooter,
        }
    },
    {
        name: 'BoardDetail',
        path: '/board/detail/:id',
        components: {
            default: () => import('../pages/board/BoardDetail.vue'),
            header: MainHeader,
            footer: MainFooter,
        },
    },
    {
        name: 'BoardDetailId',
        path: '/board/detail',
        redirect: '/board/mentor'
    },
    {
        name: 'BoardModify',
        path: '/board/modify/:id',
        components: {
            default: () => import('../pages/board/BoardForm.vue'),
            header: MainHeader,
            footer: MainFooter,
        },
    },


];

const router = new Router({
    mode: 'history',
    base: '/view/',
    routes,
});

export default router;







