package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.Dog;
import zen.zen.entity.dogStatus;
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

    public Dog findOneDog(Long id) {
        return dogRepository.getOne(id);
    }

    public Optional<Dog> findById(Long id) {
        return dogRepository.findById(id);
    }

    public List<Dog> findOkList(){
        return dogRepository.findOneList(dogStatus.ONE);
    }

    public List<Dog> findAllDog() {
        return dogRepository.findAll();
    }

    public List<Dog> findByOwner(Long id){  return dogRepository.findByOwner(id);}

    public List<Dog> findByOwnerOne(Long id){  return dogRepository.findByOwnerOne(id, dogStatus.ONE);}
    public List<Dog> findByOwnerZero(Long id){  return dogRepository.findByOwnerZero(id, dogStatus.ZERO);}

}
