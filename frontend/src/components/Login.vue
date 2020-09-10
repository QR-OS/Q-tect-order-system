<!-- Login.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card transition="slide-x-transition" class="mx-auto" max-width="400">
          <v-toolbar color="amber darken-1" flat>
            <v-card-title>로그인</v-card-title>
          </v-toolbar>
          <v-card-text>
            <v-text-field
              v-model="user.userId"
              name="login"
              prepend-icon="mdi-account"
              label="ID"
              type="text"
              :error="!!errorMsg"
            />
            <v-text-field
              v-model="user.userPw"
              name="password"
              prepend-icon="mdi-lock"
              label="Password"
              type="password"
              :error="!!errorMsg"
              :error-messages="errorMsg"
              @keyup.enter="login"
            />
          </v-card-text>
          <v-spacer />
          <v-card-actions>
            <v-btn color="orange" text @click="moveToRegister">회원가입</v-btn>
            <v-btn color="orange" text @click="moveToFindIdPw">
              ID/PW 찾기
            </v-btn>
            <v-spacer />
            <v-btn type="submit" color="orange" text @click="login">
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
import { mapActions } from "vuex";

export default {
  data() {
    return {
      user: {
        userId: "",
        userPw: ""
      },
      errorMsg: ""
    };
  },
  methods: {
    ...mapActions({
      updateToken: "login"
    }),
    async login() {
      if (!this.user.userId || !this.user.userPw) {
        this.errorMsg = "아이디와 비밀번호를 입력하세요.";
        return;
      }
      try {
        const res = await axios.post("/login", {
          user_id: this.user.userId,
          user_pw: this.user.userPw
        });
        this.updateToken(res.data.accessToken);
        //this.$store.dispatch("updateToken", res.data.accessToken);
        this.$router.push({
          name: "Home"
        });
      } catch (error) {
        this.errorMsg = error.response.data.message;
      }
    },
    moveToRegister() {
      this.$router.push({
        name: "SelectRegisterType"
      });
    },
    moveToFindIdPw() {
      this.$router.push({
        name: "/"
      });
    }
  }
};
</script>
