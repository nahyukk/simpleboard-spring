package spring.simpleboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import spring.simpleboard.entity.Comment;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long id;

    @NotBlank(message = "{comment.content.notBlank}")
    @Length(max = 200, message = "{comment.content.length}")
    private String content;

    public static CommentDto from(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent());
    }
}
