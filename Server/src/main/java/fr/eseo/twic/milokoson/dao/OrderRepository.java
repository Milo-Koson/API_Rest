package fr.eseo.twic.milokoson.dao;

import fr.eseo.twic.milokoson.bo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.customer.accountNo = :identifier")
    List<Order> findOrdersByCustomer(@Param("identifier") String identifier);

    @Query("SELECT o FROM Order o WHERE o.customer.accountNo = :identifier AND o.orderId = :identifier2")
    List<Order> findOrdersByCustomerAndOrderId(@Param("identifier") String identifier, @Param("identifier2") String identifier2);
}
