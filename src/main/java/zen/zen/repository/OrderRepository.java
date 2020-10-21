package zen.zen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zen.zen.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
