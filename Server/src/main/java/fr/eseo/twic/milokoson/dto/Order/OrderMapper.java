package fr.eseo.twic.milokoson.dto.Order;

import fr.eseo.twic.milokoson.bo.Order;

public interface OrderMapper {

    OrderDto bo2dto(Order bo);

    Iterable<OrderDto> bo2dtoList(Iterable<Order> bo);

}