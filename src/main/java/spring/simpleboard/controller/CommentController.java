package spring.simpleboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.simpleboard.dto.CommentDto;
import spring.simpleboard.entity.Comment;
import spring.simpleboard.service.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public String saveComment(
            @PathVariable Long postId,
            @ModelAttribute CommentDto commentDto) {
        commentService.saveComment(postId, commentDto);
        return "redirect:/posts/" + postId;
    }


    @PostMapping("/comments/{id}/edit")
    public String updateComment(
            @PathVariable Long postId,
            @PathVariable Long id,
            @ModelAttribute CommentDto commentDto) {
        commentService.updateComment(id, commentDto);

        return "redirect:/posts/{postId}";
    }

    @PostMapping("/comments/{id}/delete")
    public String deleteComment(
            @PathVariable Long postId,
            @PathVariable Long id) {
        commentService.deleteComment(id);

        return "redirect:/posts/{postId}";
    }
}
