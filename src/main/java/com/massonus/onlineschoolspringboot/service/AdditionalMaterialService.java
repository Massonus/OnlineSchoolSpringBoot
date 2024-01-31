package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.AdditionalMaterial;
import com.massonus.onlineschoolspringboot.entity.Lecture;
import com.massonus.onlineschoolspringboot.entity.ResourceType;
import com.massonus.onlineschoolspringboot.repo.AdditionalMaterialRepo;
import com.massonus.onlineschoolspringboot.repo.LectureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdditionalMaterialService {

    private final AdditionalMaterialRepo materialsRepo;
    private final LectureRepo lectureRepo;

    @Autowired
    public AdditionalMaterialService(AdditionalMaterialRepo materialsRepo, LectureRepo lectureRepo) {
        this.materialsRepo = materialsRepo;
        this.lectureRepo = lectureRepo;
    }

    AdditionalMaterial material;

    public AdditionalMaterial createElementByUserForm(String task, ResourceType resourceType, Long lectureId) {
        material = new AdditionalMaterial();

        material.setTask(task);
        material.setResourceType(resourceType);
        Lecture lectureById = lectureRepo.findById(lectureId).orElse(null);
        material.setLecture(lectureById);

        return material;
    }

    AdditionalMaterial createElementAuto() {
        material = new AdditionalMaterial();
        long id = materialsRepo.findAll().size() + 1L;

        material.setTask("Material " + id);

        if (id % 2 == 0) {
            material.setResourceType(ResourceType.URL);
        } else if (id % 3 == 2) {
            material.setResourceType(ResourceType.VIDEO);
        } else {
            material.setResourceType(ResourceType.BOOK);
        }

        return material;
    }

    public void saveMaterial(final AdditionalMaterial material) {
        materialsRepo.save(material);
    }

    public List<AdditionalMaterial> getMaterialList() {
        return materialsRepo.findAll();
    }

    public Optional<AdditionalMaterial> getMaterialById(final long id) {
        return materialsRepo.findById(id);
    }

    public void deleteMaterial(final long id) {
        materialsRepo.deleteById(id);
    }

    public List<AdditionalMaterial> sortMaterialsById(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getId))
                .collect(Collectors.toList());
    }

    public List<AdditionalMaterial> sortMaterialsByType(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(a -> a.getResourceType().toString()))
                .collect(Collectors.toList());
    }

    public List<AdditionalMaterial> sortMaterialsByName(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getTask))
                .collect(Collectors.toList());
    }

    /*public List<AdditionalMaterial> sortMaterialsByLectureId(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getLectureId))
                .collect(Collectors.toList());
    }*/

    /*public Map<Integer, List<AdditionalMaterial>> groupingMaterialsAsMap(List<AdditionalMaterial> materials) {
        return materials.stream()
                .collect(Collectors.groupingBy(AdditionalMaterial::getLectureId));
    }*/
}
