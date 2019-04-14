package ch.practice.repository;

import ch.practice.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {

    @Query("from User where name = ?1")
    User findUserByName(String name);
}
