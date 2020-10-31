package zen.zen.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Email {
    private String address;
    private String title = "도그블록 홈페이지에 인증번호 입력해주세요.";
    private String message = "";

}