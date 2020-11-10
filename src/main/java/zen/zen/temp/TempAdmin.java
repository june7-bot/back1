package zen.zen.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import zen.zen.entity.OrderStatus;

@Getter
@Setter
@AllArgsConstructor
public class TempAdmin {
    private Long id ;
    private String seller;
    private String buyer;
    private Long dogId;
    private int price;
    private OrderStatus orderStatus;

}
