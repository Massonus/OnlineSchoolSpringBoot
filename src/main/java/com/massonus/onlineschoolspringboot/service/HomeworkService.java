package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.Homework;
import com.massonus.onlineschoolspringboot.entity.Lecture;
import com.massonus.onlineschoolspringboot.repo.HomeworkRepo;
import com.massonus.onlineschoolspringboot.repo.LectureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class HomeworkService {

    private final HomeworkRepo homeworkRepo;
    private final LectureRepo lectureRepo;

    @Autowired
    public HomeworkService(HomeworkRepo homeworkRepo, LectureRepo lectureRepo) {
        this.homeworkRepo = homeworkRepo;
        this.lectureRepo = lectureRepo;
    }

    Homework homework;

    public Homework createElementByUserForm(final String task, final Long lectureId) {
        homework = new Homework();

        homework.setTask(task);
        Lecture lectureById = lectureRepo.findById(lectureId).orElse(null);
        homework.setLecture(lectureById);

        return homework;
    }

    Homework createElementAuto() {
        homework = new Homework();
        Random random = new Random();
        long id = random.nextInt(1, 50);

        if (id < 10 || id > 40) {
            homework.setTask("Doing first and second");
        } else if (id < 20 || id > 30) {
            homework.setTask("Doing last");
        } else {
            homework.setTask("No homework!!!");
        }
        return homework;
    }

    public void saveHomework(final Homework homework) {
        homeworkRepo.save(homework);
    }

    public List<Homework> getHomeworkList() {
        return homeworkRepo.findAll();
    }

    public Optional<Homework> getHomeworkById(final long id) {
        return homeworkRepo.findById(id);
    }

    public void deleteHomework(final long id) {
        homeworkRepo.deleteById(id);
    }

    /*public List<Homework> sortHomeworkByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .sorted(Comparator.comparing(Homework::getLectureId))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Homework>> groupHomeworksByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .collect(Collectors.groupingBy(Homework::getLectureId));
    }*/
}
