package com.applicationpro.service;

import com.applicationpro.dto.CategoryDTO;
import com.applicationpro.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listAllCategories();
    CategoryDTO findById(Long id);
}
