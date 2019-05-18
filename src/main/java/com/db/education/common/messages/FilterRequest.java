package com.db.education.common.messages;

import com.db.education.common.messages.filter.FilterModel;
import com.db.education.common.messages.filter.OrderModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class FilterRequest {
    @NotNull
    private int startIndex;

    @NotNull
    private int count;

    private Set<FilterModel> filter;


    public FilterRequest() {
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<FilterModel> getFilter() {
        return filter;
    }

    public void setFilter(Set<FilterModel> filter) {
        this.filter = filter;
    }

}
