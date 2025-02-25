package spring.simpleboard.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.simpleboard.dto.CommentDto;
import spring.simpleboard.dto.PostDto;
import spring.simpleboard.entity.Comment;
import spring.simpleboard.entity.Post;
import spring.simpleboard.repository.CommentRepository;
import spring.simpleboard.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 등록
    public PostDto savePost(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getContent());
        Post savedPost = postRepository.save(post);
        return PostDto.from(savedPost, List.of());
    }

    // 게시글 목록 조회 (응답은 id와 타이틀만 / content, comment 제외)
    public Page<PostDto> getAllPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByDeletedFalse(pageable);

        Page<PostDto> postDtoPage = posts.map(post -> {
                    Long id = post.getId();
                    String title = post.getTitle();
                    return new PostDto(id, title, null, List.of());
                });
        return postDtoPage;
    }

    // 게시글 단건 조회
    public PostDto getPost(Long id) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 없습니다."));

        List<CommentDto> comments = commentRepository.findByPostIdAndDeletedFalse(post.getId())
                .stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());

        return PostDto.from(post, comments);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 없습니다."));

        List<Comment> comments = commentRepository.findByPostIdAndDeletedFalse(id);
        comments.forEach(Comment::softDelete);
        commentRepository.saveAll(comments);

        post.softDelete();
        postRepository.save(post);
    }

    // 게시글 수정
    public PostDto updatePost(Long id, PostDto updatedPostDto) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 없습니다."));

        post.update(updatedPostDto.getTitle(), updatedPostDto.getContent());
        Post updatedPost = postRepository.save(post);

        return PostDto.from(updatedPost, List.of());
    }

}
