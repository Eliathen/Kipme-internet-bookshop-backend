package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.exception.PublisherNotFound;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class PublisherServiceImplTest {

    private final PublisherRepository repository = Mockito.mock(PublisherRepository.class);

    private final PublisherService sut = new PublisherServiceImpl(repository);

    @Test
    void shouldReturnPublishers() {
        var publishers = getListOfPublishers();
        when(repository.getPublishers()).thenReturn(publishers);

        List<PublisherEntity> result = sut.getPublishers();

        assertThat(result).isNotEmpty().hasSize(publishers.size());

    }

    @Test
    void shouldSavePublisher() {
        var publisher = getListOfPublishers().get(0);
        when(repository.save(any(PublisherEntity.class))).thenReturn(publisher);

        PublisherEntity result = sut.savePublisher(publisher);

        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnPublisherWhenGivenValidId() {
        var publisher = getListOfPublishers().get(0);
        when(repository.getPublisher(publisher.getId())).thenReturn(Optional.of(publisher));

        PublisherEntity result = sut.getPublisher(publisher.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(publisher.getId());
    }

    @Test
    void shouldThrowExceptionWhenGivenInvalidId() {
        when(repository.getPublisher(1)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> sut.getPublisher(1),
                ExceptionMessages.PUBLISHER_NOT_FOUND, 1

        ).isInstanceOf(PublisherNotFound.class);
    }

    @Test
    void shouldReturnPublishersWhenGivenListOfValidIds() {
        var publishers = getListOfPublishers();
        var ids = publishers.stream().map(PublisherEntity::getId).toList();
        when(repository.getPublishersByIds(ids)).thenReturn(publishers);

        List<PublisherEntity> result = sut.getPublishers(ids);

        assertThat(result).isNotEmpty().hasSize(ids.size());
    }

    @Test
    void shouldNotReturnPublishersWhenGivenListOfInvalidIds() {
        when(repository.getPublishersByIds(anyList())).thenReturn(Collections.emptyList());

        List<PublisherEntity> result = sut.getPublishers(List.of(1, 2, 3));

        assertThat(result).isEmpty();
    }

    private List<PublisherEntity> getListOfPublishers() {
        return List.of(
                PublisherEntity.builder()
                        .id(1)
                        .publisherName("Publisher 1")
                        .publisherCity("City 1")
                        .publisherBooks(Set.of())
                        .build(),
                PublisherEntity.builder()
                        .id(2)
                        .publisherName("Publisher 2")
                        .publisherCity("City 1")
                        .publisherBooks(Set.of())
                        .build()
        );
    }
}