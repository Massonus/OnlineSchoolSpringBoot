package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepo extends JpaRepository<Homework, Long> {


}
