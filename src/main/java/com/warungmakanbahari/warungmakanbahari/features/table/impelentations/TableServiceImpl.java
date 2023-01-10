package com.warungmakanbahari.warungmakanbahari.features.table.impelentations;

import com.warungmakanbahari.warungmakanbahari.features.table.TableService;
import com.warungmakanbahari.warungmakanbahari.features.table.dtos.AddTableRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.table.entities.TableEntity;
import com.warungmakanbahari.warungmakanbahari.features.table.repositories.TableRepository;
import com.warungmakanbahari.warungmakanbahari.shared.classes.SearchCriteria;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    private final ModelMapper modelMapper;

    public TableServiceImpl(TableRepository tableRepository, ModelMapper modelMapper) {
        this.tableRepository = tableRepository;
        this.modelMapper = modelMapper;
    }

    public TableEntity create(AddTableRequestDto addTableRequestDto) {
        TableEntity table = modelMapper.map(addTableRequestDto, TableEntity.class);

        return tableRepository.save(table);
    }

    public PagedResponse<TableEntity> getAll(Integer page, Integer size) {
        Pageable pageable;
        if (size <= 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<TableEntity> tablePage = tableRepository.findAll(pageable);

        return new PagedResponse<>(tablePage);
    }

    @Override
    public PagedResponse<TableEntity> getAll(Integer page, Integer size, List<SearchCriteria> criteria) {
        throw new RuntimeException("Unimplemented");
    }

    public TableEntity getById(Long id) {
        Optional<TableEntity> table = tableRepository.findById(id);

        if (table.isEmpty()) {
            throw new NotFoundException("Table Data Not Found");
        }

        return table.get();
    }

    public TableEntity update(Long id, TableEntity updatedTable) {
        Optional<TableEntity> table = tableRepository.findById(id);

        if (table.isEmpty()) {
            throw new NotFoundException("Table Data Not Found");
        }

        table.get().setName(updatedTable.getName());
        table.get().setAvailability(updatedTable.getAvailability());

        return tableRepository.save(table.get());
    }

    public Long delete(Long id) {
        Optional<TableEntity> table = tableRepository.findById(id);

        if (table.isEmpty()) {
            throw new NotFoundException("Table Data Not Found");
        }

        table.get().setDeletedAt(Instant.now());

        return tableRepository.save(table.get()).getId();
    }
}
