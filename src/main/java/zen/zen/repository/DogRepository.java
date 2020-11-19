package zen.zen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zen.zen.entity.Dog;
import zen.zen.entity.dogStatus;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog , Long> {

    @Query(name = "Dog.findOneList")
    List<Dog> findOneList(@Param("status") dogStatus status);

    @Query(name = "Dog.findByOwner")
    List<Dog> findByOwner(@Param("owner") Long id);

    @Query(name = "Dog.findByOwnerOne")
    List<Dog> findByOwnerOne(@Param("owner") Long id, @Param("status") dogStatus status);

    @Query(name = "Dog.findByOwnerZero")
    List<Dog> findByOwnerZero(@Param("owner") Long id, @Param("status") dogStatus status);

    @Query("select d from Dog d where d.dogSize = :size ")
    List<Dog> findAllSmallDog(@Param("size") String size);

    @Query("select d from Dog d where d.dogSize = :size ")
    List<Dog> findAllMidDog(@Param("size") String size);

    @Query("select d from Dog d where d.dogSize = :size ")
    List<Dog> findAllBigDog(@Param("size") String size);

    @Query("select d from Dog d where d.dogKind = :kind ")
    List<Dog> findAllDogKind(@Param("kind") String kind);
}
