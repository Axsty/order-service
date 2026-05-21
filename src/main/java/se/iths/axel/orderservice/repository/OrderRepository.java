package se.iths.axel.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.axel.orderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
