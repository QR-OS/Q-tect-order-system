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
        System.out.println("들어왔습니다!");
        return order_state;
    }
}
