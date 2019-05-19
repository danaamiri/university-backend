package com.db.education.Course.messages;

import com.db.education.Time.entity.Time;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class RegisterCourseRequest implements Serializable {
    @NotNull
    private Long courseId;

    @NotNull
    private boolean Status;

    @Nullable
    private String error;

    @Nullable
    private String name;

    @Nullable
    private List<Time> time;

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegisterCourseRequest() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
