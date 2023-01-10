package com.example.studydynamicschedulerv2.service.product;

import com.example.studydynamicschedulerv2.dto.ProductSchedulerForm;
import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv2.exception.ApiException;
import com.example.studydynamicschedulerv2.repository.product.ProductSchedulerRepository;
import com.example.studydynamicschedulerv2.service.job.JobService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductSchedulerService {

    @PersistenceContext
    private EntityManager em;
    private final ProductSchedulerRepository repository;
    private final ProductService productService;
    private final JobService jobService;

    public ProductSchedulerService(ProductSchedulerRepository repository, ProductService productService, JobService jobService) {
        this.repository = repository;
        this.productService = productService;
        this.jobService = jobService;
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

        jobService.register(productScheduler);
    }

    public void update(ProductSchedulerForm productSchedulerForm) throws ApiException {
        Product product = productService.fetch(productSchedulerForm.getProductId());
        ProductScheduler productScheduler = fetch(productSchedulerForm.getId());

        productScheduler.setProduct(null);
        em.flush();

        productScheduler.updateFields(productSchedulerForm);
        productScheduler.setProduct(product);

        jobService.update(productScheduler);
    }

    public void remove(String id) throws ApiException {
        repository.deleteById(id);

        jobService.delete(id);
    }

    public void resume(String id) throws ApiException {
        ProductScheduler productScheduler = fetch(id);
        productScheduler.resume();
        jobService.resume(productScheduler);
    }

    public void pause(String id) throws ApiException {
        ProductScheduler productScheduler = fetch(id);
        productScheduler.pause();
        jobService.pause(productScheduler.getId());
    }
}
