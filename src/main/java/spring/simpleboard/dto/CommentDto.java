package spring.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.simpleboard.entity.Comment;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;

    public static CommentDto from(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent());
    }
}
