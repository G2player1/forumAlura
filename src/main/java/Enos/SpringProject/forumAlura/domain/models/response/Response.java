package Enos.SpringProject.forumAlura.domain.models.response;

import Enos.SpringProject.forumAlura.domain.models.response.dto.RegisterResponseDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reponse")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message",nullable = false,length = 5000)
    private String message;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(name = "creation_date",nullable = false)
    private LocalDate creationDate;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "solution",nullable = false,length = 5000)
    private String solution;
    @Column(name = "active",nullable = false)
    private Integer active;


    public Response(RegisterResponseDTO registerResponseDTO){
        this.message = registerResponseDTO.message();
        this.creationDate = registerResponseDTO.date();
        this.solution = registerResponseDTO.solution();
        this.active = 1;
    }

    public void setUser(User user){
        if (user == null) throw new RuntimeException("Invalid user");
        this.user = user;
    }

    public void setTopic(Topic topic){
        if (topic == null) throw new RuntimeException("Invalid topic");
        this.topic = topic;
    }

    public void deleteResponse(){
        this.active = 0;
    }

}
