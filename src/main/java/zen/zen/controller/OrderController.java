package zen.zen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zen.zen.service.OrderService;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;


    
}
