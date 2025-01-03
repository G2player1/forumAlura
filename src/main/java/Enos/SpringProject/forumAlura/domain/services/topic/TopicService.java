package Enos.SpringProject.forumAlura.domain.services.topic;

import Enos.SpringProject.forumAlura.domain.exceptions.RegisterTopicException;
import Enos.SpringProject.forumAlura.domain.models.course.Course;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadDetailedTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.RegisterTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.repositories.ICourseRepository;
import Enos.SpringProject.forumAlura.domain.repositories.ITopicRepository;
import Enos.SpringProject.forumAlura.domain.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICourseRepository courseRepository;

    @Transactional
    public ReadTopicDTO registerTopic(RegisterTopicDTO registerTopicDTO) {
        User user = userRepository.findByIdAndActive(registerTopicDTO.user_id(), 1);
        Course course = courseRepository.findByIdAndActive(registerTopicDTO.course_id(), 1);
        if (user == null) throw new EntityNotFoundException("user not found");
        if (course == null) throw new EntityNotFoundException("course not found");
        if (topicRepository.findByTitleAndMessageAndActive(registerTopicDTO.title(), registerTopicDTO.message(), 1) != null) throw new RegisterTopicException("already exists a topic with the same title and message");
        Topic topic = new Topic(registerTopicDTO);
        topic.setUser(user);
        topic.setCourse(course);
        user.addTopic(topic);
        course.addTopic(topic);
        topic = topicRepository.save(topic);
        return new ReadTopicDTO(topic);
    }

    public ReadDetailedTopicDTO getTopicById(Long id) {
        Topic topic = topicRepository.findByIdAndActive(id, 1);
        if (topic == null) throw new EntityNotFoundException("topic not found");
        return new ReadDetailedTopicDTO(topic);
    }
}
