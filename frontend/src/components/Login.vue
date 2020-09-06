<!-- Login.Vue -->

<template>
  <v-container class="fill-height aligh-center d-flex justify-center" fluid>
    <div class="fill-height aligh-center d-flex justify-center">
      <v-card class="mx-auto" outlined>
        <v-layout column>
          <v-flex xs12 sm4>
            <v-card-text>
              <v-text-field v-model="user_id" label="ID" />
              <v-text-field
                v-model="user_pw"
                label="Password"
                type="password"
              />
            </v-card-text>
          </v-flex>
          <v-spacer />
          <v-flex xs12 sm4>
            <v-card-actions>
              <router-link to="/selectregistertype">회원가입</router-link>
              <v-spacer />
              <router-link to="/">ID/PW 찾기</router-link>
              <v-spacer />
              <v-btn type="submit" outlined @click="login">
                로그인
              </v-btn>
            </v-card-actions>
          </v-flex>
        </v-layout>
      </v-card>
    </div>
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
