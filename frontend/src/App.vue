<template>
  <v-app>
    <v-navigation-drawer
      v-if="!!storeInfo && Object.keys(storeInfo).length !== 0"
      app
      clipped
      v-model="drawer"
    >
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="amber--text text--darken-4"
        >
          <v-list-item @click="moveToPage('StoreInfo')">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>매장 정보</v-list-item-title>
          </v-list-item>

          <v-list-item @click="moveToPage('Product')">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-edit-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title>메뉴/상품 관리</v-list-item-title>
          </v-list-item>
          <v-list-group prepend-icon="mdi-database">
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title>데이터 집계</v-list-item-title>
              </v-list-item-content>
            </template>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>매출 현황</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>메뉴별</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>시간별</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
          <v-list-group prepend-icon="mdi-credit-card-multiple-outline">
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title>주문 현황</v-list-item-title>
              </v-list-item-content>
            </template>
            <v-list-item @click="moveToPage('StoreOrderManage')">
              <v-list-item-content>
                <v-list-item-title>실시간</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item @click="moveToPage('StoreCompletedOrderManage')">
              <v-list-item-content>
                <v-list-item-title>완료, 취소</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar clipped-left app color="amber darken-1">
      <v-app-bar-nav-icon
        v-if="!!storeInfo && Object.keys(storeInfo).length !== 0"
        @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-toolbar-title class="text-none">
        <v-btn text large @click="moveToPage('Home')">Q-tect Order</v-btn>
      </v-toolbar-title>
      <v-spacer />
      <v-btn v-if="!accessToken" text dark @click="moveToPage('Login')">
        로그인
      </v-btn>
      <v-menu v-else offset-y :nudge-width="200">
        <template v-slot:activator="{ on }">
          <v-btn text dark v-on="on">
            <v-icon>mdi-account-circle</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  Hello, {{ user.user_id }}
                </v-list-item-title>
                <v-list-item-subtitle></v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn text @click="logout">로그아웃</v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <v-list>
            <v-list-item @click="moveToPage('CheckPw')">마이페이지</v-list-item>
            <v-list-item @click="moveToPage('OrderHistory')"
              >주문내역</v-list-item
            >
          </v-list>
        </v-card>
      </v-menu>
    </v-app-bar>

    <v-main>
      <v-fade-transition hide-on-leave>
        <router-view :key="$route.fullPath"></router-view>
      </v-fade-transition>
    </v-main>
    <v-footer color="yellow lighten-4" app>
      <span class="black--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "App",
  data() {
    return { drawer: false, group: null };
  },
  computed: {
    ...mapState("auth", ["accessToken", "user", "storeInfo"]),
  },
  methods: {
    ...mapActions({
      getLogout: "auth/logout",
    }),
    async logout() {
      this.getLogout();
      this.moveToPage("Home");
    },
    moveToPage(next) {
      if (this.$route.name != next) {
        this.$router.push({ name: next });
      }
    },
  },
};
</script>
