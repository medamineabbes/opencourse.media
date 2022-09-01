package com.opencourse.media.apis;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencourse.media.services.ImageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/image")
@AllArgsConstructor
public class ImageController {

    private final ImageService service;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestBody(required = true) MultipartFile file) throws IOException{
        return ResponseEntity.ok(service.addImage(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPage(@PathVariable(required = true) String id){
        return ResponseEntity.ok(service.getImage(id));
    }

}
