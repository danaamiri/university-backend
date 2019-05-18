package com.db.education.common.messages.filter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FilterMapper {
    public FilterResponse mapFilter(Page<?> page) {
        FilterResponse filterResponse = new FilterResponse();
        filterResponse.setTotalCount(page.getTotalElements());
        filterResponse.setList(page.getContent());
        return filterResponse;
    }
}
