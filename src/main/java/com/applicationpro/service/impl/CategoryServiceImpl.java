package com.applicationpro.service.impl;

import com.applicationpro.dto.CategoryDTO;
import com.applicationpro.repository.CategoryRepository;
import com.applicationpro.service.CategoryService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> listAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> mapperUtil.convert(category, new CategoryDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        return mapperUtil.convert(categoryRepository.findById(id).get(), new CategoryDTO());
    }
}
