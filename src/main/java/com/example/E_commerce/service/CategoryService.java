package com.example.E_commerce.service;

import com.example.E_commerce.exception.CategoryNotFoundException;
import com.example.E_commerce.model.Category;
import com.example.E_commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found for this id: " + id));
    }

    public List<Category> getAllCategoryList(){
        return  categoryRepository.findAll();
    }
}
