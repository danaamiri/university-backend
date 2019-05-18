package com.db.education.Course.messages;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RegisterCourseRequest implements Serializable {
    @NotNull
    private Long courseId;

    @NotNull
    private boolean Status;

    @Nullable
    private String error;

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
