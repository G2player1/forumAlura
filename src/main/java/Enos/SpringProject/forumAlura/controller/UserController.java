package Enos.SpringProject.forumAlura.controller;

import Enos.SpringProject.forumAlura.domain.exceptions.CantAuthenticateException;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.models.user.dto.*;
import Enos.SpringProject.forumAlura.domain.services.user.UserService;
import Enos.SpringProject.forumAlura.domain.services.user.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ReadTokenDTO> loginUser(@RequestBody @Valid LoginUserDTO loginUserDTO){
        try {
            var token = new UsernamePasswordAuthenticationToken(loginUserDTO.username(),loginUserDTO.password());
            var authentication = authenticationManager.authenticate(token);
            var tokenDTO = new ReadTokenDTO(tokenService.generateToken((User) authentication.getPrincipal()));
            return ResponseEntity.ok(tokenDTO);
        } catch (AuthenticationException e) {
            throw new CantAuthenticateException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ReadUserDTO> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO,
                                                    UriComponentsBuilder uriBuilder){
        ReadUserDTO readUserDTO = userService.registerUser(registerUserDTO);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(readUserDTO.id()).toUri();
        return ResponseEntity.created(uri).body(readUserDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDTO> getUserById(@PathVariable(name = "id") Long id){
        ReadUserDTO readUserDTO = userService.getUserById(id);
        return ResponseEntity.ok().body(readUserDTO);
    }

    @GetMapping("/detailed/{id}")
    public ResponseEntity<ReadDetailedUserDTO> getDetailedUserById(@PathVariable(name = "id") Long id){
        ReadDetailedUserDTO readDetailedUserDTO = userService.getDetailedUserById(id);
        return ResponseEntity.ok().body(readDetailedUserDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ReadUserDTO>> getAllUsers(@PageableDefault(size = 5) Pageable pageable){
        Page<ReadUserDTO> userDTOPage = userService.getAllUsers(pageable);
        return ResponseEntity.ok().body(userDTOPage);
    }
}
