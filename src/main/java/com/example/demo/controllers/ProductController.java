package com.example.demo.controllers;

import com.example.demo.entities.ProductModel;
import com.example.demo.repositories.ProductRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Lista todos os produtos
     *
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {

        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);

    }

    /**
     * Lista um produto pelo ID
     *
     * @param id
     * @return
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getOneProduct(@PathVariable(value = "id") UUID id) {

        Optional<ProductModel> productO = productRepository.findById(id);

        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productO.get(), HttpStatus.OK);

    }

    /**
     * Cadastra um produto
     *
     * @param product
     * @return
     */
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductModel product) {

        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);

    }

    /**
     * Remove um produto pelo ID
     *
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") UUID id) {

        Optional<ProductModel> productO = productRepository.findById(id);

        if (productO.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        productRepository.delete(productO.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Atualiza um produto pelo ID
     *
     * @param id
     * @param product
     * @return
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductModel product) {

        Optional<ProductModel> productO = productRepository.findById(id);

        if (productO.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        product.setIdProduct(productO.get().getIdProduct());
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);

    }

}
