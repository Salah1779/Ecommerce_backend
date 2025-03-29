package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.dto.CategoryDTO;
import com.ilisi.Ecommerce.exception.CategoryNotFoundException;
import com.ilisi.Ecommerce.mapper.CategoryMapper;
import com.ilisi.Ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;


    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toBO(categoryDTO)));
    }

    public void deleteCategory(Integer id) throws CategoryNotFoundException {
        if(!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) throws CategoryNotFoundException {
        if(!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with ID: " + id);
        }
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toBO(categoryDTO)));
    }

    public List<CategoryDTO> searchCategory(String keyword) {
        return categoryRepository.findByLabel(keyword).stream().
                map(categoryMapper::toDTO).
                collect(Collectors.toList());
    }

}
