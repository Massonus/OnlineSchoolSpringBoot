package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.Image;
import com.massonus.onlineschoolspringboot.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public void upload(final MultipartFile resource) throws IOException {
        Image createdFile = new Image();
        createdFile.setBytes(resource.getBytes());
        createdFile.setName(resource.getOriginalFilename());
        createdFile.setSize(resource.getSize());
        createdFile.setContentType(resource.getContentType());

        imageRepo.save(createdFile);
    }

    public Optional<Image> getImageById(final Long id) {

        return imageRepo.findById(id);

    }
}
