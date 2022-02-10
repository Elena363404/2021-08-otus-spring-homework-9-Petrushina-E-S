package ru.otus.elena363404.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Test Author")
class AuthorTest {

  @DisplayName("Create Author by constructor")
  @Test
  void shouldHaveCorrectConstructor() {
    Author author = new Author(5, "Pushkin");

    assertEquals(5, author.getId());
    assertEquals("Pushkin", author.getName());
  }
}