<template>
  <v-container>
    <v-alert v-if="!!errorMsg">{{ errorMsg }}</v-alert>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="orderList"
      :single-select="singleSelect"
      item-key="name"
      show-select
      class="elevation-1"
    >
    </v-data-table>
  </v-container>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  data() {
    return {
      singleSelect: false,
      selected: [],
      headers: [
        {
          text: "주문 방법",
          align: "start",
          sortable: false,
          value: "orderType",
        },
        { text: "접수시간", value: "order_time" },
        { text: "완료 예정 시간", value: "book_time" },
        { text: "총 금액", value: "total_price" },
        { text: "결제방법", value: "pay_type" },
        { text: "완료여부", value: "order_state" },
      ],
      orderList: [],
      errorMsg: "",
    };
  },
  async created() {
    try {
      const storeInfo = await axios.get(
        "/store/" + this.$store.state.auth.user.user_id
      );
      const res = await axios.get("/orders/" + storeInfo.data.store_id);
      this.orderList = res.data;
      if (
        this.orderList.book_time === null ||
        typeof this.orderList.book_time !== "undefined"
      ) {
        this.orderList.book_time = moment().format("YYYY-MM-DD HH:mm");
      }
    } catch (error) {
      this.errorMsg = error.response.message;
    }
    //this.orderList = 
  },
};
</script>
