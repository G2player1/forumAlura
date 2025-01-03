package Enos.SpringProject.forumAlura.domain.services.Response;

import Enos.SpringProject.forumAlura.domain.models.response.Response;
import Enos.SpringProject.forumAlura.domain.models.response.dto.ReadResponseDTO;
import Enos.SpringProject.forumAlura.domain.models.response.dto.RegisterResponseDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.repositories.IResponseRepository;
import Enos.SpringProject.forumAlura.domain.repositories.ITopicRepository;
import Enos.SpringProject.forumAlura.domain.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Autowired
    private IResponseRepository responseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ITopicRepository topicRepository;

    public ReadResponseDTO registerResponse(@Valid RegisterResponseDTO registerResponseDTO) {
        User user = userRepository.findByIdAndActive(registerResponseDTO.user_id(), 1);
        Topic topic = topicRepository.findByIdAndActive(registerResponseDTO.topic_id(), 1);
        if (user == null) throw new EntityNotFoundException("user not found");
        if (topic == null) throw new EntityNotFoundException("topic not found");
        Response response = new Response(registerResponseDTO);
        response.setTopic(topic);
        response.setUser(user);
        topic.addResponse(response);
        user.addResponse(response);
        response = responseRepository.save(response);
        return new ReadResponseDTO(response);
    }
}
