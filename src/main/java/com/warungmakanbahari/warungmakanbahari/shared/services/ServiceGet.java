package com.warungmakanbahari.warungmakanbahari.shared.services;

import com.warungmakanbahari.warungmakanbahari.shared.classes.SearchCriteria;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;

import java.util.List;

public interface ServiceGet<T, ID> {
    T getById(ID id);

    PagedResponse<T> getAll(Integer page, Integer size);

    PagedResponse<T> getAll(Integer page, Integer size, List<SearchCriteria> criteria);
}
