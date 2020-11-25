<!-- SelectRegisterType -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-scroll-x-reverse-transition>
          <v-card v-show="show" outlined class="mx-auto py-4" max-width="400">
            <v-toolbar dense flat>
              <v-card-title>회원가입</v-card-title>
            </v-toolbar>
            <v-spacer />
            <v-card-actions class="px-15 py-8">
              <v-row>
                <v-col align="center">
                  <v-btn
                    class="mx-1"
                    depressed
                    outlined
                    @click="moveToRegister(2)"
                  >
                    매장 관리자
                  </v-btn>
                </v-col>
                <v-spacer />
                <v-col align="center">
                  <v-btn
                    class="mx-1"
                    depressed
                    outlined
                    @click="moveToRegister(1)"
                  >
                    일반 회원
                  </v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </v-card>
        </v-scroll-x-reverse-transition>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "SelectRegisterType",
  data() {
    return {
      show: false,
    };
  },
  mounted() {
    this.show = true;
  },
  methods: {
    moveToRegister(params) {
      this.$router.push("/register?regType=" + params);
    },
  },
};
</script>

<style></style>
