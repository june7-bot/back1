package zen.zen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String price;

    private UUID nose;



}
