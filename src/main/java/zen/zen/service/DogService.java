package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.Dog;
import zen.zen.repository.DogRepository;

import java.util.List;
import java.util.Optional;

import static zen.zen.entity.dogStatus.READY;
import static zen.zen.entity.dogStatus.SELL;

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
        return dogRepository.findOneList(READY);
    }

    public String checkSize(String check) {
        Dog dog = new Dog();
        return dog.checkSize(check);
    }

    public List<Dog> findAllDog() {
        return dogRepository.findAll();
    }

    public List<Dog> findByOwner(Long id){  return dogRepository.findByOwner(id);}

    public List<Dog> findByOwnerOne(Long id){  return dogRepository.findByOwnerOne(id, READY);}
    public List<Dog> findByOwnerZero(Long id){  return dogRepository.findByOwnerZero(id, SELL);}

    public List<Dog> findAllSmallDog() { return  dogRepository.findAllSmallDog("SMALL");}
    public List<Dog> findAllMidDog() { return  dogRepository.findAllMidDog("MID");}
    public List<Dog> findAllBigDog() { return  dogRepository.findAllBigDog("BIG");}

    public List<Dog> findSmallDogsByKind (int kindNum){
        Dog dog = new Dog();
        return dogRepository.findAllDogKind(dog.checkSmallKind(kindNum));}

    public List<Dog> findMidDogsByKind (int kindNum){
        Dog dog = new Dog();
        return dogRepository.findAllDogKind(dog.checkMidKind(kindNum));}
    public List<Dog> findBigDogsByKind (int kindNum){
        Dog dog = new Dog();
        return dogRepository.findAllDogKind(dog.checkBigKind(kindNum));}
}
