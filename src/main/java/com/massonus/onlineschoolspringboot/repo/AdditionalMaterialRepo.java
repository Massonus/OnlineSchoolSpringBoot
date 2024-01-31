package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.AdditionalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalMaterialRepo extends JpaRepository<AdditionalMaterial, Long> {


}
