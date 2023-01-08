package com.example.studydynamicschedulerv2.service.product;

import com.example.studydynamicschedulerv2.dto.ProductForm;
import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void cleanUp() {
        productService.removeAll();
    }

    @Test
    @DisplayName("Product Insert Success")
    public void insertSuccessTest() {
        ProductForm productForm = new ProductForm();
        productForm.setTitle("Test Title");

        productService.save(productForm);

        List<Product> productList = productService.search();
        assertEquals(productList.size(), 1);
    }

    @Test
    @DisplayName("Product Insert Success")
    public void fetchTest() {
        ProductForm productForm = new ProductForm();
        productForm.setTitle("Test Title");

        productService.save(productForm);

        Product product = productService.search().get(0);

        assertEquals(productForm.getTitle(), product.getTitle());
    }

}