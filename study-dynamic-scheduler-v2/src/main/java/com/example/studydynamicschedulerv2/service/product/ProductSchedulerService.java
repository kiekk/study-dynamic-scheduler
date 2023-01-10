package com.example.studydynamicschedulerv2.service.product;

import com.example.studydynamicschedulerv2.dto.ProductSchedulerForm;
import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv2.exception.ApiException;
import com.example.studydynamicschedulerv2.repository.product.ProductSchedulerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductSchedulerService {

    private final ProductSchedulerRepository repository;
    private final ProductService productService;

    public ProductSchedulerService(ProductSchedulerRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public List<ProductScheduler> search() {
        return repository.findAll();
    }

    public ProductScheduler fetch(String id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException(ApiExceptionType.FAILED_TO_FETCH, "Product Scheduler"));
    }

    public void save(ProductSchedulerForm productSchedulerForm) throws ApiException {
        Product product = productService.fetch(productSchedulerForm.getProductId());
        ProductScheduler productScheduler = productSchedulerForm.toEntity();

        productScheduler.setProduct(product);
        repository.save(productScheduler);
    }

    public void update(ProductSchedulerForm productSchedulerForm) throws ApiException {
        Product product = productService.fetch(productSchedulerForm.getProductId());
        ProductScheduler productScheduler = fetch(productSchedulerForm.getId());

        productScheduler.updateFields(productSchedulerForm);
        productScheduler.setProduct(product);
    }

    public void remove(String id) {
        repository.deleteById(id);
    }
}
