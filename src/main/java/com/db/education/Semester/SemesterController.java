package com.db.education.Semester;

import com.db.education.Course.entity.CourseTime;
import com.db.education.Course.entity.SemesterCourse;
import com.db.education.Course.entity.StudentCourse;
import com.db.education.Course.messages.RegisterCourseRequest;
import com.db.education.Course.messages.ScheduleModel;
import com.db.education.Course.repository.CourseTimeRepository;
import com.db.education.Course.service.CourseService;
import com.db.education.Semester.entity.Semester;
import com.db.education.Semester.service.SemesterService;
import com.db.education.Time.messages.SemesterCourseTimeReponse;
import com.db.education.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semester")
public class SemesterController {
    @Autowired
    UserService userService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseTimeRepository courseTimeRepository;

    @GetMapping("/list")
    public List<Semester> getCurrentSemesters() {
        return semesterService.getSemestersByCurrentUser();

    }

    @GetMapping("/marks/{id}")
    public List<StudentCourse> getMarksBySemester(@PathVariable("id") Long id) {
        return courseService.getCurrentUserMarksBySemester(id);
    }

    @PostMapping("/course/register")
    public List<RegisterCourseRequest> registerCourse(@RequestBody List<RegisterCourseRequest> registerCourseRequests) {
        List<SemesterCourse> tempCourseTime = courseService.getCourseTimeListById(registerCourseRequests);
        courseService.saveCourseTime(registerCourseRequests);
        return registerCourseRequests;

    }

    @GetMapping("/schedule")
    public List<ScheduleModel> getWeeklySchedule() {
        return courseService.getSchdule(courseService.getCurrentUserSemesterWeeklySchedule(semesterService.getCurrentSemesterId()));
    }

    @GetMapping("/registered")
    public List<RegisterCourseRequest> getRegistered() {
        return courseService.getRegisteredCourse();
    }


}
