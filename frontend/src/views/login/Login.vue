<!-- Login.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="400" outlined>
          <v-container pa-md-4 pa-xs-2>
            <v-toolbar dense flat>
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
                dense
                outlined
                @input="removeError"
              />
              <v-text-field
                v-model="user.userPw"
                dense
                outlined
                name="password"
                prepend-icon="mdi-lock"
                label="Password"
                type="password"
                :error="!!errorMsg"
                :error-messages="errorMsg"
                @input="removeError"
                @keyup.enter="login"
              />
            </v-card-text>
            <v-spacer />
            <v-card-actions>
              <v-btn text @click="moveToPage('SelectRegisterType')"
                >회원가입</v-btn
              >
              <v-btn text @click="moveToPage('FindIdPw')">
                ID/PW 찾기
              </v-btn>
              <v-spacer />
              <v-btn
                class="white--text"
                type="submit"
                color="amber darken-1"
                depressed
                @click="login"
              >
                로그인
              </v-btn>
            </v-card-actions>
          </v-container>
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
        userPw: "",
      },
      errorMsg: "",
    };
  },
  methods: {
    ...mapActions({
      updateToken: "auth/login",
      managerAuth: "auth/managerAuth",
    }),
    removeError() {
      this.errorMsg = "";
    },
    async login() {
      if (!this.user.userId || !this.user.userPw) {
        this.errorMsg = "아이디와 비밀번호를 입력하세요.";
        return;
      }
      try {
        const res = await axios.post("/login", {
          user_id: this.user.userId,
          user_pw: this.user.userPw,
        });
        this.updateToken(res.data.accessToken);
      } catch (error) {
        this.errorMsg = error.response.data.message;
      }
      const userInfo = await axios.get("/user/" + this.user.userId);
      if (userInfo.data.status === 2) {
        try {
          const storeInfo = await axios.get("/store/" + this.user.userId);
          this.managerAuth({
            store_name: storeInfo.data.store_name,
            store_id: storeInfo.data.store_id,
          });
          this.moveToPage("Home");
        } catch (error) {
          this.errorMsg = error.response.data.message;
        }
      } else this.moveToPage("Home");
    },
    moveToPage(next) {
      this.$router.push({ name: next });
    },
  },
};
</script>
