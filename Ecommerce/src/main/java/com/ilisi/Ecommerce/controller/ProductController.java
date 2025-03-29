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
@RequestMapping("/products")
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
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        Response res= new Response(true , HttpStatus.OK , "success" , product);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> saveProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = productService.saveProduct(productDTO);
        Response res= new Response(true , HttpStatus.CREATED , "Product Created Successfully" , savedProduct);
        return new ResponseEntity<Response>(res, HttpStatus.CREATED);
    }
    @PostMapping("/search/{label}")
    public ResponseEntity<Response> searchProductsByLabel(@PathVariable String label) {
        List<ProductDTO> products = productService.searchProductsByLabel(label);
        Response res= new Response(true , HttpStatus.OK , "success" , products);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Response> filterProducts(
            @RequestParam(required = false, defaultValue = "-1" , name ="min") Double minPrice,
            @RequestParam(required = false, defaultValue = "-1" ,name ="max") Double maxPrice,
            @RequestParam(required = false, defaultValue = "0", name = "cat" ) Integer categoryID)
    {
        List<ProductDTO> products;
        if((categoryID==null || categoryID<0 ) && (minPrice>=0 && maxPrice>0))
          products = productService.filterByPriceRange(minPrice, maxPrice);
        else if((minPrice<0 || maxPrice<=0) && (categoryID!=null && categoryID>0))
          products = productService.filterProductsByCategory(categoryID);
        else{

        }
          products = productService.filterProductsByCategoryAndPriceRange(categoryID, minPrice, maxPrice);
        Response res= new Response(true , HttpStatus.OK , "success" , products);

        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }


   @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        Response res= new Response(true , HttpStatus.OK , "Product Updated Successfully" , updatedProduct);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        Response res= new Response(true , HttpStatus.NO_CONTENT , "Product Deleted Successfully" , null);
        return new ResponseEntity<Response>(res,HttpStatus.NO_CONTENT);
    }

}