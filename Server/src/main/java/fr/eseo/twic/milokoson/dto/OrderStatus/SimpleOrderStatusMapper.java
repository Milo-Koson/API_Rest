package fr.eseo.twic.milokoson.dto.OrderStatus;

import fr.eseo.twic.milokoson.bo.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimpleOrderStatusMapper implements OrderStatusMapper{

    @Override
    public OrderStatusDto bo2dto(OrderStatus bo) {
        return new OrderStatusDto(bo.getOrderStatusId(), bo.getOrderStatusName());
    }

    @Override
    public Iterable<OrderStatusDto> bo2dtoList(Iterable<OrderStatus> bo) {
        return StreamSupport.stream(bo.spliterator(), false)
                .map(this::bo2dto).collect(Collectors.toList());
    }

}
