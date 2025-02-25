package spring.simpleboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.simpleboard.dto.CommentDto;
import spring.simpleboard.entity.Comment;
import spring.simpleboard.entity.Post;
import spring.simpleboard.repository.CommentRepository;
import spring.simpleboard.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    public CommentDto saveComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 없습니다."));

        Comment newComment = new Comment(post, commentDto.getContent());
        Comment savedComment = commentRepository.save(newComment);
        return CommentDto.from(savedComment);
    }

    // 댓글 목록 조회 - 필요한지 의문? view에 쓰일 수 있어서 남겨 두었으나 Post 엔티티에 comment 부분 추가로 삭제
//    public List<CommentDto> getCommentsByPostId(Long postId) {
//        return commentRepository.findByPostIdAndDeletedFalse(postId)
//                .stream()
//                .map(CommentDto::from)
//                .collect(Collectors.toList());
//    }

    // 댓글 수정
    public CommentDto updateComment(Long id, CommentDto updatedComment) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글이 없습니다."));


        comment.update(updatedComment.getContent());
        Comment updated = commentRepository.save(comment);
        return CommentDto.from(updated);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글이 없습니다."));

        comment.softDelete();
        commentRepository.save(comment);
    }
}
