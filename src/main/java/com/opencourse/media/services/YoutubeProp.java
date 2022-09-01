package com.opencourse.media.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "youtube")
@Component
@Data
public class YoutubeProp {
    private String accessToken;    
    private String refreshToken;
    private String clientId;
    private String clientSecret;
    private String serverEncodedUrl;
}
