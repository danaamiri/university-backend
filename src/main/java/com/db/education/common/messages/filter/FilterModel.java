package com.db.education.common.messages.filter;

import java.util.Set;

public class FilterModel {
    private String property;

    private Set<String> values;

    public FilterModel() {
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }
}
