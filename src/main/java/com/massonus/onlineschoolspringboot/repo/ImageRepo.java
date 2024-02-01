package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
