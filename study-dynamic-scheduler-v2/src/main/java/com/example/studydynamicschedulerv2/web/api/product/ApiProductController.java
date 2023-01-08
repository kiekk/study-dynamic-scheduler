package com.example.studydynamicschedulerv2.web.api.product;

import com.example.studydynamicschedulerv2.dto.ProductForm;
import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.exception.ApiException;
import com.example.studydynamicschedulerv2.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ApiProductController {

    private final ProductService service;

    public ApiProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> search() {
        return service.search();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product fetch(@PathVariable String id) throws ApiException {
        return service.fetch(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProductForm product) {
        service.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductForm product) throws ApiException {
        service.update(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }


}
