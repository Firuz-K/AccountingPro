package com.applicationpro.service.impl;

import com.applicationpro.dto.CategoryDTO;
import com.applicationpro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<CategoryDTO> listAllCategories() {
        return null;
    }

    @Override
    public CategoryDTO findById(Long id) {
        return null;
    }
}
