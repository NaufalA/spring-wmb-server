package com.warungmakanbahari.warungmakanbahari.shared.services;

public interface ServiceCreate<T, DTO> {
    T create(DTO dto);
}
