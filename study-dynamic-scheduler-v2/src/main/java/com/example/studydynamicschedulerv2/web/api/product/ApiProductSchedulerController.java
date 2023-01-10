package com.example.studydynamicschedulerv2.web.api.product;

import com.example.studydynamicschedulerv2.dto.ProductSchedulerForm;
import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv2.exception.ApiException;
import com.example.studydynamicschedulerv2.service.product.ProductSchedulerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-scheduler")
public class ApiProductSchedulerController {

    private final ProductSchedulerService service;

    public ApiProductSchedulerController(ProductSchedulerService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductScheduler> search() {
        return service.search();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductScheduler fetch(@PathVariable String id) throws ApiException {
        return service.fetch(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody ProductSchedulerForm product, BindingResult bindingResult) throws ApiException {

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            throw new ApiException(ApiExceptionType.INVALID_PARAMETER, fieldError.getField(), fieldError.getDefaultMessage());
        }
        service.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id,
                       @RequestBody ProductSchedulerForm product) throws ApiException {
        product.setId(id);
        service.update(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }

}
