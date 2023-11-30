package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.data.jpa.PublisherJpaRepository;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class PublisherRepositoryImplTest {

    private final PublisherJpaRepository jpa = Mockito.mock(PublisherJpaRepository.class);

    private final PublisherRepository sut = new PublisherRepositoryImpl(jpa);

    @Test
    void shouldReturnPublishers() {
        List<PublisherEntity> publishers = List.of(
                PublisherEntity.builder()
                        .id(1)
                        .build(),
                PublisherEntity.builder()
                        .id(2)
                        .build()
        );
        when(jpa.findAll()).thenReturn(publishers);

        List<PublisherEntity> result = sut.getPublishers();

        assertThat(result).hasSizeGreaterThan(1);
    }

    @Test
    void shouldSavePublisher() {
        PublisherEntity publisher = PublisherEntity.builder()
                .id(1)
                .build();
        when(jpa.saveAndFlush(Mockito.any(PublisherEntity.class))).thenReturn(publisher);

        PublisherEntity result = sut.save(publisher);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void shouldReturnPublisherWhenGivenValidId() {
        int id = 1;
        PublisherEntity publisher = PublisherEntity.builder()
                .id(id)
                .build();
        when(jpa.findById(id)).thenReturn(Optional.of(publisher));

        Optional<PublisherEntity> result = sut.getPublisher(id);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(id);
    }

    @Test
    void shouldNotReturnPublisherWhenGivenInvalidId() {
        int id = 999;
        when(jpa.findById(id)).thenReturn(Optional.empty());

        Optional<PublisherEntity> result = sut.getPublisher(id);

        assertThat(result).isNotPresent();
    }

    @Test
    void shouldReturnPublishersWhenGivenListOfValidId() {
        List<PublisherEntity> publishers = List.of(
                PublisherEntity.builder()
                        .id(1)
                        .build(),
                PublisherEntity.builder()
                        .id(2)
                        .build()
        );
        List<Integer> ids = List.of(1, 2);
        when(jpa.findAllById(ids)).thenReturn(publishers);

        List<PublisherEntity> result = sut.getPublishersByIds(ids);

        Assertions.assertThat(result).hasSize(ids.size());
        assertThat(result.stream().map(PublisherEntity::getId).toList()).isEqualTo(ids);
    }

    @Test
    void shouldNotReturnPublishersWhenGivenListOfInvalidId() {
        List<Integer> ids = List.of(1, 2);
        when(jpa.findAllById(anyList())).thenReturn(Collections.emptyList());

        List<PublisherEntity> result = sut.getPublishersByIds(ids);

        Assertions.assertThat(result).isEmpty();
    }

}