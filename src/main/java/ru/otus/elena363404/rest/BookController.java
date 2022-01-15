package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.service.BookService;

@Controller
@AllArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/edit_book")
  public String getBookById(@RequestParam("id") long id, Model model) {

    Book book = bookService.getBookById(id);
    BookDto bookDto = bookToBookDto(book);

    model.addAttribute("book", bookDto);
    return "edit_book";
  }

  @PostMapping("/edit_book")
  public String saveBook(BookDto bookDto, Model model) {
    Book book = bookDtoToBook(bookDto);
    Book saved = bookService.saveBook(book);
    model.addAttribute(bookToBookDto(saved));
    return "redirect:/";
  }

  @DeleteMapping("/delete_book")
  public String deleteBook(@RequestParam("id") long id) throws Exception {

    bookService.deleteBook(id);
    return "redirect:/";
  }

  @RequestMapping(value = "edit_book_author", method = RequestMethod.GET)
  public String getAuthorName(@RequestParam("author_id") long id, Model model) {
    model.addAttribute("authorName", "hello");
    return "edit_book";
  }

  public BookDto bookToBookDto(Book book) {
    Author author = book.getAuthor();
    Genre genre = book.getGenre();

    BookDto bookDto = new BookDto(book.getId(), book.getName(), author != null ? author.getId() : 0, author != null ? author.getName() : "", genre != null ? genre.getId() : 0, genre != null ? genre.getName() : "");

    return bookDto;
  }

  public Book bookDtoToBook(BookDto bookDto) {
    long authorId = bookDto.getAuthorId();
    long genreId = bookDto.getGenreId();
    Book book = new Book(bookDto.getId(), bookDto.getName(), authorId == 0 ? null : new Author(authorId, bookDto.getAuthorName()), genreId == 0 ? null : new Genre(genreId, bookDto.getGenreName()));

    return book;
  }

}
