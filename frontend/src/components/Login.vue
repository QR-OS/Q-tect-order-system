<!-- Login.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-card class="mx-auto" max-width="344" outlined>
      <v-layout column>
        <v-card-text>
          <v-text-field v-model="user_id" label="ID"/>
          <v-text-field v-model="user_pw" label="Password" type="password"/>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn
            type="submit"
            outlined
            >회원가입</v-btn
          >
          <v-btn
            type="submit"
            outlined
            >ID/PW 찾기</v-btn
          >
          <v-btn type="submit" outlined>
            로그인
          </v-btn>
        </v-card-actions>
      </v-layout>
    </v-card>
  </v-container>
</template>

<script>
export default {
  props: ["propsdata"],
  data: () => ({}),
  methods: {},
};
</script>

<style></style>
