package com.opencourse.media.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;

import java.io.IOException;

import java.security.GeneralSecurityException;

@Service
public class MYoutubeService {     
    private YoutubeProp properties;
    private MYoutubeService(YoutubeProp prop){
        this.properties=prop;
    }

    private static final String APPLICATION_NAME = "opencourse";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public Credential authorize(final NetHttpTransport httpTransport) throws IOException, GeneralSecurityException {        
        Credential access = new Credential
        .Builder(BearerToken.queryParameterAccessMethod())
        .setJsonFactory(JSON_FACTORY)
        .setTokenServerEncodedUrl(properties.getServerEncodedUrl())
        .setTransport(GoogleNetHttpTransport.newTrustedTransport())
        .setClientAuthentication(new BasicAuthentication(properties.getClientId(), properties.getClientSecret()))
        .build()
        .setAccessToken(properties.getAccessToken())
        .setRefreshToken(properties.getRefreshToken())
        .setExpirationTimeMilliseconds(0L);
        return access;
    }

    public YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }


    public String uploadVideo(MultipartFile file) throws IOException, GeneralSecurityException{

        YouTube youtubeService = getService();

        Video video = new Video();
        
        // Add the snippet object property to the Video object.
        VideoSnippet snippet = new VideoSnippet();
        snippet.setCategoryId("22");
        snippet.setDescription("");
        snippet.setTitle("");
        video.setSnippet(snippet);
        
        // Add the status object property to the Video object.
        VideoStatus status = new VideoStatus();
        status.setPrivacyStatus("unlisted");
        video.setStatus(status);

        InputStreamContent content=new InputStreamContent("application/octet-stream", file.getInputStream());
        content.setLength(file.getSize());
        YouTube.Videos.Insert request = youtubeService.videos().insert("snippet,status", video, content);
        Video response = request.execute();
        return response.getId();
    }
}
