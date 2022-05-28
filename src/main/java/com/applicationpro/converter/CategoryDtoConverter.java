package com.applicationpro.converter;

import com.applicationpro.dto.CategoryDTO;
import com.applicationpro.service.CategoryService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CategoryDtoConverter implements Converter<String, CategoryDTO> {

    CategoryService categoryService;

    public CategoryDtoConverter(@Lazy CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        return categoryService.findById(Long.parseLong(source));
    }
}
