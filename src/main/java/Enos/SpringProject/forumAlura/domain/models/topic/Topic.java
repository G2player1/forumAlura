package Enos.SpringProject.forumAlura.domain.models.topic;

import Enos.SpringProject.forumAlura.domain.models.course.Course;
import Enos.SpringProject.forumAlura.domain.models.response.Response;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.RegisterTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "topic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "message",nullable = false,length = 5000)
    private String message;
    @Column(name = "creation_date",nullable = false)
    private LocalDate creationDate;
    @Column(name = "status",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TopicStatus status;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "course_id")
    private Course course;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "topic",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Response> responseList;

    public Topic(RegisterTopicDTO registerTopicDTO){
        this.title = registerTopicDTO.title();
        this.message = registerTopicDTO.message();
        this.creationDate = registerTopicDTO.date();
        this.status = TopicStatus.UNSOLVED;
    }

    public void setCourse(Course course){
        if (course == null) throw new RuntimeException("invalid course");
        this.course = course;
    }

    public void setUser(User user){
        if (user == null) throw new RuntimeException("invalid user");
        this.user = user;
    }

    public void addResponse(Response response){
        if(response == null) throw new RuntimeException("invalid response");
        responseList.add(response);
        this.status = TopicStatus.SOLVED;
    }
}