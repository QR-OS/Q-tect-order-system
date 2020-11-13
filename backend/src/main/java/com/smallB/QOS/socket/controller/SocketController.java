package com.smallB.QOS.socket.controller;

import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.socket.domain.OrderStateToUserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/socket.manager")
    @SendTo("/socket/user")
    public OrderStateToUserDto ManagerToUserHandler(OrderStateToUserDto order_state) {
        return order_state;
    }

    @MessageMapping("/socket.user")
    @SendTo("/socket/manager")
    public OrderDto UserToManagerHandler(OrderDto orderDto) {
        return orderDto;
    }
}
