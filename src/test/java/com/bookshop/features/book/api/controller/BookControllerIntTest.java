package com.bookshop.features.book.api.controller;

import com.bookshop.core.security.UserDetailsImpl;
import com.bookshop.features.base.MariaDbContainerBaseTest;
import com.bookshop.features.book.api.request.AuthorRequest;
import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.CategoryEntity;
import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.data.jpa.BookJpaRepository;
import com.bookshop.features.book.data.jpa.CategoryJpaRepository;
import com.bookshop.features.book.data.jpa.LanguageJpaRepository;
import com.bookshop.features.book.data.jpa.PublisherJpaRepository;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.data.jpa.UserJpaRepository;
import com.bookshop.features.utils.BookUtils;
import com.jayway.jsonpath.JsonPath;
import com.redis.testcontainers.RedisContainer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntTest extends MariaDbContainerBaseTest {
    public static RedisContainer redis = new RedisContainer(DockerImageName.parse("redis:7.2"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LanguageJpaRepository languageJpaRepository;

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Autowired
    PublisherJpaRepository publisherJpaRepository;

    @Autowired
    BookJpaRepository bookJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("app.cache.redis-url", () -> redis.getRedisURI());
    }

    static {
        redis.start();
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void shouldSaveBookWhenGivenValidSaveBookRequest() throws Exception {
        MockMultipartFile cover = getCover();
        MockMultipartFile data = new MockMultipartFile("request",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(getSaveBookRequest()).getBytes()
        );
        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/books")
                                .file(cover)
                                .file(data)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void shouldReturnBadRequestWhenGivenInvalidBookRequest() throws Exception {
        MockMultipartFile cover = getCover();
        MockMultipartFile data = new MockMultipartFile("request",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(getInvalidSaveBookRequest()).getBytes()
        );
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/books")
                                .file(cover)
                                .file(data)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        String message = JsonPath.read(response, "$.message[0]");
        String time = JsonPath.read(response, "$.timestamp");
        assertThat(time).isNotEmpty();
        assertThat(message).contains("Provide publishers' ids");
    }

    @Test
    void shouldReturnBookWhenGivenValidId() throws Exception {
        BookEntity saved = bookJpaRepository.findAll().get(0);
        UserEntity user = userJpaRepository.findAll().get(0);
        String response = mockMvc.perform(get("/books/" + saved.getId())
                        .with(user(UserDetailsImpl.of(user))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.read(response, "$.id");
        String title = JsonPath.read(response, "$.title");
        assertThat(Long.valueOf(id)).isEqualTo(saved.getId());
        assertThat(title).isEqualTo(saved.getTitle());

    }

    @Test
    void shouldReturn404WhenGivenInvalidId() throws Exception {
        UserEntity user = userJpaRepository.findAll().get(0);
        int invalidId = 999;
        String response = mockMvc.perform(get("/books/" + invalidId)
                        .with(user(UserDetailsImpl.of(user))))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        String message = JsonPath.read(response, "$.message[0]");
        String time = JsonPath.read(response, "$.timestamp");
        assertThat(time).isNotEmpty();
        assertThat(message).contains(String.format("Book with id %s not found", invalidId));
    }

    @Test
    void shouldReturnCoverWhenGivenValidBookId() throws Exception {
        BookEntity book = bookJpaRepository.findAll().get(0);
        UserEntity user = userJpaRepository.findAll().get(0);
        byte[] cover = mockMvc.perform(get("/books/" + book.getId() + "/cover")
                        .with(user(UserDetailsImpl.of(user))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        assertThat(cover).isNotEmpty();

    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void shouldReturnBadRequestWhenGivenEmptyCover() throws Exception {
        MockMultipartFile data = new MockMultipartFile("request",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(getSaveBookRequest()).getBytes()
        );
        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/books")
                        .file(data)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundWhenGivenInvalidBookId() throws Exception {
        var invalidId = 999;
        UserEntity user = userJpaRepository.findAll().get(0);
        mockMvc.perform(get("/books/" + invalidId + "/cover")
                        .with(user(UserDetailsImpl.of(user))))
                .andExpect(status().isNotFound());


    }

    @Test
    @WithMockUser(authorities = {"USER"})
    void shouldReturnNotAuthorizedWhenGivenUserAuthorities() throws Exception {
        MockMultipartFile cover = getCover();
        MockMultipartFile data = new MockMultipartFile("request",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(getSaveBookRequest()).getBytes()
        );
        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/books")
                        .file(cover)
                        .file(data)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }

    private SaveBookRequest getSaveBookRequest() {
        CategoryEntity category = categoryJpaRepository.save(BookUtils.getCategory());
        LanguageEntity language = languageJpaRepository.save(BookUtils.getLanguage());
        PublisherEntity publisher = publisherJpaRepository.save(BookUtils.getPublisher());
        return new SaveBookRequest("Kotlin in action",
                "9781617293290",
                2017,
                """
                        Kotlin in Action guides experienced Java developers from the language basics of Kotlin all the way through building applications to run on the JVM and Android devices.
                        """,
                60,
                BigDecimal.valueOf(67.00),
                List.of(
                        new AuthorRequest(BookUtils.getAuthorDmitryJemerow().getName(),
                                BookUtils.getAuthorDmitryJemerow().getSurname()
                        )
                ),
                Set.of(publisher.getId()),
                language.getId(),
                category.getId(),
                List.of(category.getSubcategories().get(0).getId())
        );
    }

    private SaveBookRequest getInvalidSaveBookRequest() {
        CategoryEntity category = categoryJpaRepository.save(BookUtils.getCategory());
        LanguageEntity language = languageJpaRepository.save(BookUtils.getLanguage());
        return new SaveBookRequest("Kotlin in action",
                "9781617293290",
                2017,
                """
                        Kotlin in Action guides experienced Java developers from the language basics of Kotlin all the way through building applications to run on the JVM and Android devices.
                        """,
                60,
                BigDecimal.valueOf(67.00),
                List.of(
                        new AuthorRequest(BookUtils.getAuthorDmitryJemerow().getName(),
                                BookUtils.getAuthorDmitryJemerow().getSurname()
                        )
                ),
                Set.of(),
                language.getId(),
                category.getId(),
                List.of(category.getSubcategories().get(0).getId())
        );
    }

    @NotNull
    private MockMultipartFile getCover() throws IOException {
        Resource file = new ClassPathResource("test_cover.png");
        return new MockMultipartFile("cover",
                file.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                file.getInputStream()
        );
    }

}
