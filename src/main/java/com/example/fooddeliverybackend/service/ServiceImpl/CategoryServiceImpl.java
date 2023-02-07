package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.entity.Category;
import com.example.fooddeliverybackend.repository.CategoryRepository;
import com.example.fooddeliverybackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ApiResponse add(Category category) {
        boolean b = categoryRepository.existsByName(category.getName());
        if (b) return new ApiResponse("Already added category",false);
        Category category1=new Category();
        category1.setName(category.getName());
        categoryRepository.save(category1);
        return new ApiResponse("Category successfully added", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        boolean b = categoryRepository.existsById(id);
        if (!b) return new ApiResponse("Not found", false);
        categoryRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
