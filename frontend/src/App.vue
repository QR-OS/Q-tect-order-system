<template>
  <v-app>
    <v-app-bar app color="amber darken-1">
      <v-toolbar-title class="text-none">
        <v-btn text large @click="moveToHome">QR코드 주문 시스템^^!</v-btn>
      </v-toolbar-title>
      <v-spacer />
      <v-btn
        v-if="!this.$store.state.accessToken"
        text
        dark
        @click="moveToLogin"
      >
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
              <v-list-item-action>
                <v-btn text @click="logout">로그아웃</v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <v-list>
            <v-list-item to="/mypage">마이페이지</v-list-item>
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
import { mapActions } from "vuex";

export default {
  name: "App",
  data() {
    return {};
  },
  methods: {
    ...mapActions({
      getLogout: "logout"
    }),
    async logout() {
      this.getLogout();
      if (this.$route.name != "Home") {
        this.$router.push({ name: "Home" });
      }
    },
    moveToLogin() {
      if (this.$route.name != "Login") {
        this.$router.push({
          name: "Login"
        });
      }
    },
    moveToHome() {
      if (this.$route.path != "/") {
        this.$router.push({ name: "Home" });
      }
    }
  }
};
</script>
