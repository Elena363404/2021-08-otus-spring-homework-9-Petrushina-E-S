package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.service.CommentService;

@Controller
@AllArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping("/edit_comment")
  public String getCommentById(@RequestParam("id") long id, Model model) {
    CommentDto commentDto = commentToCommentDto(commentService.getCommentById(id));

    model.addAttribute("comment", commentDto);
    return "edit_comment";
  }

  @PostMapping("/edit_comment")
  public String saveComment(CommentDto commentDto, Model model) {
    Comment comment = commentDtoToComment(commentDto);
    Comment saved = commentService.saveComment(comment);
    model.addAttribute(commentToCommentDto(saved));

    return "redirect:/";
  }

  @DeleteMapping("/delete_comment")
  public String deleteComment(@RequestParam("id") long id) throws Exception {
    commentService.deleteComment(id);
    return "redirect:/";
  }

  public CommentDto commentToCommentDto(Comment comment) {
    Book book = comment.getBook();

    CommentDto commentDto = new CommentDto(comment.getId(), comment.getComment(), book != null ? book.getId() : 0, book != null ? book.getName() : "");
    return commentDto;
  }

  public Comment commentDtoToComment(CommentDto commentDto) {
    Comment comment = new Comment(commentDto.getId(), commentDto.getComment(), new Book(commentDto.getBookId(), commentDto.getBookName(), null, null));

    return comment;
  }
}
