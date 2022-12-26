package al.spring.repository;

import al.spring.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    @EntityGraph(attributePaths = {"posts", "roles"})
    List<User> findAll();
}