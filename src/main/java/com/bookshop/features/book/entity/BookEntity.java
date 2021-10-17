package com.bookshop.features.book.entity;


import com.bookshop.features.author.entity.AuthorEntity;
import com.bookshop.features.opinion.entity.OpinionEntity;
import com.bookshop.features.order.entity.OrderEntity;
import com.bookshop.features.publisher.entity.PublisherEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Table(name = "BookEntity")
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String isbn;
    private Integer publishedYear;
    private String description;
    private Integer quantity;
    private BigDecimal price;

    //private Category categoryId;
    //private Language languageId; ENUM

    @OneToMany
    @JoinColumn(name = "book_id")
    private Set<OpinionEntity> bookOpinions = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_orders",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<OrderEntity> bookOrders = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> bookAuthors = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private Set<PublisherEntity> bookPublishers = new HashSet<>();

    public BookEntity() {
    }

    public BookEntity(String title, String isbn, Integer publishedYear, String description, Integer quantity, BigDecimal price) {
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Set<OpinionEntity> getBookOpinions() {
        return bookOpinions;
    }

    public void setBookOpinions(Set<OpinionEntity> bookOpinions) {
        this.bookOpinions = bookOpinions;
    }

    public Set<OrderEntity> getBookOrders() {
        return bookOrders;
    }

    public void setBookOrders(Set<OrderEntity> bookOrders) {
        this.bookOrders = bookOrders;
    }

    public Set<AuthorEntity> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<AuthorEntity> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public Set<PublisherEntity> getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(Set<PublisherEntity> bookPublishers) {
        this.bookPublishers = bookPublishers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishedYear=" + publishedYear +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
