package com.opencourse.media.apis;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencourse.media.services.MYoutubeService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/video")
@AllArgsConstructor
public class VideoController {
    
    private final MYoutubeService service;

    //only teachers && admin 
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVide(@RequestParam(name = "file",required = true) MultipartFile file) throws IOException, GeneralSecurityException{
        String videoId=service.uploadVideo(file);
        return ResponseEntity.ok(videoId);

    }

    
}
