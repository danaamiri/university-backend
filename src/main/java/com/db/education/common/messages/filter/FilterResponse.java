package com.db.education.common.messages.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class FilterResponse<T> implements Serializable {
    private Long totalCount;

    private List<T> list;

    public FilterResponse() {
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
