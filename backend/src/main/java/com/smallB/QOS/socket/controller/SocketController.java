package com.smallB.QOS.socket.controller;

import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.socket.domain.OrderStateToUserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/socket.manager/{store_id}/{order_id}")
    @SendTo("/socket/{store_id}/user/{order_id}")
    public OrderStateToUserDto ManagerToUserHandler(OrderStateToUserDto order_state) {
        return order_state;
    }

    @MessageMapping("/socket.user/{store_id}")
    @SendTo("/socket/manager/{store_id}")
    public OrderDto UserToManagerHandler(String storeId,OrderDto orderDto) {
        return orderDto;
    }
}
