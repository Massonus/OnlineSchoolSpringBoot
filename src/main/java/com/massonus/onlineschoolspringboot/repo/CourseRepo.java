package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {


}
