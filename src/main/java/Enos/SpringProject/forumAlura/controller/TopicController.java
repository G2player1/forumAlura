package Enos.SpringProject.forumAlura.controller;

import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadDetailedTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.RegisterTopicDTO;
import Enos.SpringProject.forumAlura.domain.services.topic.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/register")
    public ResponseEntity<ReadTopicDTO> registerTopic(@RequestBody @Valid RegisterTopicDTO registerTopicDTO,
                                                      UriComponentsBuilder uriBuilder){
        ReadTopicDTO readTopicDTO = topicService.registerTopic(registerTopicDTO);
        URI uri = uriBuilder.path("/topic/{id}").buildAndExpand(readTopicDTO.id()).toUri();
        return ResponseEntity.created(uri).body(readTopicDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadDetailedTopicDTO> getTopicById(@PathVariable(name = "id") Long id){
        ReadDetailedTopicDTO readDetailedTopicDTO = topicService.getTopicById(id);
        return ResponseEntity.ok().body(readDetailedTopicDTO);
    }
}
