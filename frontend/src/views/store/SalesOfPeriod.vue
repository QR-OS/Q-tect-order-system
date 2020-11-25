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
          :return-value.sync="date"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="date"
              label="기간"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-if="!!month"
            v-model="date"
            :max="currentDate"
            type="month"
            no-title
            scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">
              Cancel
            </v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(date)">
              OK
            </v-btn>
          </v-date-picker>
          <v-date-picker
            v-else
            :max="currentDate"
            v-model="date"
            no-title
            scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">
              Cancel
            </v-btn>
            <v-btn
              text
              color="primary"
              @click="
                () => {
                  $refs.menu.save(date);
                  this.updateData();
                }
              "
            >
              OK
            </v-btn>
          </v-date-picker>
        </v-menu>
      </v-col>
      <v-col col="12" md="1" align="end">
        <v-btn
          :disabled="!!day"
          class="white--text"
          color="amber darken-1"
          @click="selectType(0)"
        >
          일간
        </v-btn>
      </v-col>
      <v-col col="12" md="1" align="end">
        <v-btn
          :disabled="!!week"
          class="white--text"
          color="amber darken-1"
          @click="selectType(1)"
        >
          주간
        </v-btn>
      </v-col>
      <v-col col="12" md="1" align="end">
        <v-btn
          :disabled="!!month"
          class="white--text"
          color="amber darken-1"
          @click="selectType(2)"
        >
          월간
        </v-btn>
      </v-col>
    </v-row>
    <v-row v-if="!!isLoading">
      <v-alert outlined type="warning" prominent border="left">
        로딩중입니다......!
      </v-alert>
    </v-row>
    <v-row v-else>
      <v-col><div id="areaRangeChart"></div> </v-col>
    </v-row>
  </v-container>
</template>

<script>
import moment from "moment";
import axios from "axios";
import bb, { areaLineRange } from "billboard.js";
import "billboard.js/dist/billboard.css";

export default {
  data() {
    return {
      errorMsg: "",
      storeId: "",
      day: true,
      week: false,
      month: false,
      date: moment().format("YYYY-MM-DD"),
      currentDate: moment().format("YYYY-MM-DD"),
      menu: false,
      data: [],
      chart: bb.generate({
        data: {
          x: "x",
          columns: [["x"], []],
          types: {
            data1: areaLineRange(),
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
        bindto: "#areaRangeChart",
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
  methods: {
    createChart() {
      this.chart = bb.generate({
        data: {
          x: "x",
          columns: this.data,
          types: {
            data1: areaLineRange(), // for ESM specify as: areaLineRange()
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
        bindto: "#areaRangeChart",
      });
    },
    async updateData() {
      const addType = this.day ? "days" : this.week ? "weeks" : "months";
      this.date = moment(this.date).format("YYYY-MM-DD");
      let startDate = moment(this.date)
        .clone()
        .subtract(15, addType)
        .format("YYYY-MM-DD");
      if (this.month) {
        startDate = moment(startDate)
          .add(1, "months")
          .startOf("month")
          .format("YYYY-MM-DD");
      }
      let endDate = moment(this.date)
        .add(1, addType)
        .format("YYYY-MM-DD");
      const res = await axios.get(
        `/statistic/sale/${this.storeId}/?startDate=${startDate}&endDate=${endDate}`
      );
      if (this.month) startDate = moment(startDate);
      else {
        startDate = moment(
          moment(startDate)
            .add(1, "days")
            .format("YYYY-MM-DD")
        );
      }
      endDate = moment(endDate);
      this.data = [["x"], ["매출액"]];
      let idx = 0;
      let now;
      for (now = startDate; now < endDate && now <= moment(this.date); ) {
        let price = 0;
        const last = moment(
          now
            .clone()
            .add(1, addType)
            .format("YYYY-MM-DD")
        );
        let period = now.format("YYYY-MM-DD");
        for (
          ;
          now < last;
          now = moment(now.add(1, "days").format("YYYY-MM-DD"))
        ) {
          if (
            idx < res.data.length &&
            now.isSame(moment(res.data[idx].order_time), "day")
          ) {
            price += res.data[idx].price;
            idx++;
          }
        }
        this.data[0].push(period);
        this.data[1].push(price);
      }
      this.createChart();
    },
    async pickerUpdate() {
      await this.updateData();
    },
    async selectType(type) {
      if (type === 0) {
        this.day = true;
        this.week = false;
        this.month = false;
        await this.updateData();
      } else if (type === 1) {
        this.day = false;
        this.week = true;
        this.month = false;
        await this.updateData();
      } else {
        this.day = false;
        this.week = false;
        this.month = true;
        await this.updateData();
      }
    },
  },
};
</script>
