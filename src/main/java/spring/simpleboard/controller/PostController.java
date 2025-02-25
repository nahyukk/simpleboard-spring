package spring.simpleboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.simpleboard.dto.CommentDto;
import spring.simpleboard.dto.PostDto;
import spring.simpleboard.entity.Comment;
import spring.simpleboard.entity.Post;
import spring.simpleboard.dto.PostPageDto;
import spring.simpleboard.service.CommentService;
import spring.simpleboard.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/posts")
    public String savePost(
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        PostDto postDto = new PostDto(null, title, content, List.of());
        PostDto post = postService.savePost(postDto);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/new")
    public String postForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "simple/addPost";
    }

    @GetMapping("/posts")
    public String getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size, Model model
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<PostDto> posts = postService.getAllPosts(pageable);

        if (posts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "페이지가 없습니다.");
        }

        model.addAttribute("posts",posts.getContent());
        model.addAttribute("currentPage", posts.getNumber());
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("totalElements", posts.getTotalElements());

        return "simple/index";

    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        PostDto post = postService.getPost(id);
//        List<CommentDto> comments = commentService.getCommentsByPostId(id);
        model.addAttribute("post", post);
//        model.addAttribute("comments", comments);
        return "simple/post";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        PostDto postDto = postService.getPost(id);
        model.addAttribute("post", postDto);
        return "simple/editPost";
    }

    @PostMapping("/posts/{id}")
    public String updatePost(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        PostDto postDto = new PostDto(id, title, content, List.of());
        postService.updatePost(id, postDto);
        return "redirect:/posts/{id}";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
