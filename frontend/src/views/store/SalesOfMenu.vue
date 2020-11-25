<template>
  <!-- Load with base style -->
  <v-container>
    <v-row>
      <v-col>
        <v-alert v-if="!!errorMsg">{{ errorMsg }}</v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="11" sm="5">
        <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="dates"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="dateRangeText"
              label="기간"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="dates" :max="currentDate" range>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">
              Cancel
            </v-btn>
            <v-btn
              text
              color="primary"
              @click="
                () => {
                  $refs.menu.save(dates);
                  this.updateData();
                }
              "
            >
              OK
            </v-btn>
          </v-date-picker>
        </v-menu>
      </v-col>
    </v-row>
    <v-row v-if="!!isLoading">
      <v-alert outlined type="warning" prominent border="left">
        로딩중입니다......!
      </v-alert>
    </v-row>
    <v-row v-else>
      <v-col><div id="pieChart"></div> </v-col>
    </v-row>
  </v-container>
</template>

<script>
import moment from "moment";
import axios from "axios";
import bb, { pie } from "billboard.js";
import "billboard.js/dist/billboard.css";

export default {
  data() {
    return {
      errorMsg: "",
      storeId: "",
      dates: [moment().format("YYYY-MM-DD"), moment().format("YYYY-MM-DD")],
      currentDate: moment().format("YYYY-MM-DD"),
      menu: false,
      data: [],
      chart: bb.generate({
        data: {
          x: "x",
          columns: [["x"], []],
          types: {
            data1: pie(),
          },
        },
        axis: {
          x: {
            type: "timeseries",
            tick: {
              format: "%Y-%m-%d",
            },
          },
        },
        bindto: "#pieChart",
      }),
      isLoading: false,
    };
  },
  async created() {
    try {
      const storeInfo = await axios.get(
        "/store/" + this.$store.state.auth.user.user_id
      );
      this.storeId = storeInfo.data.store_id;
      await this.updateData();
    } catch (error) {
      this.errorMsg = error.response.message;
    }
  },
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
  methods: {
    createChart() {
      this.chart = bb.generate({
        data: {
          columns: this.data,
          type: "pie",
          onclick: function(d, i) {
            console.log("onclick", d, i);
          },
          onover: function(d, i) {
            console.log("onover", d, i);
          },
          onout: function(d, i) {
            console.log("onout", d, i);
          },
        },
        bindto: "#pieChart",
      });
    },
    async updateData() {
      if (moment(this.dates[0]) > moment(this.dates[1])) {
        [this.dates[0], this.dates[1]] = [this.dates[1], this.dates[0]];
      }
      let startDate = moment(this.dates[0]).format("YYYY-MM-DD");
      let endDate = moment(this.dates[1])
        .add(1, "days")
        .format("YYYY-MM-DD");
      const res = await axios.get(
        `/statistic/menu/${this.storeId}/?startDate=${startDate}&endDate=${endDate}`
      );

      this.data = [["x"]];

      for (let item of res.data) {
        this.data.push([item.product_name, item.order_quantity]);
      }
      this.createChart();
    },
  },
};
</script>
