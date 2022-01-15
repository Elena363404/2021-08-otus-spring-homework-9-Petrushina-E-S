package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.service.GenreService;


@Controller
@AllArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping("/edit_genre")
  public String getGenreById(@RequestParam("id") long id, Model model) {
    Genre genre = genreService.getGenreById(id);

    model.addAttribute("genre", genre);
    return "edit_genre";
  }

  @PostMapping("/edit_genre")
  public String saveGenre(Genre genre, Model model) {
    Genre saved = genreService.saveGenre(genre);
    model.addAttribute(saved);
    return "redirect:/";
  }

  @DeleteMapping("/delete_genre")
  public String deleteGenre(@RequestParam("id") long id) {
    genreService.deleteGenre(id);
    return "redirect:/";
  }
}
