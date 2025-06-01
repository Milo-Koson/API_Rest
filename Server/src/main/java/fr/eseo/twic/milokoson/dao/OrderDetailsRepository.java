package fr.eseo.twic.milokoson.dao;

import fr.eseo.twic.milokoson.bo.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {

    @Query("SELECT od FROM OrderDetails od WHERE od.orderId.orderId = :identifier ")
    List<OrderDetails> findOrderDetailsByOrderId(@Param("identifier") String identifier);

}
