package com.smallB.QOS.detailOrder.service;

import com.smallB.QOS.detailOrder.dao.DetailOrderDao;
import com.smallB.QOS.detailOrder.domain.DetailOrderDto;
import com.smallB.QOS.detailOrder.error.DetailOrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class DetailOrderServiceImpl implements DetailOrderService{

    @Autowired
    private DetailOrderDao detailOrderDao;

    @Override
    public List<DetailOrderDto> getDetailOrder(String order_id, String user_id) throws Exception{
        List<DetailOrderDto> detailOrderDtos = detailOrderDao.findDetailOrder(order_id,user_id);

        if(detailOrderDtos.size()==0){
            throw new DetailOrderNotFound(order_id);
        }
        return detailOrderDtos;
    }

    @Override
    public DetailOrderDto createDetailOrder(DetailOrderDto detailOrderDto) throws Exception{
        boolean res = detailOrderDao.createDetailOrder(detailOrderDto);
        if(!res){
            throw new DetailOrderNotFound(Integer.toString(detailOrderDto.getOrder_id()));
        }
        return detailOrderDto;
    }
}
