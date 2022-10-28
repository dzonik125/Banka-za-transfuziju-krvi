package group8.bloodbank.repository;

import group8.bloodbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User create(User user);
}
