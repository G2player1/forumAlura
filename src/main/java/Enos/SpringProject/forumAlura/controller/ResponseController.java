package Enos.SpringProject.forumAlura.controller;

import Enos.SpringProject.forumAlura.domain.models.response.dto.ReadResponseDTO;
import Enos.SpringProject.forumAlura.domain.models.response.dto.RegisterResponseDTO;
import Enos.SpringProject.forumAlura.domain.services.Response.ResponseService;
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
@RequestMapping("reponse")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/register")
    public ResponseEntity<ReadResponseDTO> registerResponse(@RequestBody @Valid RegisterResponseDTO registerResponseDTO,
                                                            UriComponentsBuilder uriBuilder){
        ReadResponseDTO readResponseDTO = responseService.registerResponse(registerResponseDTO);
        URI uri = uriBuilder.path("/response/{id}").buildAndExpand(readResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(readResponseDTO);
    }
}
