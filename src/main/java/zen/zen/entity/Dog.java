package zen.zen.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Dog {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String price;

}
