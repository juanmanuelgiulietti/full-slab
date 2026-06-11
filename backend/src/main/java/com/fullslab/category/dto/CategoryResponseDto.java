package com.fullslab.category.dto;

import com.fullslab.category.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class CategoryResponseDto {
    private Long id;
    private String name;

    public CategoryResponseDto(Category category) {
        this.id = category.getCategory_id();
        this.name = category.getName();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}