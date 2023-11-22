package com.bookshop.features.book.api.controller;

import com.bookshop.core.exceptions.advice.BusinessExceptionAdvice;
import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.exception.LanguageNotFound;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@WebMvcTest(controllers = {LanguageController.class})
@AutoConfigureMockMvc
class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService languageService;

    private List<LanguageEntity> languages;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        languages = List.of(
                new LanguageEntity(1, "English", Collections.emptySet()),
                new LanguageEntity(2, "Polish", Collections.emptySet())
        );
        mockMvc = MockMvcBuilders
                .standaloneSetup(new LanguageController(languageService))
                .setControllerAdvice(new BusinessExceptionAdvice())
                .build();
    }

    @Test
    @WithMockUser
    void shouldFindAllLanguages() throws Exception {
        when(languageService.getLanguages()).thenReturn(languages);
        String expectedJson = getListOfLanguageResponsesAsJsonString();
        mockMvc.perform(get("/languages"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @WithMockUser
    void shouldFindLanguageWhenGivenValidId() throws Exception {
        var language = languages.get(0);
        String json = getEnglishLanguageResponseAsJsonString();
        when(languageService.getLanguage(language.getId())).thenReturn(language);

        mockMvc.perform(get("/languages/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    @WithMockUser
    void shouldNotFindLanguageWhenGivenInvalidId() throws Exception {

        when(languageService.getLanguage(999)).thenThrow(new LanguageNotFound(999));
        mockMvc.perform(get("/languages/999")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void shouldCreateNewLanguageWhenLanguageIsValid() throws Exception {
        SaveLanguageRequest saveLanguageRequest = new SaveLanguageRequest("Spanish");

        String request = mapper.writeValueAsString(saveLanguageRequest);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LanguageEntity language = new LanguageEntity(3, "Spanish", Set.of());
        when(languageService.saveLanguage(saveLanguageRequest)).thenReturn(language);

        String json = getSpanishLanguageResponseAsJson();

        mockMvc.perform(
                        post("/languages")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void shouldNotCreateNewLanguageWhenLanguageIsInvalid() throws Exception {
        String request = """
                    {
                        "name": ""
                    }
                """;
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(
                post("/languages")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @NotNull
    private String getListOfLanguageResponsesAsJsonString() {
        return """
                [
                  {
                    "id":1,
                    "name":"English"
                  },
                  {
                    "id":2,
                    "name":"Polish"
                  }
                ]""";
    }

    private String getEnglishLanguageResponseAsJsonString() {
        return """
                {
                    "id":1,
                    "name":"English"
                }
                """;
    }

    @NotNull
    private static String getSpanishLanguageResponseAsJson() {
        return """
                {
                    "id": 3,
                    "name": "Spanish"
                }
                """;
    }
}