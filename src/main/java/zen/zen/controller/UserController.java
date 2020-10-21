package zen.zen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zen.zen.JwtTokenProvider;
import zen.zen.entity.User;
import zen.zen.repository.UserRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;



    @PostMapping("/api/register")
    public ResponseEntity register(@RequestBody Map<String, String> user) {
                userRepository.save(User.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER"))
                .build()).getId();

        return new ResponseEntity(true , HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public Map<String, Object> login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되자않은 이메일"));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalStateException("잘못된 비번입니다");
        }
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        Long id  = member.getId();
    Map<String, Object> data= new HashMap<>();
    data.put("success", true);
    data.put("token", token);
    data.put("id", id);

    return data;
    }

   @PostMapping("/api/auth")
    public Map<String , Object> auth(@RequestHeader(value = "X-AUTH-TOKEN")String token) {
        Map<String, Object> data= new HashMap<>();
        Map<String, Object> data2= new HashMap<>();
        if (token != null && jwtTokenProvider.validateToken(token))
        {
            data.put("isAuth", true);
            data.put("isAdmin", false);
            return data;
        }
        data2.put("isAuth", false);
        return data2;
    }


    @GetMapping("/api/logout")
    public Map<String , Object> logout() {
        Map<String, Object> data= new HashMap<>();
        data.put("isAuth", false);
        data.put("isAdmin", false);
        data.put("success", true);
        return data;
    }


    @PostMapping("/api/user/resource")
    public boolean uResource(){
      return   true;
    }

    @PostMapping("/api/admin/resource")
    public String aResource() {
        return "어드민만";
    }
}
