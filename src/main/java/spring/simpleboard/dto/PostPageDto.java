package spring.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.simpleboard.entity.Post;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostPageDto {
    private List<PostDto> posts;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}
