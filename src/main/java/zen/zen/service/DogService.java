package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.Dog;
import zen.zen.repository.DogRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    public void saveDog(Dog dog) {
        dogRepository.save(dog);
    }

    public Optional<Dog> findOneDog(Long id) {
        return dogRepository.findById(id);
    }

    public List<Dog> findAllDog() {
        return dogRepository.findAll();
    }
}
