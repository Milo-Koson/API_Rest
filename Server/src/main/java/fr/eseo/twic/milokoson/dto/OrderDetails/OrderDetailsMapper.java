package fr.eseo.twic.milokoson.dto.OrderDetails;

import fr.eseo.twic.milokoson.bo.OrderDetails;

public interface OrderDetailsMapper {

    OrderDetailsDto bo2dto(OrderDetails bo);

    Iterable<OrderDetailsDto> bo2dtoList(Iterable<OrderDetails> bo);
}
