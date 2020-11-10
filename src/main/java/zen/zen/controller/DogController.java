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
import java.util.Optional;

import static zen.zen.entity.dogStatus.READY;
import static zen.zen.uri.DogPaths.Dog.list.LIST;
import static zen.zen.uri.DogPaths.Dog.register.*;
import static zen.zen.uri.DogPaths.one.ONE;

@RestController
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping(LIST)
    public Map<String, Object> DogList() {

        List<Dog> dogs =  dogService.findOkList();
        Map<String, Object> data = new HashMap<>();
        data.put("list", dogs );
        data.put("success", true);
        return data;

    }


    @PostMapping(ONE)
    public Map<String, Object> oneDog(@RequestBody Map<String, Long> id) {
        System.out.println(id.get("dogId"));
        Optional<Dog> temp = dogService.findById(id.get("dogId"));
        Dog dog = temp.get();

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("dog", dog);

        return data;
    }

    @PostMapping(REGISTER)
    public Map<String, Object> upload(
            @RequestParam("nose") MultipartFile nose,
            @RequestParam("picture") MultipartFile photo,
            @RequestParam("birth") MultipartFile birthFile,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("owner") Long id,
            @RequestParam("kind") String kind,
            @RequestParam("gender") String gender,
            @RequestParam("prevent") String prevent,
            @RequestParam("age") int age
            ){
        File targetFile = new File("upload/dogs/" + nose.getOriginalFilename());
        try {
            InputStream fileStream = photo.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        System.out.println(photo.getOriginalFilename());
        File targetFile1 = new File("upload/dogs/" + photo.getOriginalFilename());
        try {
            InputStream fileStream = nose.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile1);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile1);
            e.printStackTrace();
        }

        File targetFile2 = new File("upload/dogs/" + birthFile.getOriginalFilename());
        try {
            InputStream fileStream = birthFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile2);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile2);
            e.printStackTrace();
        }

        dogService.saveDog(Dog.builder()
                .name(name)
                .price(price)
                .photo(photo.getOriginalFilename())
                .owner(id)
                .nose(nose.getOriginalFilename())
                .dogKind(kind)
                .dogGender(gender)
                .dogPrevent(prevent)
                .dogAge(age)
                .birthFile(birthFile.getOriginalFilename())
                .status(READY)
                .build());

        Map<String, Object> data = new HashMap<>();
        data.put("success", true);

        return data;
    }
}
