package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.service.AuthorService;

@Controller
@AllArgsConstructor
public class AuthorController {

  private final AuthorService authorService;


  @GetMapping("/edit_author")
  public String getAuthorById(@RequestParam("id") long id, Model model) {
    Author author = authorService.getAuthorById(id);

    model.addAttribute("author", author);
    return "edit_author";
  }

  @PostMapping("/edit_author")
  public String saveAuthor(Author author, Model model) {
    Author saved = authorService.saveAuthor(author);
    model.addAttribute(saved);
    return "redirect:/";
  }

  @DeleteMapping("/delete_author")
  public String deleteAuthor(@RequestParam("id") long id)  {
    authorService.deleteAuthor(id);
    return "redirect:/";
  }
}
