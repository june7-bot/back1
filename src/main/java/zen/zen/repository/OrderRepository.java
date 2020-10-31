package zen.zen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zen.zen.entity.Dog;
import zen.zen.entity.Order;
import zen.zen.entity.OrderStatus;
import zen.zen.entity.dogStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /*@Query(value = "select user_id from orders where dog_id = ?", nativeQuery = true)
    Long findBuyer(Long dog_id);*/

    @Query(value = "select dog_id from orders where buyer_id = ?", nativeQuery = true)
    List<Long> findDogByPurchaser(Long purchaserId);

    @Query("select o from Order o join fetch o.user join fetch o.dog")
    List<Order> findAllJoinFetch();

    @Query(value = "select count(*) from orders where buyer_id = ? or seller_id = ?", nativeQuery = true)
    int findTransactionCount(Long buy, Long sell);

    @Query(value = "select * from orders where status = ? and seller_id = ?", nativeQuery = true)
    List<Order> findProceedOrderSeller(String orderStatus , Long sellerId);

    @Query(value = "select * from orders where status = ? and buyer_id = ?", nativeQuery = true)
    List<Order> findProceedOrderBuyer(String orderStatus , Long buyerId);
}
