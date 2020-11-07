package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.*;
import zen.zen.repository.OrderRepository;

import java.util.List;
import java.util.Optional;



@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomUserDetailService userService;
    private final DogService dogService;

    public Long order(Long userId, Long dogId) {
        User buyer = userService.findOne(userId);
        Dog dog = dogService.findOneDog(dogId);
        dog.setStatus(dogStatus.ZERO);
        Long seller = dog.getOwner();
        int price = dog.getPrice();

        Order order = Order.createOrder(buyer, seller, dog, price);
        orderRepository.save(order);

        return order.getId();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrder(Long id) {
        return orderRepository.findById(id);
    }

   /* public Long findBuyer(Long dogId) {
        return orderRepository.findBuyer(dogId);
    }*/

    public List<Long> findDogByPurchaser(Long userId) {
        return orderRepository.findDogByPurchaser(userId);
    }

    public List<Order> findListByJoinFetch() {
        return orderRepository.findAllJoinFetch();
    }

    public int findTransactionCount(Long sell, Long buy) {
        return orderRepository.findTransactionCount(sell, buy);
    }

    public List<Order> findBlockList() {

        return orderRepository.findBlockList("BLOCK");
    }

//    public List<Order> findNotSaveInBc(){
//        return orderRepository.findNotSaveInBc("NOBLOCK");
//    }
//    public List<Order> findProceedOrderBuyer(Long id ){
//        return orderRepository.findProceedOrderBuyer("PROCEEDING", id);
//    }
//
//    public List<Order> findProceedOrderSeller(Long id ){
//        return orderRepository.findProceedOrderSeller("PROCEEDING", id);
//    }
}
