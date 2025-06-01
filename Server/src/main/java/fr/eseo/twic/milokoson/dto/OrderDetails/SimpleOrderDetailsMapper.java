package fr.eseo.twic.milokoson.dto.OrderDetails;

import fr.eseo.twic.milokoson.bo.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimpleOrderDetailsMapper implements OrderDetailsMapper{

    @Override
    public OrderDetailsDto bo2dto(OrderDetails bo) {
        return new OrderDetailsDto(bo.getOrderDetailId(), bo.getOrderId(), bo.getProductId(), bo.getQuantity());
    }

    @Override
    public Iterable<OrderDetailsDto> bo2dtoList(Iterable<OrderDetails> bo) {
        return StreamSupport.stream(bo.spliterator(), false)
                .map(this::bo2dto).collect(Collectors.toList());
    }

}
