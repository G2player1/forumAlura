package Enos.SpringProject.forumAlura.domain.repositories;

import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    UserDetails findByUsernameAndActive(String username, Integer active);

    User findByIdAndActive(Long id, Integer active);

    Page<User> findAllByActive(Integer active, Pageable pageable);
}
