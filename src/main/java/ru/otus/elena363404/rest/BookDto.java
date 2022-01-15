package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
  private long id;
  private String name;
  private long authorId;
  private String authorName;
  private long genreId;
  private String genreName;


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public long getGenreId() {
    return genreId;
  }

  public void setGenreId(long genreId) {
    this.genreId = genreId;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

  public static Book toDomainObject(BookDto dto) {
    return new Book(dto.getId(), dto.getName(), new Author(dto.getAuthorId(), dto.getAuthorName()), new Genre(dto.getGenreId(), dto.getGenreName()));
  }

  public static BookDto toDto(Book book) {
    Author author = book.getAuthor();
    Genre genre = book.getGenre();
    return new BookDto(book.getId(), book.getName(), author != null ? author.getId() : 0, author != null ? author.getName() : "", genre != null ? genre.getId() : 0, genre != null ? genre.getName() : "");
  }
}
