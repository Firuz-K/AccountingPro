package com.applicationpro.service.impl;

import com.applicationpro.dto.CategoryDTO;
import com.applicationpro.entity.Category;
import com.applicationpro.repository.CategoryRepository;
import com.applicationpro.service.CategoryService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public void save(CategoryDTO categoryDTO) {
        categoryDTO.setEnabled(true);
        categoryRepository.save(mapperUtil.convert(categoryDTO,new Category()));
    }

    @Override
    public void edit(CategoryDTO categoryDTO) {

        //find current category
       Category category = categoryRepository.getById(categoryDTO.getId());
        //Mapped updated user DTO to entity object
        Category convertedCategory = mapperUtil.convert(categoryDTO,new Category());
        //set it to converted object
        convertedCategory.setId(category.getId());
        convertedCategory.setCompany(category.getCompany());
        convertedCategory.setEnabled(category.getEnabled());
        //save updated user to DB
        categoryRepository.save(convertedCategory);

    }

    @Override
    public void deleteById(Long id){
        Category category = categoryRepository.getById(id);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }



}
