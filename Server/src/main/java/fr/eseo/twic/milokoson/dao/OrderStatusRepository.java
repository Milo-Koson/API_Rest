package fr.eseo.twic.milokoson.dao;

import fr.eseo.twic.milokoson.bo.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {
}
