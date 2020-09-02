<!-- Login.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-card class="mx-auto" max-width="344" outlined>
      <v-layout column>
        <v-card-text>
          <v-text-field v-model="user_id" label="ID" />
          <v-text-field v-model="user_pw" label="Password" type="password" />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <router-link to="/selectregistertype">회원가입</router-link>
          <router-link to="/">ID/PW 찾기</router-link>
          <v-btn type="submit" outlined @click="login">
            로그인
          </v-btn>
        </v-card-actions>
      </v-layout>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  props: ["propsdata"],
  data() {
    return { user_id: "", user_pw: "" };
  },
  methods: {
    login() {
      const res = axios.post("/login", {
        user_id: this.user_id,
        user_pw: this.user_pw,
      });
      console.log(res);
    },
  },
};
</script>

<style></style>
