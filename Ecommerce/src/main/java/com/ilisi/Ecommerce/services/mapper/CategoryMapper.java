package com.ilisi.Ecommerce.services.mapper;

import com.ilisi.Ecommerce.bo.Category;
import com.ilisi.Ecommerce.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements IMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryID(category.getCategoryID());
        dto.setLabel(category.getLabel());
        return dto;
    }

    @Override
    public Category toBO(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryID(dto.getCategoryID());
        category.setLabel(dto.getLabel());
        return category;
    }
}