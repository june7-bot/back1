package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.*;
import zen.zen.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

import static zen.zen.entity.OrderStatus.BLOCKCHAINSUCCESS;
import static zen.zen.entity.OrderStatus.COMPLETE;
import static zen.zen.entity.dogStatus.ORDERING;
import static zen.zen.entity.dogStatus.READY;


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
        dog.setStatus(ORDERING);
        Long seller = dog.getOwner();
        int price = dog.getPrice();

        Order order = Order.createOrder(buyer, seller, dog, price);
        orderRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.getDog().setStatus(READY);
        order.cancelOrder();
    }

    public void successOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.completeOrder();
    }

    public void blockchainSuccess(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.blockchainSuccess();
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
        return orderRepository.findAllJoinFetch(COMPLETE);
    }

    public int findTransactionCount(Long sell, Long buy) {
        return orderRepository.findTransactionCount(sell, buy);
    }

    public List<Order> findAllBlock(){
        return orderRepository.findAllBlocks(BLOCKCHAINSUCCESS);
    }

    public List<Order> findProceedOrderBuyer(Long id ){
        return orderRepository.findProceedOrderBuyer("ORDER", id);
    }

    public List<Order> findProceedOrderSeller(Long id ){
        return orderRepository.findProceedOrderSeller("ORDER", id);
    }

    public Order findOrderByDogId(Long id ){
        return orderRepository.findOrderByDogId(id);
    }

}
