package com.example.studydynamicschedulerv2.service.product;

import com.example.studydynamicschedulerv2.dto.ProductForm;
import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv2.exception.ApiException;
import com.example.studydynamicschedulerv2.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> search() {
        return repository.findAll();
    }

    public Product fetch(String id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException(ApiExceptionType.FAILED_TO_FETCH, "Product"));
    }

    public void save(ProductForm productForm) {
        Product product = productForm.toEntity();
        repository.save(product);
    }

    public void update(ProductForm productForm) throws ApiException {
        Product product = fetch(productForm.getId());
        product.updateFields(productForm);
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

}
