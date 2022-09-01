package com.opencourse.media.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.opencourse.media.entities.Image;

@Repository
public interface ImageRepo extends MongoRepository<Image,String>{
    
}
