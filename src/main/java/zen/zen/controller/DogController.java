package zen.zen.controller;

import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zen.zen.entity.Dog;
import zen.zen.service.DogService;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;


//
//    @PostMapping("/api/dog/register")
//    public ResponseEntity DogRegister(@RequestBody Map<String, String> dog) {
//        dogService.saveDog(Dog.builder()
//                .name(dog.get("name"))
//                .price(dog.get("price"))
//                .nose(dog.get("nose"))
//                .build());
//
//        return new ResponseEntity(true, HttpStatus.OK);
//    }

    @PostMapping("/api/dog/list")
    public List<Dog> DogList() throws FileNotFoundException {


        return dogService.findAllDog();
    }

    @PostMapping("/api/dog/register")
    public Map<String, Object> upload(@RequestBody Map<String , String > info ){


        dogService.saveDog(Dog.builder()
                .name(info.get("name"))
                .price(info.get("price"))
                .build());

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);

        return data;
    }


    @PostMapping("/api/dog/photo")
    public void getPhoto (@RequestParam("file") MultipartFile multipartFile) {
        UUID id = UUID.randomUUID();


        File targetFile = new File("src/main/resources/static/imgs/" + id + ".jpg");
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }

    }
}
