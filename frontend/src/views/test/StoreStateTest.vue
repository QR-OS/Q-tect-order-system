<template>
    <v-container>
      <v-row>
      <v-btn
      depressed
      color="primary"
      v-on:click="Reception()"
    >
      주문 접수대기
    </v-btn>
    </v-row>
    <v-row>
    <v-btn
      depressed
      color="primary"
      v-on:click="Ready()"
    >
      주문 준비중
    </v-btn>
    </v-row>
    <v-row>
    <v-btn
      depressed
      color="primary"
      v-on:click="OK()"
    >
      주문 완료
    </v-btn>
    </v-row>
    <v-row>
    <v-btn
      depressed
      color="primary"
      v-on:click="NOTOK()"
    >
      주문 거절
    </v-btn>
    </v-row>
    <v-row>
    <v-btn
      depressed
      color="primary"
      v-on:click="TakeOK()"
    >
      수령 완료
    </v-btn>
    </v-row>
    </v-container>
</template>

<script>
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
  created() {
    this.connect();
  },
  methods: {
    connect() {
      const serverURL = "http://localhost:3000/api";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        frame => {
          console.log('소켓 연결 성공', frame);
          this.connected = true;
        }
      ),
      error => {
        console.log('소켓 연결 실패', error);
        this.connected = false;
      }
    },
    Reception() {
    if(this.stompClient && this.stompClient.connected) {
        const msg = {
          order_state : "주문 접수대기"
        };
        this.stompClient.send("/socket.manager", JSON.stringify(msg), {});
      }
  },
  Ready() {
    if(this.stompClient && this.stompClient.connected) {
        const msg = {
          order_state : "주문 준비중"
        };
        this.stompClient.send("/socket.manager", JSON.stringify(msg), {});
      }
  },
  OK() {
      if(this.stompClient && this.stompClient.connected) {
        const msg = {
          order_state : "주문 완료"
        };
        this.stompClient.send("/socket.manager", JSON.stringify(msg), {});
      }
    },
    NOTOK() {
      if(this.stompClient && this.stompClient.connected) {
        const msg = {
          order_state : "주문 거절"
        };
        this.stompClient.send("/socket.manager/V12006Abp9AeL5pgA11V44G3i/2", JSON.stringify(msg), {});
      }
    },
    TakeOK() {
      if(this.stompClient && this.stompClient.connected) {
        const msg = {
          order_state : "수령 완료"
        };
        this.stompClient.send("/socket.manager/V12006Abp9AeL5pgA11V44G3i/1", JSON.stringify(msg), {});
      }
    }
  }
}
</script>