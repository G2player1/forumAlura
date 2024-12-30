package Enos.SpringProject.forumAlura.domain.services.user;

import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadDetailedUserDTO;
import Enos.SpringProject.forumAlura.domain.repositories.IUserRepository;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadUserDTO;
import Enos.SpringProject.forumAlura.domain.models.user.dto.RegisterUserDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public ReadUserDTO registerUser(RegisterUserDTO registerUserDTO){
        var user = new User(registerUserDTO);
        user = userRepository.save(user);
        return new ReadUserDTO(user);
    }

    @Transactional
    public ReadUserDTO getUserById(Long id) {
        var user = userRepository.findByIdAndActive(id,1);
        if(user == null){
            throw new EntityNotFoundException("user not found");
        }
        return new ReadUserDTO(user);
    }

    public ReadDetailedUserDTO getDetailedUserById(Long id) {
        var user = userRepository.findByIdAndActive(id,1);
        if(user == null){
            throw new EntityNotFoundException("user not found");
        }
        return new ReadDetailedUserDTO(user);
    }

    public Page<ReadUserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAllByActive(1,pageable).map(ReadUserDTO::new);
    }
}
