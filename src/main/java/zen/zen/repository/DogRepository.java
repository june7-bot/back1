package zen.zen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zen.zen.entity.Dog;

public interface DogRepository extends JpaRepository<Dog , Long> {
}
