package fr.eseo.twic.milokoson.dto.Order;

import fr.eseo.twic.milokoson.bo.Order;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimpleOrderMapper implements OrderMapper {

    @Override
    public OrderDto bo2dto(Order bo) {
        return new OrderDto(bo.getOrderId(), bo.getCustomer(), bo.getOrderStatus(), bo.getPlacedTimestamp(), bo.getDeliveredTimestamp(), bo.getCancelledTimestamp());
    }

    @Override
    public Iterable<OrderDto> bo2dtoList(Iterable<Order> bo) {
        return StreamSupport.stream(bo.spliterator(), false)
                .map(this::bo2dto).collect(Collectors.toList());
    }

}