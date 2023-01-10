package com.warungmakanbahari.warungmakanbahari.features.table;

import com.warungmakanbahari.warungmakanbahari.features.table.dtos.AddTableRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.table.entities.TableEntity;
import com.warungmakanbahari.warungmakanbahari.shared.constants.UrlMapping;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.CommonResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.TABLE_URL)
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> addTable(@RequestBody AddTableRequestDto dto) {
        TableEntity response = tableService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.CREATED),
                "Success Add Table",
                response
        ));
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        PagedResponse<TableEntity> response = tableService.getAll(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get All Table",
                response
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable("id") Long id) {
        TableEntity response = tableService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get Table",
                response
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable("id") Long id, @RequestBody TableEntity updatedTable) {
        TableEntity response = tableService.update(id, updatedTable);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Update Table",
                response
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable("id") Long id) {
        Long response = tableService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Delete Table",
                response
        ));
    }
}
