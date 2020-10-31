package zen.zen.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import zen.zen.email.Email;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "dogtemptt@gmail.com";

    public void mailSend(Email email) {
        //이메일 메시지 정보
        SimpleMailMessage message = new SimpleMailMessage();
        //받는 사람 주소
        message.setTo(email.getAddress());
        //보내는 사람 주소
        message.setFrom(MailService.FROM_ADDRESS);
        //제목및 내용
        message.setSubject(email.getTitle());
        message.setText(email.getMessage());

        mailSender.send(message);
    }
}