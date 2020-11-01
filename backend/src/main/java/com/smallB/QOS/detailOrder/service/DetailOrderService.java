package com.smallB.QOS.detailOrder.service;

import com.smallB.QOS.detailOrder.domain.DetailOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailOrderService {
    List<DetailOrderDto> getDetailOrder(String order_id, String user_id) throws Exception;
}
