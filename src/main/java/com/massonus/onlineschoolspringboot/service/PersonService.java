package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.Course;
import com.massonus.onlineschoolspringboot.entity.Lecture;
import com.massonus.onlineschoolspringboot.entity.Person;
import com.massonus.onlineschoolspringboot.entity.Position;
import com.massonus.onlineschoolspringboot.repo.CourseRepo;
import com.massonus.onlineschoolspringboot.repo.PersonRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepo personRepo;
    private final CourseRepo courseRepo;
    private final LectureService lectureService;

    @Autowired
    public PersonService(PersonRepo personRepo, CourseRepo courseRepo, LectureService lectureService) {
        this.personRepo = personRepo;
        this.courseRepo = courseRepo;
        this.lectureService = lectureService;
    }

    Person person;

    public void createElementByUserForm(final String firstName, final String lastName, final String phone, final String email, Position position, final List<Integer> lectureIdList, final List<Integer> courseIdList) {
        person = new Person();

        person.setFirstName(firstName);

        person.setLastName(lastName);

        person.setPhone(phone);

        person.setEmail(email);

        person.setPosition(position);

        personRepo.save(person);

        lecturesForPerson(person, lectureIdList);

        coursesForPerson(person, courseIdList);

    }

    public Person refactorElementByUserForm(final Person person, final String firstName, final String lastName, final String phone, final String email, Position position, final List<Integer> lectureIdList, final List<Integer> courseIdList) {

        person.setFirstName(firstName);

        person.setLastName(lastName);

        person.setPhone(phone);

        person.setEmail(email);

        person.setPosition(position);

        personRepo.save(person);

        lecturesForPerson(person, lectureIdList);

        coursesForPerson(person, courseIdList);

        return person;

    }

    public Person createElementAuto(final Course course) {
        person = new Person();
        Random random = new Random();
        long id = random.nextInt(1, 50);

        if (id < 10 || id > 40) {
            person.setFirstName("John");
            person.setLastName("Smith");
            person.setPhone("Samsung");
            person.setPosition(Position.TEACHER);
        } else if (id < 20 || id > 30) {
            person.setFirstName("Nick");
            person.setLastName("Nikolos");
            person.setPhone("Xiaomi");

            person.setPosition(Position.STUDENT);
        } else {
            person.setFirstName("Max");
            person.setLastName("Collins");
            person.setPhone("iPhone");
            person.setPosition(Position.TEACHER);
        }
        person.setEmail(generateRandomString() + "@gmail.com");

        person.getCourses().add(course);
        personRepo.save(person);
        person.setLectures(createAndFillLecturesForPerson(course, person));

        return person;
    }

    private void coursesForPerson(final Person person, final List<Integer> courseIdList) {

        for (Integer id : courseIdList) {
            Course courseById = courseRepo.findById(id.longValue()).get();
            courseById.getPeople().add(person);
            person.getCourses().add(courseById);
        }
    }

    private void lecturesForPerson(final Person person, final List<Integer> lectureIdList) {

        for (Integer id : lectureIdList) {
            Lecture lectureById = lectureService.getLectureById(id).get();
            lectureById.setPerson(person);
        }
    }

    private List<Lecture> createAndFillLecturesForPerson(final Course course, final Person person) {
        List<Lecture> materials = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 5);
        for (int i = 0; i < lengthMas; i++) {
            Lecture elementAuto = lectureService.createElementAuto(course, person);
            materials.add(elementAuto);
        }
        return materials;
    }

    private String generateRandomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public void savePerson(final Person person) {
        personRepo.save(person);
    }

    public List<Person> getPeopleList() {
        return personRepo.findAll();
    }

    public Optional<Person> getPersonById(final long id) {
        return personRepo.findById(id);
    }

    public void deletePerson(final long id) {
        personRepo.deleteById(id);
    }

    public List<Person> sortPeopleById(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.toList());
    }

    public void printFilteredEmails(List<Person> people) {
        people.stream()
                .filter(t -> !t.getLastName().startsWith("N"))
                .forEach(System.out::println);
    }

    public List<String> emailsToList(List<Person> people) {
        return people.stream()
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }

    public void printEmailAndFullName(List<Person> people) {
        people.stream()
                .map(a -> a.getFirstName() + " " + a.getLastName() + ": " + a.getEmail())
                .forEach(System.out::println);
    }

    public boolean writeEmailsToTheFile(List<String> collect) {
        Path path = Path.of("src/main/resources/emails.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(collect.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public List<String> getEmailsFromFile() {
        List<String> emails = new ArrayList<>();
        Path path = Paths.get("src/main/resources/emails.txt");
        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String currentValue;
            while ((currentValue = reader.readLine()) != null) {
                emails.add(currentValue);
            }
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        return emails;
    }

}
