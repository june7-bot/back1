package zen.zen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zen.zen.service.DogService;
import zen.zen.service.OrderService;

import java.util.HashMap;
import java.util.Map;

import static zen.zen.uri.OrderPaths.Order.orderDog.ORDER;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final DogService dogService;

    @PostMapping(ORDER)
    public Map<String, Object> orderDog(@RequestBody Map<String, Long> info) {
        Long userId = info.get("userId");
        Long dogId = info.get("dogId");
        Long owner = dogService.findOneDog(dogId).getOwner();

        Map<String, Object> data = new HashMap<>();
        if(userId == owner)
        {
            data.put("success", false);
        }
        else {
            orderService.order(userId, dogId);
            data.put("success", true);
        }
        return data;

    }

}
