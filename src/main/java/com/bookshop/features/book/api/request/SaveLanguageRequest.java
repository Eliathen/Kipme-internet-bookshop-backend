package com.bookshop.features.book.api.request;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SaveLanguageRequest {

    @NotBlank(message = "Provide language's name")
    private String name;

}
