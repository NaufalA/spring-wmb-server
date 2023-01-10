package com.warungmakanbahari.warungmakanbahari.shared.services;

import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;

public interface ServiceGet<T, ID> {
    T getById(ID id);

    PagedResponse<T> getAll(Integer page, Integer size);
}
