package com.smallB.QOS.detailOrder.dao;

import com.smallB.QOS.detailOrder.domain.DetailOrderDto;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailOrderDao {
    List<DetailOrderDto> findDetailOrder(String order_id,String user_id) throws Exception;
    Boolean createDetailOrder(DetailOrderDto detailOrderDto) throws Exception;
}
