package zen.zen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zen.zen.JwtTokenProvider;
import zen.zen.email.Email;
import zen.zen.entity.User;
import zen.zen.repository.UserRepository;
import zen.zen.service.CustomUserDetailService;
import zen.zen.service.MailService;
import zen.zen.uri.UserPaths;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static zen.zen.uri.UserPaths.User.auth.AUTH;
import static zen.zen.uri.UserPaths.User.changePassword.CHANGEPW;
import static zen.zen.uri.UserPaths.User.findId.FINDID;
import static zen.zen.uri.UserPaths.User.findPassword.FINDPW;
import static zen.zen.uri.UserPaths.User.login.LOGIN;
import static zen.zen.uri.UserPaths.User.logout.LOGOUT;
import static zen.zen.uri.UserPaths.User.register.REGISTER;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final  CustomUserDetailService userService;

    @PostMapping(REGISTER)
    public Map<String, Object> register(@RequestBody Map<String, String> user) {

        if(user.get("email") == "ADMIN@naver.com"){
            userRepository.save(User.builder()
                    .email(user.get("email"))
                    .password(passwordEncoder.encode(user.get("password")))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build()).getId();
        }else {
            userRepository.save(User.builder()
                    .email(user.get("email"))
                    .password(passwordEncoder.encode(user.get("password")))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build()).getId();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        return data;
    }

    @PostMapping(LOGIN)
    public Map<String, Object> login(@RequestBody Map<String, String> user) {
        Map<String, Object> data = new HashMap<>();
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되자않은 이메일"));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            data.put("success", false);
            return data;
        }
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        Long id = member.getId();

        data.put("success", true);
        data.put("token", token);
        data.put("id", id);

        return data;
    }

    @PostMapping(AUTH)
    public Map<String, Object> auth(@RequestHeader(value = "X-AUTH-TOKEN") String token) {
        Map<String, Object> data = new HashMap<>();

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getUserEmail(token);
            Optional<User> user = userRepository.findByEmail(email);
            Long id = user.get().getId();
            String adminCk = user.get().getEmail();

            if(adminCk.equals("ADMIN@naver.com") ){

                data.put("isAuth", true);
                data.put("isAdmin", true);
                data.put("userId", id);
                return data;

            }else{
                data.put("isAuth", true);
                data.put("isAdmin", false);
                data.put("userId", id);
                return data;
            }
        }
        data.put("isAuth", false);
        return data;
    }


    @GetMapping(LOGOUT)
    public Map<String, Object> logout() {
        Map<String, Object> data = new HashMap<>();
        data.put("isAuth", false);
        data.put("isAdmin", false);
        data.put("success", true);
        return data;
    }


    @PostMapping(FINDID)
    public void findId() {
    }

    @PostMapping(FINDPW)
    public Map<String, Object> findPw(@RequestBody Map<String, String> data) {
        String id = data.get("email");
        int n = (int) (Math.random() * 10000);
        Email email = new Email();
        email.setAddress(id);
        email.setMessage("인증번호 : " + n);
        mailService.mailSend(email);

        Map<String, Object> submit = new HashMap<>();
        submit.put("success", true);
        submit.put("number", n);

        return submit;
    }

    @PostMapping(CHANGEPW)
    public Map<String, Object> changePw(@RequestBody Map<String, String> info) {
        String email = (String) info.get("email");
        String pw = (String) info.get("password");

        Optional<User> OptUser = userRepository.findByEmail(info.get("email"));
        User user = OptUser.get();
        user.setPassword(passwordEncoder.encode(info.get("password")));
        userService.flush();

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        return data;
    }

}
