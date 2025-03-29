package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.bo.Category;
import com.ilisi.Ecommerce.bo.Product;
import com.ilisi.Ecommerce.dto.CategoryDTO;
import com.ilisi.Ecommerce.dto.ProductDTO;
import com.ilisi.Ecommerce.exception.ProductNotFoundException;
import com.ilisi.Ecommerce.mapper.ProductMapper;
import com.ilisi.Ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;


    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    public ProductDTO saveProduct(ProductDTO dto) {
        CategoryDTO cat=dto.getCategory();
        CategoryDTO existingCategory = categoryService.getCategoryById(cat.getCategoryID());
        dto.setCategory(existingCategory);
        Product savedProduct = productRepository.save(productMapper.toBO(dto));
        return productMapper.toDTO(savedProduct);
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
    }


    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        product.setLabel(productDTO.getLabel());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setRetailPrice(productDTO.getRetailPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product .setCategory(new Category(productDTO.getCategory().getCategoryID(),
                              productDTO.getCategory().getLabel()
                            ));

        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    public List<ProductDTO> filterProductsByCategory(Integer idCategory) {
        List<Product> products = productRepository.filterProductsByCategory(idCategory);
        if(products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category with  " + idCategory);
        }
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }



    public List<ProductDTO> filterByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByRetailPriceBetween(minPrice, maxPrice);
        if(products.isEmpty()) {
            throw new ProductNotFoundException("No product found in price range " + minPrice + " - " + maxPrice);
        }
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());

    }

    public List<ProductDTO> filterProductsByCategoryAndPriceRange(Integer idCategory, double minPrice, double maxPrice) {
        List<Product> products = productRepository.filterPriceCategory(idCategory, minPrice, maxPrice);
        if(products.isEmpty()) {
            throw new ProductNotFoundException("No product found in category with id " + idCategory + " and price range " + minPrice + " - " + maxPrice);
        }
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> searchProductsByLabel(String label) {
        if(label==null)
            return productRepository.findAll().stream().map(productMapper::toDTO).
                    collect(Collectors.toList());
        List<Product> products= productRepository.findByLabelContainig(label);
        if(products.isEmpty()) {
            throw new ProductNotFoundException("No product found with label " + label);
        }
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }


}
