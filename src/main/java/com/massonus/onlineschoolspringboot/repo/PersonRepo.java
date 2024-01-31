package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {


}
