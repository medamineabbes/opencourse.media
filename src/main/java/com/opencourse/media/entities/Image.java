package com.opencourse.media.entities;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("images")
public class Image {
    @Id
    private String id;
    
    private Binary image;
    
}
