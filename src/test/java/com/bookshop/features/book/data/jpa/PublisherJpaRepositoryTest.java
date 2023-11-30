package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.base.MariaDbContainerBaseTest;
import com.bookshop.features.book.data.entity.PublisherEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PublisherJpaRepositoryTest extends MariaDbContainerBaseTest {

    @Autowired
    PublisherJpaRepository sut;

    @BeforeEach
    void setUp() {
        List<PublisherEntity> publishers = List.of(
                PublisherEntity.builder()
                        .publisherName("Publisher 1")
                        .publisherCity("City 1")
                        .publisherBooks(Set.of())
                        .build(),
                PublisherEntity.builder()
                        .publisherName("Publisher 2")
                        .publisherCity("City 1")
                        .publisherBooks(Set.of())
                        .build()
        );
        sut.saveAll(publishers);
    }

    @Test
    void shouldReturnPublishers() {
        List<PublisherEntity> result = sut.findAll();

        assertThat(result.size()).isNotZero();
    }

    @Test
    void shouldSavePublisher() {
        PublisherEntity publisher = PublisherEntity.builder()
                .publisherName("Publisher 3")
                .publisherCity("City 3")
                .publisherBooks(Set.of())
                .build();

        PublisherEntity saved = sut.save(publisher);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void shouldReturnPublisherWhenGivenValidId() {
        PublisherEntity publisher = PublisherEntity.builder()
                .publisherName("Publisher 3")
                .publisherCity("City 3")
                .publisherBooks(Set.of())
                .build();
        PublisherEntity saved = sut.save(publisher);

        Optional<PublisherEntity> result = sut.findById(saved.getId());

        assertThat(result).isPresent().contains(saved);
    }

    @Test
    void shouldNotReturnPublisherWhenGivenInvalidId() {
        Optional<PublisherEntity> result = sut.findById(999);

        assertThat(result).isNotPresent();
    }

    @Test
    void shouldReturnPublisherWhenGivenListOfValidIds() {
        List<Integer> ids = sut.findAll()
                .stream()
                .map(PublisherEntity::getId)
                .toList();

        List<PublisherEntity> result = sut.findAllById(ids);
        List<PublisherEntity> result2 = sut.findAllById(List.of(ids.get(0)));

        assertThat(result).hasSize(ids.size());
        assertThat(result2).hasSize(1);
    }


    @Test
    void shouldNotReturnPublisherWhenGivenListOfInvalidIds() {
        List<Integer> ids = List.of(9999, 9998);
        List<PublisherEntity> result = sut.findAllById(ids);

        assertThat(result).isEmpty();
    }

}