package com.db.education.Semester.service;

import com.db.education.Semester.entity.Semester;
import com.db.education.Semester.repository.SemesterRepository;
import com.db.education.User.UserService;
import com.db.education.User.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {
    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    UserService userService;

    public List<Semester> getSemestersByCurrentUser() {
        User user = userService.getCurrentUser();
        List<Semester> allSemesters = semesterRepository.findAll();
        int userStartSemesterIndex = allSemesters.indexOf(user.getStartSemester());
        List<Semester> userSemesters;
        if (user.getEndSemester() != null) {
            userSemesters = allSemesters.subList(userStartSemesterIndex, allSemesters.size() - userStartSemesterIndex);
        } else {
            int currentSemesterIndex = allSemesters.indexOf(semesterRepository.getOne(getCurrentSemesterId()));
            System.out.println(allSemesters.size());
            userSemesters = allSemesters.subList(userStartSemesterIndex, currentSemesterIndex+1);
        }

        return userSemesters;
    }
    private Long getCurrentSemesterId() {
        return semesterRepository.getCurrentSemester();
    }
}
