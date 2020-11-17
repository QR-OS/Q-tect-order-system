package com.smallB.QOS.socket.controller;

import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.socket.domain.OrderStateToUserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/socket.user/{store_id}")
    @SendTo("/socket/manager/{store_id}")
    public OrderDto UserToManagerHandler(OrderDto orderDto) {
        return orderDto;
    }
}
