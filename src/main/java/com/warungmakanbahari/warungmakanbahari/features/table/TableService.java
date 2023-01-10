package com.warungmakanbahari.warungmakanbahari.features.table;

import com.warungmakanbahari.warungmakanbahari.features.table.dtos.AddTableRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.table.entities.TableEntity;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceCreate;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceDelete;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceGet;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceUpdate;

public interface TableService extends ServiceCreate<TableEntity, AddTableRequestDto>, ServiceGet<TableEntity, Long>, ServiceUpdate<TableEntity, Long>, ServiceDelete<Long> {
}
