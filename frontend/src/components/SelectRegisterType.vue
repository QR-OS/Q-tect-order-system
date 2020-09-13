<!-- SelectRegisterType -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card transition="slide-x-transition" class="mx-auto" max-width="400">
          <v-toolbar color="amber darken-1 white--text" flat>
            <v-card-title>회원가입</v-card-title>
          </v-toolbar>
          <v-spacer />
          <v-card-actions>
            <v-row>
              <v-col align="center">
                <v-btn
                  class="mx-1"
                  x-large
                  depressed
                  outlined
                  color="amber darken-1 text-bold"
                  @click="moveToOwnerRegister"
                >
                  매장 관리자
                </v-btn>
                <v-btn
                  class="mx-1"
                  x-large
                  depressed
                  outlined
                  color="amber darken-1"
                  @click="moveToConsumerRegister"
                >
                  일반 회원
                </v-btn>
              </v-col>
            </v-row>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  methods: {
    moveToOwnerRegister() {
      this.$router.push({
        name: "Register",
        params: { visible: 2 }
      });
    },
    moveToConsumerRegister() {
      this.$router.push({
        name: "Register",
        params: { visible: 1 }
      });
    }
  }
};
</script>

<style></style>
