package zen.zen.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    private int orderPrice;

    //연관관계
    public void setUser(User user) {
        this.user = user;
        user.getOrder().add(this);
    }
}
