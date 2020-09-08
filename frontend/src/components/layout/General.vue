<template>
  <v-app>
    <v-container>
      <v-app-bar app color="amber darken-1">
        <v-toolbar-title class="text-none">
          <v-btn text large @click="moveToHome">QR코드 주문 시스템^^!</v-btn>
        </v-toolbar-title>
        <v-spacer />
        <v-btn @click="moveToLogin">로그인</v-btn>
      </v-app-bar>
      <v-content>
        <v-fade-transition hide-on-leave>
          <router-view></router-view>
        </v-fade-transition>
      </v-content>
      <v-footer color="yellow lighten-4" app>
        <span class="black--text">&copy; {{ new Date().getFullYear() }}</span>
      </v-footer>
    </v-container>
  </v-app>
</template>

<script>
export default {

}
</script>