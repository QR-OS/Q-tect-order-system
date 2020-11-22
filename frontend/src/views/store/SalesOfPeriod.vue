<template>
  <!-- Load with base style -->
  <v-container>
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
            v-if="month"
            v-model="date"
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
          <v-date-picker v-model="date" no-title scrollable>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">
              Cancel
            </v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(date)">
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
    <v-row>
      <v-col><div id="areaRangeChart"></div> </v-col>
    </v-row>
  </v-container>
</template>

<script>
import bb, { areaLineRange, areaSplineRange } from "billboard.js";
import "billboard.js/dist/billboard.css";

export default {
  data() {
    return {
      day: true,
      week: false,
      month: false,
      date: new Date().toISOString().substr(0, 7),
      menu: false,
      data: [],
    };
  },
  mounted() {
    var chart = bb.generate({
      data: {
        x: "x",
        columns: [
          [
            "x",
            "2013-01-01",
            "2013-01-02",
            "2013-01-03",
            "2013-01-04",
            "2013-01-05",
            "2013-01-06",
          ],
          [
            "data1",
            [150, 140, 110],
            [155, 130, 115],
            [160, 135, 120],
            [135, 120, 110],
            [180, 150, 130],
            [199, 160, 125],
          ],
          ["data2", 130, 340, 200, 500, 250, 350],
        ],
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
    setTimeout(function() {
      chart.load({
        columns: [
          [
            "data3",
            [220, 215, 205],
            [240, 225, 215],
            [260, 235, 225],
            [280, 245, 235],
            [270, 255, 225],
            [240, 225, 215],
          ],
        ],
        types: {
          data3: areaSplineRange(), // for ESM specify as: areaSplineRange()
        },
      });
    }, 1000);

    setTimeout(function() {
      chart.load({
        columns: [
          [
            "data4",
            { high: 155, low: 145, mid: 150 },
            { high: 200, mid: 190, low: 150 },
            { high: 230, mid: 215, low: 200 },
            { high: 210, mid: 200, low: 180 },
            { high: 220, mid: 210, low: 190 },
            { high: 200, mid: 180, low: 160 },
          ],
        ],
        types: {
          data4: areaSplineRange(), // for ESM specify as: areaSplineRange()
        },
      });
    }, 1500);
  },
  methods: {
    selectType(type) {
      if (type === 0) {
        this.day = true;
        this.week = false;
        this.month = false;
      } else if (type === 1) {
        this.day = false;
        this.week = true;
        this.month = false;
      } else {
        this.day = false;
        this.week = false;
        this.month = true;
      }
    },
  },
};
</script>
