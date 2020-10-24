package zen.zen.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Getter
@Setter
public class dogVo {

    String name;
    String price;

}
