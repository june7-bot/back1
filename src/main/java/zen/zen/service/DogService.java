package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.Dog;
import zen.zen.repository.DogRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    public void saveDog(Dog dog) {

    }
}
