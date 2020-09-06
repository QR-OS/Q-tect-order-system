<!-- Login.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="400" outlined>
          <v-card-title>로그인</v-card-title>
          <v-card-text>
            <v-text-field v-model="user_id" label="ID" />
            <v-text-field v-model="user_pw" label="Password" type="password" />
          </v-card-text>
          <v-spacer />
          <v-card-actions>
            <router-link to="/selectregistertype">회원가입</router-link>
            <v-spacer />
            <router-link to="/">ID/PW 찾기</router-link>
            <v-spacer />
            <v-btn type="submit" outlined @click="login">
              로그인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
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
        user_pw: this.user_pw
      });
      console.log(res);
    }
  }
};
</script>
