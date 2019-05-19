package com.db.education.Course.service;

import com.db.education.Course.entity.CourseTime;
import com.db.education.Course.entity.SemesterCourse;
import com.db.education.Course.entity.StudentCourse;
import com.db.education.Course.messages.RegisterCourseRequest;
import com.db.education.Course.messages.ScheduleModel;
import com.db.education.Course.repository.CourseTimeRepository;
import com.db.education.Course.repository.SemesterCourseRepository;
import com.db.education.Course.repository.StudentCourseRepository;
import com.db.education.Semester.entity.Semester;
import com.db.education.Time.entity.Time;
import com.db.education.Time.messages.CouresTimeErrors;
import com.db.education.Time.messages.SemesterCourseTimeReponse;
import com.db.education.User.UserService;
import com.db.education.User.entity.User;
import com.db.education.common.messages.FilterRequest;
import com.db.education.common.messages.filter.FilterResponse;
import com.db.education.common.paging.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    SemesterCourseRepository semesterCourseRepository;

    @Autowired
    UserService userService;

    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Autowired
    CourseTimeRepository courseTimeRepository;


    public FilterResponse<SemesterCourse> getThisSemesterForDepartment(Long semesterId, Long deptId, FilterRequest filterRequest) {
        FilterResponse filterResponse = new FilterResponse();
        List<SemesterCourse> semesterCoursePage = semesterCourseRepository.findBySemester_id(semesterId);
        List<SemesterCourse> semesterCoursesList = semesterCoursePage;
        List<SemesterCourse> deptSemesterCourses = new ArrayList<>();
        semesterCoursesList.iterator().forEachRemaining(semesterCourse -> {
                    if (semesterCourse.getCourse().getDepartment().getId() == deptId) {
                        deptSemesterCourses.add(semesterCourse);
                    }
                }
        );
        Paginator paginator = new Paginator(filterRequest.getStartIndex(), filterRequest.getCount(), deptSemesterCourses.size());
        System.out.println(paginator.getCount());
        if (paginator.getStartIndex() != -1) {
            semesterCoursesList = deptSemesterCourses.subList(paginator.getStartIndex(), paginator.getCount());
        }


        filterResponse.setList(semesterCoursesList);
        filterResponse.setTotalCount((long) semesterCoursesList.size());
        return filterResponse;

    }

    public List<StudentCourse> getCurrentUserMarksBySemester(Long semesterId) {
        User user = userService.getCurrentUser();
        List<StudentCourse> studentCoursesList = studentCourseRepository.findAllByUser_Id(user.getId());
        List<StudentCourse> studentCoursesBySemeter = new ArrayList<>();
        studentCoursesList.iterator().forEachRemaining(studentCourse -> {
            if (studentCourse.getSemesterCourse().getSemester().getId() == semesterId) {
                studentCoursesBySemeter.add(studentCourse);
            }
        });

        return studentCoursesBySemeter;
    }

    public List<SemesterCourse> getCourseTimeListById(List<RegisterCourseRequest> registerCourseRequests) {
        List<SemesterCourse> semesterCourses = new ArrayList<>();
        registerCourseRequests.iterator().forEachRemaining(rcR -> {
            semesterCourses.add(semesterCourseRepository.getOne(rcR.getCourseId()));
        });

        return semesterCourses;
    }


    public List<RegisterCourseRequest> checkCourse(List<SemesterCourse> courseTimes) {
        CouresTimeErrors couresTimeErrors = CouresTimeErrors.CONFLICT;
        RegisterCourseRequest trcr = new RegisterCourseRequest();
        List<RegisterCourseRequest> registerCourseRequests = new ArrayList<>();
        List<SemesterCourse> temp = courseTimes;
        courseTimes.iterator().forEachRemaining(pct -> {
            temp.iterator().forEachRemaining(cct -> {
                pct.getTime().iterator().forEachRemaining(pt -> {
                    cct.getTime().iterator().forEachRemaining(ct -> {
                        if (pt.getId() == ct.getId()) {
                            trcr.setCourseId(pct.getId());
                            trcr.setStatus(false);
                            trcr.setError(couresTimeErrors.toString());
                            registerCourseRequests.add(trcr);
                            trcr.setCourseId(cct.getId());
                            trcr.setStatus(false);
                            trcr.setError(couresTimeErrors.toString());
                            registerCourseRequests.add(trcr);
                        }
                    });
                });
            });
        });
        return registerCourseRequests;
    }

    @Transactional
    public void saveCourseTime(List<RegisterCourseRequest> registerCourseRequests) {
        User user = userService.getCurrentUser();
        List<StudentCourse> studentCoursesList = new ArrayList<>();
        registerCourseRequests.iterator().forEachRemaining(courseTime -> {
            if (courseTime.isStatus()) {
                Optional<StudentCourse> tempStudentCourse = studentCourseRepository.findByUser_idAndSemesterCourse_Id(user.getId(), courseTime.getCourseId());
                if (!tempStudentCourse.isPresent()) {
                    StudentCourse tStudentCourse = new StudentCourse();
                    tStudentCourse.setSemesterCourse(semesterCourseRepository.getOne(courseTime.getCourseId()));
                    tStudentCourse.setUser(user);
                    studentCoursesList.add(tStudentCourse);
                }

            } else {
                Optional<StudentCourse> tempStudentCourse = studentCourseRepository.findByUser_idAndSemesterCourse_Id(user.getId(), courseTime.getCourseId());
                if (tempStudentCourse.isPresent()) {
                    studentCourseRepository.deleteById(tempStudentCourse.get().getId());
                }
            }

        });
        studentCourseRepository.saveAll(studentCoursesList);
    }

    public List<SemesterCourseTimeReponse> getCurrentUserSemesterWeeklySchedule(Long semesterId) {
        User user = userService.getCurrentUser();
        List<StudentCourse> studentCourses = studentCourseRepository.findAllByUser_Id(user.getId());
        List<SemesterCourseTimeReponse> weeklySchedule = new ArrayList<>();
        studentCourses.iterator().forEachRemaining(x -> {
            if (x.getSemesterCourse().getSemester().getId() == semesterId) {
                x.getSemesterCourse().getTime().iterator().forEachRemaining(time -> {
                    SemesterCourseTimeReponse tempSCT = new SemesterCourseTimeReponse(time, x.getSemesterCourse());
                    weeklySchedule.add(tempSCT);
                });
            }
        });

        return weeklySchedule;


    }

    public List<ScheduleModel> getSchdule(List<SemesterCourseTimeReponse> semesterCourseTimeReponses) {
        List<ScheduleModel> scheduleModels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            ScheduleModel scheduleModel = new ScheduleModel();
            switch (finalI) {
                case 0:
                    scheduleModel.setWeekday("Saturday");
                    break;
                case 1:
                    scheduleModel.setWeekday("Sunday");
                    break;
                case 2:
                    scheduleModel.setWeekday("Monday");
                    break;
                case 3:
                    scheduleModel.setWeekday("Tuesday");
                    break;
            }
            semesterCourseTimeReponses.iterator().forEachRemaining(sctr -> {
                if (sctr.getTime().getDay() == finalI) {
                    Time time = sctr.getTime();
                    switch (time.getStartTime().toLocalTime().getHour() + 1) {
                        case 8:
                            scheduleModel.setFirstTime(sctr.getSemesterCourse());
                            break;
                        case 10:
                            scheduleModel.setSecondTime(sctr.getSemesterCourse());
                            break;
                        case 14:
                            scheduleModel.setThirdTime(sctr.getSemesterCourse());
                            break;
                        case 16:
                            scheduleModel.setForthTime(sctr.getSemesterCourse());
                            break;
                    }
                }
            });
            scheduleModels.add(scheduleModel);
        }
        return scheduleModels;
    }

    public List<RegisterCourseRequest> getRegisteredCourse() {
        User user = userService.getCurrentUser();
        List<RegisterCourseRequest> studentCourses = new ArrayList<>();
        List<StudentCourse> serverStudentCourse = studentCourseRepository.findAllByUser_Id(user.getId());
        serverStudentCourse.iterator().forEachRemaining(x -> {
            if(x.getMark() == null){
                RegisterCourseRequest registerCourseRequest = new RegisterCourseRequest();
                registerCourseRequest.setError("");
                registerCourseRequest.setCourseId(x.getSemesterCourse().getId());
                registerCourseRequest.setStatus(true);
                registerCourseRequest.setName(x.getSemesterCourse().getCourse().getName());
                registerCourseRequest.setTime(x.getSemesterCourse().getTime());
                studentCourses.add(registerCourseRequest);
            }
        });

        return studentCourses;

    }


}
