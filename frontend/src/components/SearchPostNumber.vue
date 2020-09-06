<template>
  <div ref="searchWindow" :style="searchWindow">
    <img
      src="//t1.daumcdn.net/postcode/resource/images/close.png"
      id="btnFoldWrap"
      style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1"
      @click="close"
      alt="close"
    />
  </div>
</template>

<script>
export default {
  name: "SearchPostNumberModal",
  props: ["hot_table"],
  data() {
    return {
      searchWindow: {
        display: "none",
        height: "300px",
      },
      postcode: "",
      address: "",
      extraAddress: "",
    };
  },
  mounted() {
    console.log("mounted is called");
    this.execDaumPostcode();
  },
  methods: {
    close() {
      this.$emit("close");
    },
    // 우편번호 API
    execDaumPostcode() {
      const currentScroll = Math.max(
        document.body.scrollTop,
        document.documentElement.scrollTop
      );
      // eslint-disable-next-line
      new daum.Postcode({
        onComplete: (data) => {
          if (data.userSelectedType === "R") {
            this.address = data.roadAddress;
          } else {
            this.address = data.jibunAddress;
          }
          if (data.userSelectedType === "R") {
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            if (this.extraAddress !== "") {
              this.extraAddress = ` (${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          this.postcode = data.zonecode;
          this.$emit("focusToExtraAd");
          this.searchWindow.display = "none";
          document.body.scrollTop = currentScroll;
          this.close();
        },
        onResize: (size) => {
          this.searchWindow.height = `${size.height}px`;
        },
        width: "100%",
        height: "100%",
      }).embed(this.$refs.searchWindow);
      this.searchWindow.display = "block";
    },
  },
};
</script>
