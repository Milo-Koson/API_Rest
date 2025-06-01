package fr.eseo.twic.milokoson.dto.OrderStatus;

import fr.eseo.twic.milokoson.bo.OrderStatus;

public interface OrderStatusMapper {

    OrderStatusDto bo2dto(OrderStatus bo);

    Iterable<OrderStatusDto> bo2dtoList(Iterable<OrderStatus> bo);

}
