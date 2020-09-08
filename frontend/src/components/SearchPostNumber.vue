<template>
  <div ref="searchWindow" :style="searchWindow">
    <img
      id="btnFoldWrap"
      src="//t1.daumcdn.net/postcode/resource/images/close.png"
      style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1"
      alt="close"
      @click="close"
    />
  </div>
</template>

<script>
export default {
  name: "SearchPostNumberModal",
  data() {
    return {
      searchWindow: {
        display: "none",
        height: "300px"
      },
      postcode: "",
      address: "",
      extraAddress: ""
    };
  },
  mounted() {
    this.execDaumPostcode();
  },
  methods: {
    close() {
      this.$emit("child", this.postcode, (this.address += this.extraAddress));
    },
    // 우편번호 API
    execDaumPostcode() {
      const currentScroll = Math.max(
        document.body.scrollTop,
        document.documentElement.scrollTop
      );
      // eslint-disable-next-line
      new daum.Postcode({
        onComplete: data => {
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
        onResize: size => {
          this.searchWindow.height = `${size.height}px`;
        },
        width: "100%",
        height: "100%"
      }).embed(this.$refs.searchWindow);
      this.searchWindow.display = "block";
    }
  }
};
</script>
