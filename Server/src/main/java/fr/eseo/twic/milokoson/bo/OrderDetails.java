package fr.eseo.twic.milokoson.bo;

import fr.eseo.twic.milokoson.dto.OrderDetails.OrderDetailsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private String orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity")
    private Integer quantity;

    public static OrderDetails from(OrderDetailsDto dto) {
        return new OrderDetails(dto.getOrderDetailId(), dto.getOrderId(), dto.getProductId(), dto.getQuantity());
    }

    public OrderDetails updateFrom(OrderDetails other) {
        orderDetailId = other.getOrderDetailId();
        return this;
    }
}
