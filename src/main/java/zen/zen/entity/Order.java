package zen.zen.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import static zen.zen.entity.OrderStatus.*;
import static zen.zen.entity.dogStatus.SELL;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NamedQuery(
        name = "Order.findAllBlock",
        query = "select o from Order o join fetch o.user join fetch o.dog where o.status = :status")
public class Order {

    @Id @GeneratedValue
    private Long id;

    private Long SellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    private int orderPrice;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status = READY;


    public static Order createOrder(User buyer,Long seller , Dog dog, int price) {

        Order order = new Order();
        order.setUser(buyer);
        order.setSellerId(seller);
        order.setDog(dog);
        order.setOrderPrice(price);
        order.setStatus(ORDER);

        return order;

    }

    public void completeOrder() {

        if (status == COMPLETE) {
            throw new IllegalStateException("이미 입양 완료된 강아지입니다.");
        }
        this.dog.setStatus(SELL);
        this.setStatus(COMPLETE);
    }


    public void cancelOrder() {

        if (status == COMPLETE) {
            throw new IllegalStateException("이미 입양 완료된 강아지입니다.");
        }
        this.setStatus(READY);
    }


    public void blockchainSuccess() {

        if (status == BLOCKCHAINSUCCESS) {
            throw new IllegalStateException("이미 블록체인에 저장된 거래입니다.");
        }
        this.setStatus(BLOCKCHAINSUCCESS);


    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}

