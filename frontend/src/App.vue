<template>
  <v-app>
    <v-app-bar app color="amber darken-1">
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
            <v-list-item @click="moveToPage('OrderHistory')">주문내역</v-list-item>
          </v-list>
        </v-card>
      </v-menu>
    </v-app-bar>
    <v-main>
      <v-fade-transition hide-on-leave>
        <router-view></router-view>
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
    return {};
  },
  computed: {
    ...mapState("auth", ["accessToken", "user"]),
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
