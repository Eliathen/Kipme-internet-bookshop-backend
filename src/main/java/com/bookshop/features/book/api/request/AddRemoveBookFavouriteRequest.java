package com.bookshop.features.book.api.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddRemoveBookFavouriteRequest {

    private Long bookId;

    @JsonCreator
    public AddRemoveBookFavouriteRequest(@JsonProperty(value = "book_id", required = true) Long bookId) {
        this.bookId = bookId;
    }
}
