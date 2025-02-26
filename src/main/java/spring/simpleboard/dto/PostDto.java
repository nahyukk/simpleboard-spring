package spring.simpleboard.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import spring.simpleboard.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;

    @NotBlank(message = "{post.title.notBlank}")
    @Length(max = 50, message = "{post.title.length}")
    private String title;

    @NotBlank(message = "{post.content.notBlank}")
    private String content;

    @Valid
    private List<CommentDto> comments;

    public static PostDto from (Post post, List<CommentDto> comments) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), comments);
    }
}

