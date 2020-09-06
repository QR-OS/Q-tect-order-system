<template>
  <v-app>
    <v-container>
      <v-app-bar app color="indigo">
        <v-toolbar-title>QR코드주문시스템^^!</v-toolbar-title>
        <v-spacer />
        <v-btn @click="MoveToLogin">로그인</v-btn>
      </v-app-bar>
      <v-main> <router-view></router-view></v-main>
      <v-footer color="indigo" app>
        <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
      </v-footer>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data() {
    return {};
  },
  methods: {
    MoveToLogin() {
      this.$router.push({
        name: "Login"
      });
    }
  }
};
</script>
