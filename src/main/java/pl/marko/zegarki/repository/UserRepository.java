package pl.marko.zegarki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.marko.zegarki.entity.User;
import pl.marko.zegarki.entity.UserJoinInterface;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query(value = "SELECT p.user_id AS id, p.email AS email, p.name AS name, p.active AS active, role.role AS role from ((user_role c \n" +
            "INNER JOIN users p ON c.user_id=p.user_id)\n" +
            "INNER JOIN role ON role.role_id = c.role_id);", nativeQuery = true)
    List<UserJoinInterface> findAUsersAndRole();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users u SET active = :active WHERE u.user_id = :id", nativeQuery = true)
    void setUserActive(@Param("active") int active, @Param("id") int id);

}
