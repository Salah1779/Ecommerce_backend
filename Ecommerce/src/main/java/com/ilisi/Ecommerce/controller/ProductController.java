package com.ilisi.Ecommerce.controller;

import com.ilisi.Ecommerce.dto.ProductDTO;
import com.ilisi.Ecommerce.services.ProductService;
import com.ilisi.Ecommerce.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        Response res= new Response(true , HttpStatus.OK , "success" , products);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Integer id) {
        ProductDTO product = productService.getProductById(id);
        Response res= new Response(true , HttpStatus.OK , "success" , product);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> saveProduct(@RequestBody ProductDTO newProduct) {
        ProductDTO savedProduct = productService.saveProduct(newProduct);
        Response res= new Response(true , HttpStatus.CREATED , "Product Created Successfully" , savedProduct);
        return new ResponseEntity<Response>(res, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchProductsByLabel(@RequestParam(required = false) String label)
    {

        if (label == null || label.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new Response(false, HttpStatus.BAD_REQUEST, "Label cannot be empty", null));
        }

        List<ProductDTO> products = productService.searchProductsByLabel(label.trim());

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, HttpStatus.NOT_FOUND, "No products found", null));
        }

        Response res = new Response(true, HttpStatus.OK, "Success", products);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/filter")
    public ResponseEntity<Response> filterProducts(
            @RequestParam(required = false, name = "min") Double minPrice,
            @RequestParam(required = false, name = "max") Double maxPrice,
            @RequestParam(required = false, name = "cat") Integer categoryID) {

        List<ProductDTO> products;

        boolean isCategoryInvalid = (categoryID == null || categoryID <= 0);
        boolean isPriceRangeInvalid = (minPrice == null || minPrice < 0 || maxPrice == null || maxPrice <= 0 || minPrice > maxPrice);

        if (isCategoryInvalid && !isPriceRangeInvalid) {

            products = productService.filterByPriceRange(minPrice, maxPrice);
        } else if (!isCategoryInvalid && isPriceRangeInvalid) {

            products = productService.filterProductsByCategory(categoryID);
        } else if (!isCategoryInvalid && !isPriceRangeInvalid) {

            products = productService.filterProductsByCategoryAndPriceRange(categoryID, minPrice, maxPrice);
        } else {
            // If both are invalid, return an empty list
            return ResponseEntity.badRequest()
                    .body(new Response(false, HttpStatus.BAD_REQUEST, "Invalid filters provided", null));
        }


        return ResponseEntity.ok(new Response(true, HttpStatus.OK, "Success", products));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        Response res= new Response(true , HttpStatus.OK , "Product Updated Successfully" , updatedProduct);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        Response res= new Response(true , HttpStatus.OK, "Product with id " + id + " Deleted Successfully" , null);
        return new ResponseEntity<Response>(res,HttpStatus.OK);
    }

}