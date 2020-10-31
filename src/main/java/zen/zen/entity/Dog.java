package zen.zen.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Dog.findOneList",
        query = "select d from Dog d where d.status = :status")
@NamedQuery(
        name = "Dog.findByOwner",
        query = "select d from Dog d where d.owner = :owner")
@NamedQuery(
        name = "Dog.findByOwnerOne",
        query = "select d from Dog d where d.owner = :owner and d.status = :status")
@NamedQuery(
        name = "Dog.findByOwnerZero",
        query = "select d from Dog d where d.owner = :owner and d.status = :status")
public class Dog {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private String photo;
    private Long owner;
    //private String nose;
    //private String file;

    @Enumerated(EnumType.STRING)
    @Setter
    private dogStatus status;

}
