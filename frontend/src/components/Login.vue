<!-- Login.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="400">
          <v-toolbar
            color="amber darken-1"
            flat
          >
            <v-card-title>로그인</v-card-title>
          </v-toolbar>
          <v-card-text>
            <v-text-field v-model="userId" label="ID" />
            <v-text-field v-model="userPw" label="Password" type="password" />
          </v-card-text>
          <v-spacer />
          <v-card-actions>
            <v-btn @click="moveToRegister" color="orange" text>회원가입</v-btn>
            <v-btn @click="moveToFindIdPw" color="orange" text
              >ID/PW 찾기</v-btn
            >
            <v-spacer />
            <v-btn type="submit" @click="login" color="orange" text>
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
    return { userId: "", userPw: "" };
  },
  methods: {
    login() {
      const res = axios.post("/login", {
        user_id: this.userId,
        user_pw: this.userPw,
      });
      console.log(res);
    },
    moveToRegister() {
      this.$router.push({
        name: "SelectRegisterType",
      });
    },
    moveToFindIdPw() {
      this.$router.push({
        name: "/",
      });
    },
  },
};
</script>
