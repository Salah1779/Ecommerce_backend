package com.ilisi.Ecommerce.services.mapper;

import com.ilisi.Ecommerce.bo.Product;
import com.ilisi.Ecommerce.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IMapper<Product, ProductDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setProductID(product.getProductID());
        dto.setLabel(product.getLabel());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setRetailPrice(product.getRetailPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(categoryMapper.toDTO(product.getCategory()));
        return dto;
    }

    @Override
    public Product toBO(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setProductID(dto.getProductID());
        product.setLabel(dto.getLabel());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setRetailPrice(dto.getRetailPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(categoryMapper.toBO(dto.getCategory()));
        return product;
    }
}