package com.db.education.Course;

import com.db.education.Course.entity.SemesterCourse;
import com.db.education.Course.repository.SemesterCourseRepository;
import com.db.education.Course.service.CourseService;
import com.db.education.User.UserService;
import com.db.education.common.messages.FilterRequest;
import com.db.education.common.messages.filter.FilterMapper;
import com.db.education.common.messages.filter.FilterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("api/course")
@RestController
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class CourseController {
    @Autowired
    UserService userService;

    @Autowired
    SemesterCourseRepository semesterCourseRepository;

    @Autowired
    FilterMapper filterMapper;

    @Autowired
    CourseService courseService;

    @PostMapping("/list")
    public FilterResponse<SemesterCourse> getCourses(@Valid @RequestBody FilterRequest filterRequest) {
        Long deptId = userService.getCurrentUser().getDepartment().getId();
        return courseService.getThisSemesterForDepartment(1L, deptId,filterRequest);
    }

}
