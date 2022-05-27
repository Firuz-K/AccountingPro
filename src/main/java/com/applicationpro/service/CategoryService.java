package com.applicationpro.service;

import com.applicationpro.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listAllCategories();
    CategoryDTO findById(Long id);
}
