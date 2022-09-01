package com.opencourse.media.services;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencourse.media.entities.Image;
import com.opencourse.media.exceptions.ImageNotFoundException;
import com.opencourse.media.repos.ImageRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepo repo;

    public String addImage(MultipartFile file) throws IOException{

        Image image=new Image();
        image.setImage(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
        repo.save(image);
        return image.getId();
    }


    public byte[] getImage(String id){
        
        //make sure image exists
        Image image=repo.findById(id)
        .orElseThrow(()->new ImageNotFoundException(id));

        //return content
        return image.getImage().getData();

    }
}
