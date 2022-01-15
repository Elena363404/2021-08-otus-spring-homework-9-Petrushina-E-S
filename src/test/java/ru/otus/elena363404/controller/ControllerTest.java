package ru.otus.elena363404.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  BookService bookService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void checkRedirectBookUrl() throws Exception {

    Book book = new Book(6, "New book", new Author(6, "New author"), new Genre(6, "New genre"));
    Mockito.when(bookService.saveBook(Mockito.any())).thenReturn(book);
    mockMvc.perform(
        post("/edit_book?id=0")
          .content(objectMapper.writeValueAsString(book))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(redirectedUrl("/"))
      .andExpect(status().isFound());
  }


}
