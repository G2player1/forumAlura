package Enos.SpringProject.forumAlura.domain.models.user;

import Enos.SpringProject.forumAlura.domain.models.profile.Profile;
import Enos.SpringProject.forumAlura.domain.models.response.Response;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import Enos.SpringProject.forumAlura.domain.models.user.dto.RegisterUserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true,nullable = false)
    private String username;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoles role;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Profile> profiles;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Topic> topics;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Response> responses;
    @Column(name = "active",nullable = false)
    private Integer active;

    public User(RegisterUserDTO registerUserDTO){
        profiles = new ArrayList<>();
        this.username = registerUserDTO.username();
        this.email = registerUserDTO.email();
        this.password = new BCryptPasswordEncoder().encode(registerUserDTO.password());
        this.role = UserRoles.fromString(registerUserDTO.role());
        this.active = 1;
    }

    public void deleteUser(){
        this.active = 0;
    }

    public void addProfile(Profile profile){
        if(profile == null) throw new RuntimeException("Invalid profile");
        profiles.add(profile);
    }

    public void addTopic(Topic topic){
        if(topic == null) throw new RuntimeException("Invalid topic");
        topics.add(topic);
    }

    public void addResponse(Response response){
        if(response == null) throw new RuntimeException("Invalid response");
        responses.add(response);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active == 1;
    }
}
