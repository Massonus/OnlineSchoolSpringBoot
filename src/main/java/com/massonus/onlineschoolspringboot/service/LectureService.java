package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.*;
import com.massonus.onlineschoolspringboot.repo.CourseRepo;
import com.massonus.onlineschoolspringboot.repo.LectureRepo;
import com.massonus.onlineschoolspringboot.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LectureService {
    private final LectureRepo lectureRepo;
    private final AdditionalMaterialService materialsService;
    private final HomeworkService homeworkService;
    private final PersonRepo personRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public LectureService(LectureRepo lectureRepo, AdditionalMaterialService materialsService, HomeworkService homeworkService, PersonRepo personRepo, CourseRepo courseRepo) {
        this.lectureRepo = lectureRepo;
        this.materialsService = materialsService;
        this.homeworkService = homeworkService;
        this.personRepo = personRepo;
        this.courseRepo = courseRepo;
    }

    private Lecture lecture;

    public Lecture createElementByUserForm(final String name, final String description, final Long courseId, final Long personId) {

        lecture = new Lecture();
        lecture.setSubject(name);

        lecture.setDescription(description);

        Course courseById = courseRepo.findById(courseId).orElse(null);
        lecture.setCourse(courseById);

        Person personForLecture = personRepo.findById(personId).orElse(null);
        lecture.setPerson(personForLecture);

        return lecture;
    }

    public Lecture createElementAuto(final Course course, final Person person) {
        lecture = new Lecture();
        long size = lectureRepo.findAll().size();
        long id = size + 1;

        if (id < 10 || id > 40) {
            lecture.setSubject("Math");
            lecture.setDescription("About Math");
        } else if (id < 20 || id > 30) {
            lecture.setSubject("Geography");
            lecture.setDescription("About Geography");
        } else {
            lecture.setSubject("English");
            lecture.setDescription("About English");
        }
        lecture.setCourse(course);
        lecture.setPerson(person);
        lectureRepo.save(lecture);
        lecture.setMaterials(createAndFillMaterialsListForLecture(lecture));
        lecture.setHomeworks(createAndFillHomeworkListListForLecture(lecture));

        return lecture;
    }

    public List<AdditionalMaterial> createAndFillMaterialsListForLecture(final Lecture lecture) {
        List<AdditionalMaterial> materials = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 10);
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial elementAuto = materialsService.createElementAuto();
            elementAuto.setLecture(lecture);
            materialsService.saveMaterial(elementAuto);
            materials.add(elementAuto);
        }
        return materials;
    }

    public List<Homework> createAndFillHomeworkListListForLecture(final Lecture lecture) {
        List<Homework> homeworkList = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 10);
        for (int i = 0; i < lengthMas; i++) {
            Homework elementAuto = homeworkService.createElementAuto();
            elementAuto.setLecture(lecture);
            homeworkService.saveHomework(elementAuto);
            homeworkList.add(elementAuto);
        }
        return homeworkList;
    }

    public Lecture getById(List<Lecture> list, long id) {

        List<Lecture> collect = list.stream()
                .filter(l -> l.getId().equals(id))
                .toList();

        return collect.get(0);
    }

    public void saveLecture(final Lecture lecture) {
        lectureRepo.save(lecture);
    }

    public List<Lecture> getLectureList() {
        return lectureRepo.findAll();
    }

    public Optional<Lecture> getLectureById(final long id) {
        return lectureRepo.findById(id);
    }

    public void deleteLecture(final long id) {
        lectureRepo.deleteById(id);
    }

    public Lecture findFirstLecture(List<Lecture> lectures) {
        return lectures.stream()
                .max(Lecture::compareTo)
                .orElseThrow();
    }

    public Map<Person, List<Lecture>> groupLectureByPerson(List<Lecture> lectures) {
        return lectures.stream()
                .collect(Collectors.groupingBy(Lecture::getPerson));
    }

    public List<Lecture> sortLectureById(List<Lecture> lectures) {
        return lectures.stream()
                .sorted(Comparator.comparing(Lecture::getId))
                .collect(Collectors.toList());
    }

    public List<Person> getAllTeachers() {
        List<Person> allPeople = personRepo.findAll();
        return allPeople.stream()
                .filter(a -> a.getRole().toString().equals("TEACHER"))
                .collect(Collectors.toList());
    }
}
