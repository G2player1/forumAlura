package Enos.SpringProject.forumAlura.controller;

import Enos.SpringProject.forumAlura.domain.exceptions.CantAuthenticateException;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.models.user.dto.LoginUserDTO;
import Enos.SpringProject.forumAlura.domain.services.user.UserService;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadTokenDTO;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadUserDTO;
import Enos.SpringProject.forumAlura.domain.models.user.dto.RegisterUserDTO;
import Enos.SpringProject.forumAlura.domain.services.user.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
        var readUserDTO = userService.registerUser(registerUserDTO);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(readUserDTO.id()).toUri();
        return ResponseEntity.created(uri).body(readUserDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDTO> getUserById(@PathVariable(name = "id") Long id){
        var readUserDTO = userService.getUserById(id);
        return ResponseEntity.ok().body(readUserDTO);
    }
}
