package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepo extends JpaRepository<Lecture, Long> {


}
