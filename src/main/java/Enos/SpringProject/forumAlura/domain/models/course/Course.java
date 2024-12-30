package Enos.SpringProject.forumAlura.domain.models.course;

import Enos.SpringProject.forumAlura.domain.models.course.dto.RegisterCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Topic> topicList;

    public Course(RegisterCourseDTO registerCourseDTO){
        this.name = registerCourseDTO.name();
        this.category = Category.fromString(registerCourseDTO.category());
    }

    public void addTopic(Topic topic){
        if (topic == null) throw new RuntimeException("Invalid topic");
        topicList.add(topic);
    }
}
