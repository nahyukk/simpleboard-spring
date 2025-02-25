package spring.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.simpleboard.entity.Post;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private List<CommentDto> comments;

    public static PostDto from (Post post, List<CommentDto> comments) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), comments);
    }
}

