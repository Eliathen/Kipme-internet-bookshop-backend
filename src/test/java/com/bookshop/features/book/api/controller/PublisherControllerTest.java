package com.bookshop.features.book.api.controller;

import com.bookshop.core.exceptions.advice.BusinessExceptionAdvice;
import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.exception.PublisherNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@WebMvcTest(controllers = PublisherController.class)
@AutoConfigureMockMvc
class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PublisherService publisherService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new PublisherController(publisherService))
                .setControllerAdvice(new BusinessExceptionAdvice())
                .build();
    }

    @Test
    void shouldFindPublishers() throws Exception {
        List<PublisherEntity> publishers = getListOfPublishers();
        String expectedJson = getListOfPublishersAsJson();
        when(publisherService.getPublishers()).thenReturn(publishers);

        mockMvc.perform(MockMvcRequestBuilders.get("/publishers"))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    void shouldFindPublisherWhenGivenValidId() throws Exception {
        PublisherEntity publisher = getPublisher();
        String expectedJson = getPublisherAsJson();
        when(publisherService.getPublisher(publisher.getId())).thenReturn(publisher);

        mockMvc.perform(MockMvcRequestBuilders.get("/publishers/" + publisher.getId()))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotFindPublisherWhenGivenInvalidId() throws Exception {
        PublisherEntity publisher = getPublisher();
        when(publisherService.getPublisher(publisher.getId())).thenThrow(PublisherNotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/publishers/" + publisher.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreatePublisherWhenGivenValidSavePublisherRequest() throws Exception {
        PublisherEntity entity = PublisherEntity.builder().id(1).publisherName("Name 1").publisherCity("City 1").build();
        when(publisherService.savePublisher(any(PublisherEntity.class))).thenReturn(entity);
        String content = """
                {
                    "name": "Name 1",
                    "city": "City 1"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreatePublisherWhenGivenSavePublisherRequestWithEmptyName() throws Exception {
        String content = """
                {
                    "name": "",
                    "city": "City 1"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotCreatePublisherWhenGivenSavePublisherRequestWithEmptyCity() throws Exception {
        String content = """
                {
                    "name": "Name 1",
                    "city": ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
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

    private PublisherEntity getPublisher() {
        return PublisherEntity.builder()
                .id(1)
                .publisherName("Publisher 1")
                .publisherCity("City 1")
                .publisherBooks(Set.of())
                .build();
    }

    private String getListOfPublishersAsJson() {
        return """
                [
                  {
                    "id":1,
                    "name":"Publisher 1",
                    "city":"City 1"
                  },
                  {
                    "id":2,
                    "name":"Publisher 2",
                    "city":"City 1"
                  }
                ]
                """;
    }

    private String getPublisherAsJson() {
        return """
                {
                    "id":1,
                    "name":"Publisher 1",
                    "city":"City 1"
                }
                """;
    }
}