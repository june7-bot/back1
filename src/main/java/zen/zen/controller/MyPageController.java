package zen.zen.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zen.zen.JwtTokenProvider;
import zen.zen.entity.Dog;
import zen.zen.entity.Order;
import zen.zen.entity.User;
import zen.zen.repository.UserRepository;
import zen.zen.service.CustomUserDetailService;
import zen.zen.service.DogService;
import zen.zen.service.OrderService;
import zen.zen.temp.TempDog;
import zen.zen.uri.MyPagePaths;

import java.util.*;

import static zen.zen.uri.MyPagePaths.MyPage.adopt.ADOPT;
import static zen.zen.uri.MyPagePaths.MyPage.myPage.MYPAGE;
import static zen.zen.uri.MyPagePaths.MyPage.parcel.PARCEL;
import static zen.zen.uri.MyPagePaths.MyPage.transactionProceed.TRANSACTIONPROCEED;
import static zen.zen.uri.MyPagePaths.MyPage.userInfoChange.USERINFOCHANGE;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final CustomUserDetailService userService;
    private final PasswordEncoder passwordEncoder;
    private final DogService dogService;
    private final OrderService orderService;

    @PostMapping(MYPAGE)
    public Map<String, Object> myPage(@RequestHeader(value = "X-AUTH-TOKEN")String token) {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> fail = new HashMap<>();

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getUserEmail(token);

            data.put("success", true );
            data.put("email", email);
            return data;
        }

        fail.put("success", false);
        return fail;
    }
//
    @PostMapping(USERINFOCHANGE)
    public Map<String , Object> userInfoChange(@RequestBody Map<String , String> info) {

        Optional<User> OptUser = userRepository.findByEmail(info.get("id"));
        User user = OptUser.get();
        user.setPassword(passwordEncoder.encode(info.get("password")));
        userService.flush();

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        return data;
    }


//    @PostMapping(MYWRITE)
//    public Map<String, Object> myPetInfo(@RequestBody() Map<String, String> id ) {
//        Optional<User> user = userService.findByEmail(id.get("id"));
//        Long userId =  user.get().getId();
//        List<Dog> dogs = dogService.findByOwner(userId);
//        Map<String, Object> data = new HashMap<>();
//        data.put("success", true);
//        data.put("list", dogs);
//
//        return data;
//
//    }

    @PostMapping(ADOPT)
    public Map<String, Object> adopt(@RequestBody() Map<String, String> id) {
        Optional<User> user = userService.findByEmail(id.get("id"));
        Long userId =  user.get().getId();

        List<Dog> dogOne = dogService.findByOwnerOne(userId);
        List<Dog> dogZero = dogService.findByOwnerZero(userId);


        List<Long> dogId = new ArrayList<>();

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("listOne", dogOne);
        data.put("listZero", dogZero);
        //data.put("buyer", dogId);

        return data;
    }


    @PostMapping(PARCEL)
    public Map<String, Object> parcel(@RequestBody() Map<String, String> id) {

        Optional<User> user = userService.findByEmail(id.get("id"));
        Long userId =  user.get().getId();

        List<Long> dogId = orderService.findDogByPurchaser(userId);
         List <Dog> dogs = new ArrayList<>();


        for (int i = 0; i < dogId.size(); i++) {
            Optional<Dog> byId = dogService.findById(dogId.get(i));
            dogs.add(byId.get());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("list", dogs);
        return data;

    }

//    @PostMapping(TRANSACTIONPROCEED)
//    public Map<String, Object> proceeding (@RequestBody Map<String , String> id) {
//        Optional<User> byEmail = userService.findByEmail(id.get("email"));
//        User user = byEmail.get();
//        Long userId = user.getId();
//
//
//        List<Order> proceedBuyer = orderService.findProceedOrderBuyer(userId);
//        List<Order> proceedSeller = orderService.findProceedOrderSeller(userId);
//        List<TempDog> buyerList = new ArrayList<>();
//        List<TempDog> sellerList = new ArrayList<>();
//
//        for( int i = 0 ; i < proceedBuyer.size(); i++) {
//
//            TempDog tempBuyer = new TempDog(proceedBuyer.get(i).getDog().getName()
//                    ,proceedBuyer.get(i).getDog().getPrice()
//                    ,proceedBuyer.get(i).getDog().getPhoto() );
//
//            buyerList.add(tempBuyer);
//        }
//
//        for( int i = 0 ; i < proceedSeller.size(); i++) {
//            TempDog tempSeller = new TempDog(proceedSeller.get(i).getDog().getName()
//                    ,proceedSeller.get(i).getDog().getPrice()
//                    ,proceedSeller.get(i).getDog().getPhoto() );
//
//            sellerList.add(tempSeller);
//
//
//        }
//
//
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("success", true);
//        data.put("buyer" , buyerList);
//        data.put("seller" , sellerList);
//
//
//        return data;
//
//    }

}
