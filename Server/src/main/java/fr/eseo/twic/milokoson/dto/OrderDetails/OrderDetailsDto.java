package fr.eseo.twic.milokoson.dto.OrderDetails;

import fr.eseo.twic.milokoson.bo.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailsDto extends RepresentationModel<OrderDetailsDto> {

    private String orderDetailId;
    private Order orderId;
    private Integer productId;
    private Integer quantity;

}
