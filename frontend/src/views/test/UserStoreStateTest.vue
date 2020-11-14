<template>
    <v-container>
        <v-simple-table dark>
            <template v-slot:default>
                <thead>
                    <tr>
                        <th class="text-left">
                            store_state
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <td>{{ order_state }}</td>
                </tbody>
            </template>
        </v-simple-table>
    </v-container>
</template>

<script>
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
    data() {
        return {
            order_state: '',
        }
    },
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
                    this.stompClient.subscribe("/socket/V12006Abp9AeL5pgA11V44G3i/user/2", res => {
                        console.log(res.body);
                        this.order_state = JSON.parse(res.body).order_state;
                    });
                },
                error => {
                    console.log('소켓 연결 실패', error);
                    this.connected = false;
                }
            )
        }
    }
}
</script>