package fr.eseo.twic.milokoson.bo;

import fr.eseo.twic.milokoson.dao.CustomerRepository;
import fr.eseo.twic.milokoson.dto.Order.OrderDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "account_no", referencedColumnName = "account_no")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column(name = "placed_timestamp")
    private LocalDateTime placedTimestamp;

    @Column(name = "delivered_timestamp")
    private LocalDateTime deliveredTimestamp;

    @Column(name = "cancelled_timestamp")
    private LocalDateTime cancelledTimestamp;

    public Order updateFrom(OrderDto dto, OrderStatus orderStatus) {
        this.setOrderStatus(orderStatus);
        this.setDeliveredTimestamp(dto.getDeliveredTimestamp());
        this.setCancelledTimestamp(dto.getCancelledTimestamp());
        return this;
    }

    public static Order from(OrderDto dto, CustomerRepository customerRepository) {
        String accountNo = dto.getCustomer().getAccountNo();
        List<Customer> customers = customerRepository.findCustomersByLastNameOrFirstNameOrAccountNo(accountNo);
        if (customers.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        Customer customer = customers.get(0);
        return new Order(dto.getOrderId(), customer, dto.getOrderStatus(), dto.getPlacedTimestamp(), dto.getDeliveredTimestamp(), dto.getCancelledTimestamp());
    }
}
