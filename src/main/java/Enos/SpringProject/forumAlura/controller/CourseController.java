package Enos.SpringProject.forumAlura.controller;

import Enos.SpringProject.forumAlura.domain.models.course.dto.ReadCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.course.dto.ReadDetailedCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.course.dto.RegisterCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadTopicDTO;
import Enos.SpringProject.forumAlura.domain.services.course.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/register")
    private ResponseEntity<ReadCourseDTO> registerCourse(@RequestBody @Valid RegisterCourseDTO registerCourseDTO,
                                                         UriComponentsBuilder uriBuilder){
        ReadCourseDTO readCourseDTO = courseService.registerCourse(registerCourseDTO);
        URI uri = uriBuilder.path("/course/{id}").buildAndExpand(readCourseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(readCourseDTO);
    }
}
