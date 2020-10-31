package zen.zen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zen.zen.entity.Order;
import zen.zen.entity.User;
import zen.zen.repository.UserRepository;
import zen.zen.service.CustomUserDetailService;
import zen.zen.service.OrderService;
import zen.zen.temp.TempAdmin;
import zen.zen.temp.TempUser;
import zen.zen.uri.AdminPaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static zen.zen.uri.AdminPaths.Admin.home.HOME;
import static zen.zen.uri.AdminPaths.Admin.seeUser.ALLUSER;
import static zen.zen.uri.AdminPaths.Admin.transaction.TRANSACTION;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final CustomUserDetailService userService;

    @PostMapping(HOME)
    public void admin() {

    }


    @PostMapping(ALLUSER)
    public Map<String, Object> findUser() {
        List<User> allUser = userService.findAll();
        List<TempUser> temp = new ArrayList<>();

        for (int i = 0; i < allUser.size(); i++) {

            if( allUser.get(i).getEmail().equals("ADMIN@naver.com")){
                TempUser tempUser = new TempUser(allUser.get(i).getEmail(),
                        orderService.findTransactionCount(allUser.get(i).getId(), allUser.get(i).getId())
                        ,"ADMIN");
                temp.add(tempUser);

            }else{
                if(  orderService.findTransactionCount(allUser.get(i).getId(), allUser.get(i).getId()) > 3  ){
                    TempUser tempUser = new TempUser(allUser.get(i).getEmail(),
                            orderService.findTransactionCount(allUser.get(i).getId(), allUser.get(i).getId())
                            ,"VIP");
                    temp.add(tempUser);

                }else{

                TempUser tempUser = new TempUser(allUser.get(i).getEmail(),
                        orderService.findTransactionCount(allUser.get(i).getId(), allUser.get(i).getId())
                        ,"USER");
                    temp.add(tempUser);
                }
            }

        }

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("list", temp);

        return data;
    }


    @PostMapping(TRANSACTION)
    public Map<String, Object> transaction() {

        List<Order> allOrder = orderService.findListByJoinFetch();
        List<TempAdmin> temp = new ArrayList<>();
        System.out.println(allOrder.size());
        System.out.println();

        for (int i = 0; i < allOrder.size(); i++) {

            TempAdmin t = new TempAdmin(
                    allOrder.get(i).getId(),
                    userRepository.getOne(allOrder.get(i).getSellerId()).getEmail(),
                    allOrder.get(i).getUser().getEmail(),
                    allOrder.get(i).getDog().getId(),
                    allOrder.get(i).getDog().getPrice()
            );
            temp.add(t);
        }


        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("list", temp);
        return data;

    }


}
