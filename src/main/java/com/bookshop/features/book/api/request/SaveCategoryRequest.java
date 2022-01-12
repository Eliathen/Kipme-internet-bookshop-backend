package com.bookshop.features.book.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SaveCategoryRequest {


    @NotBlank(message = "Provide category's name")
    private String name;
}
