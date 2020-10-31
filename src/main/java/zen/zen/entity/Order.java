package zen.zen.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "orders")
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
    private OrderStatus status = OrderStatus.CANCEL;

    //주문생성
    public static Order createOrder(User buyer,Long seller , Dog dog, int price) {

        Order order = new Order();
        order.setUser(buyer);
        order.setSellerId(seller);
        order.setDog(dog);
        order.setOrderPrice(price);
        order.setStatus(OrderStatus.PROCEEDING);

        return order;

    }

    //연관관계
    public void setUser(User user) {
        this.user = user;

    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
