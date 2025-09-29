package com.gestiondestock.services.impl;

import com.gestiondestock.dto.CategoryDto;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.ArticleMapper;
import com.gestiondestock.mapper.CategoryMapper;
import com.gestiondestock.repository.CategoryRepository;
import com.gestiondestock.services.CategoryService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

   private final CategoryRepository categoryRepository;
   private final CategoryMapper categoryMapper;
   private final ObjectValidator<CategoryDto> objectValidator;
    private final ArticleMapper articleMapper;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
      if(categoryDto == null) {
          throw new InvalidEntityException("Category is null", ErrorsCode.ARTICLE_NOT_VALID);
      }
      objectValidator.validate(categoryDto);
       return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id == null) {
            throw new InvalidEntityException("Category is null", ErrorsCode.ARTICLE_NOT_VALID);
        }
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Not found Category with id: " + id , ErrorsCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if(code == null) {
            throw new InvalidEntityException("Category is null", ErrorsCode.CATEGORY_NOT_FOUND);
        }
        return categoryRepository.findCategoryByCode(code)
                .map(categoryMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Not found Category with code: " + code , ErrorsCode.CATEGORY_NOT_FOUND) );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category->categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            throw new InvalidEntityException("Category is null", ErrorsCode.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }
}
