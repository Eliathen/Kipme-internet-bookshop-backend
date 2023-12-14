package com.bookshop.features.book.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AddRemoveBookFavouriteRequest(

        @NotNull(message = "Provide book's id")
        @JsonProperty(value = "book_id", required = true)
        Long bookId

) {

}
