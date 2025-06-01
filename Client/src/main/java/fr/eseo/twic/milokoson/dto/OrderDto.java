package fr.eseo.twic.milokoson.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto extends RepresentationModel<OrderDto> {

    private String orderId;
    private CustomerDto customer;
    private OrderStatusDto orderStatus;
    private LocalDateTime placedTimestamp;
    private LocalDateTime deliveredTimestamp;
    private LocalDateTime cancelledTimestamp;

}
