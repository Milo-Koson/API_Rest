package fr.eseo.twic.milokoson.bo;

import fr.eseo.twic.milokoson.dto.OrderStatus.OrderStatusDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_statuses")
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {

    @Id
    @Column(name = "order_status_id")
    private String orderStatusId;

    @Column(name = "order_status_name")
    private String orderStatusName;

    public static OrderStatus from(OrderStatusDto dto) {
        return new OrderStatus(dto.getOrderStatusId(), dto.getOrderStatusName());
    }

    public OrderStatus updateFrom(OrderStatus other) {
        orderStatusId = other.getOrderStatusId();
        return this;
    }

}
