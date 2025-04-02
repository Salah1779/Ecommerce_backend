package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.dto.CategoryDTO;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.services.mapper.CategoryMapper;
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

    public CategoryDTO getCategoryById(Integer id) throws ResourceNotFoundException {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toBO(categoryDTO)));
    }

    public void deleteCategory(Integer id) throws ResourceNotFoundException {
        if(!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        if(!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toBO(categoryDTO)));
    }

    public List<CategoryDTO> searchCategory(String keyword) {
        return categoryRepository.findByLabel(keyword).stream().
                map(categoryMapper::toDTO).
                collect(Collectors.toList());
    }

}
