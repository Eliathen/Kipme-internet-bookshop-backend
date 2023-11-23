package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.AuthorEntity;
import com.bookshop.features.book.data.jpa.AuthorJpaRepository;
import com.bookshop.features.book.domain.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class AuthorRepositoryImplTest {

    private final AuthorJpaRepository jpa = Mockito.mock(AuthorJpaRepository.class);

    private AuthorRepository sut;

    @BeforeEach
    void setUp() {
        sut = new AuthorRepositoryImpl(jpa);
    }

    @Test
    void shouldCreateAuthor() {
        AuthorEntity author = getAuthorPeterBrett();

        when(jpa.saveAndFlush(author)).thenReturn(author);

        AuthorEntity saved = sut.saveAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void shouldReturnAuthorWhenGivenValidId() {
        AuthorEntity author = getAuthorPeterBrett();

        when(jpa.findById(author.getId())).thenReturn(Optional.of(author));

        Optional<AuthorEntity> found = sut.getAuthorById(author.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(author.getId());
    }

    @Test
    void shouldNotReturnAuthorWhenGivenInvalidId() {
        when(jpa.findById(999999)).thenReturn(Optional.empty());

        Optional<AuthorEntity> found = sut.getAuthorById(999999);

        assertThat(found).isNotPresent();
    }

    @Test
    void shouldReturnAuthorsWhenGivenNameOrSurname() {
        AuthorEntity brett = getAuthorPeterBrett();
        AuthorEntity robinson = getAuthorPeterRobinson();
        AuthorEntity anthony = getAuthorAnthonyBrett();

        when(jpa.findAuthorEntityByNameOrSurname(brett.getName(), brett.getName()))
                .thenReturn(List.of(brett, robinson));

        when(jpa.findAuthorEntityByNameOrSurname(brett.getSurname(), brett.getSurname()))
                .thenReturn(List.of(anthony));

        List<AuthorEntity> result = sut.getAuthorByNameOrSurname("Peter Brett");

        assertThat(result).isNotEmpty().hasSize(3);
    }

    @Test
    void shouldReturnAuthorsIfExistWhenGivenNameAndSurname() {
        AuthorEntity author = getAuthorPeterBrett();

        when(jpa.findFirstByNameIgnoreCaseAndSurnameIgnoreCase(author.getName(), author.getSurname()))
                .thenReturn(Optional.of(author));

        AuthorEntity result = sut.getAuthorByNameAndSurnameOrSave(author.getName(), author.getSurname());

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(author.getName());
    }

    @Test
    void shouldSaveAuthorIfNotExistWhenGivenNameAndSurname() {
        AuthorEntity author = getAuthorPeterBrett();
        AuthorEntity saved = AuthorEntity.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .build();

        when(jpa.findFirstByNameIgnoreCaseAndSurnameIgnoreCase(author.getName(), author.getSurname()))
                .thenReturn(Optional.empty());
        when(jpa.saveAndFlush(saved)).thenReturn(saved);

        AuthorEntity result = sut.getAuthorByNameAndSurnameOrSave(author.getName(), author.getSurname());

        Mockito.verify(jpa, times(1)).saveAndFlush(any(AuthorEntity.class));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(author.getName());
    }


    private AuthorEntity getAuthorPeterBrett() {
        return AuthorEntity.builder()
                .id(1)
                .name("Peter")
                .surname("Brett").build();
    }

    private AuthorEntity getAuthorPeterRobinson() {
        return AuthorEntity.builder()
                .id(1)
                .name("Peter")
                .surname("Robinson").build();
    }

    private AuthorEntity getAuthorAnthonyBrett() {
        return AuthorEntity.builder()
                .id(1)
                .name("Anthony")
                .surname("Brett").build();
    }
}
