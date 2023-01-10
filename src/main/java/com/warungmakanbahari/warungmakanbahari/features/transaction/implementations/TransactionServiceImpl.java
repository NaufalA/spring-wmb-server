package com.warungmakanbahari.warungmakanbahari.features.transaction.implementations;

import com.warungmakanbahari.warungmakanbahari.features.menu.MenuService;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuPriceEntity;
import com.warungmakanbahari.warungmakanbahari.features.table.entities.TableEntity;
import com.warungmakanbahari.warungmakanbahari.features.transaction.TransactionService;
import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.AddTransactionRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.TransactionDetailDto;
import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.TransactionDto;
import com.warungmakanbahari.warungmakanbahari.features.transaction.entities.CustomerEntity;
import com.warungmakanbahari.warungmakanbahari.features.transaction.entities.TransactionDetailEntity;
import com.warungmakanbahari.warungmakanbahari.features.transaction.entities.TransactionEntity;
import com.warungmakanbahari.warungmakanbahari.features.transaction.repositories.CustomerRepository;
import com.warungmakanbahari.warungmakanbahari.features.transaction.repositories.TransactionRepository;
import com.warungmakanbahari.warungmakanbahari.shared.classes.SearchCriteria;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.NotFoundException;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceGet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final ServiceGet<TableEntity, Long> tableService;
    private final MenuService menuService;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            CustomerRepository customerRepository,
            ServiceGet<TableEntity, Long> tableService,
            MenuService menuService) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.tableService = tableService;
        this.menuService = menuService;
    }

    @Transactional
    public TransactionDto create(AddTransactionRequestDto addTransactionDto) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionDate(addTransactionDto.getTransactionDate());

        CustomerEntity customer = new CustomerEntity();

        Optional<CustomerEntity> existingCustomer = customerRepository.findByName(addTransactionDto.getCustomerName());
        if (existingCustomer.isPresent()) {
            customer = existingCustomer.get();
        } else {
            customer.setName(addTransactionDto.getCustomerName());
            customer = customerRepository.save(customer);
        }
        transaction.setCustomer(customer);

        TableEntity table = tableService.getById(addTransactionDto.getTableId());
        transaction.setTable(table);

        List<TransactionDetailEntity> transactionDetails = addTransactionDto.getDetailList()
                .stream()
                .map(detail -> {
                    TransactionDetailEntity transactionDetail = new TransactionDetailEntity();
                    transactionDetail.setQuantity(detail.getQuantity());
                    MenuPriceEntity price = menuService.getPriceById(detail.getMenuPriceId());
                    transactionDetail.setMenuPrice(price);

                    return transactionDetail;
                }).collect(Collectors.toList());

        transaction.setTransactionDetails(transactionDetails);

        transaction = transactionRepository.save(transaction);

        return mapToDto(transaction, true);
    }

    public PagedResponse<TransactionDto> getAll(Integer page, Integer size) {
        Pageable pageable;
        if (size <= 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<TransactionEntity> transactions = transactionRepository.findAll(pageable);

        List<TransactionDto> menuResponses = transactions.get()
                .map(transaction -> mapToDto(transaction, false))
                .collect(Collectors.toList());

        return new PagedResponse<>(transactions, menuResponses);
    }

    @Override
    public PagedResponse<TransactionDto> getAll(Integer page, Integer size, List<SearchCriteria> criteria) {
        throw new RuntimeException("Unimplemented");
    }

    public TransactionDto getById(Long id) {
        Optional<TransactionEntity> transaction = transactionRepository.findById(id);

        if (transaction.isEmpty()) {
            throw new NotFoundException("Transaction Data Not Found");
        }

        return mapToDto(transaction.get(), true);
    }

    private TransactionDto mapToDto(TransactionEntity transaction, Boolean withDetails) {
        if (!withDetails) {
            return new TransactionDto(
                    transaction.getId(),
                    transaction.getTransactionDate(),
                    transaction.getCustomer().getName(),
                    transaction.getTable().getName()
            );
        }

        List<TransactionDetailDto> details = transaction.getTransactionDetails()
                .stream()
                .map(detail -> new TransactionDetailDto(
                        detail.getId(),
                        detail.getQuantity(),
                        detail.getMenuPrice().getMenu().getId(),
                        detail.getMenuPrice().getMenu().getName(),
                        detail.getMenuPrice().getUnitPrice(),
                        detail.getQuantity() * detail.getMenuPrice().getUnitPrice()
                )).collect(Collectors.toList());

        return new TransactionDto(
                transaction.getId(),
                transaction.getTransactionDate(),
                transaction.getCustomer().getName(),
                transaction.getTable().getName(),
                details
        );
    }
}
