package com.warungmakanbahari.warungmakanbahari.shared.services;

public interface ServiceUpdate<T, ID> {
    T update(ID id, T updatedEntity);
}
