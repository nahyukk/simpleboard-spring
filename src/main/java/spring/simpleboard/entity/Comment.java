package spring.simpleboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@SQLRestriction("deleted = false")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

    private Boolean deleted = false;

    public Comment(Post post, String content) {
        this.post = post;
        this.content = content;
    }

    public void softDelete() {
        this.deleted = true;
    }

    public void update(String content) {
        this.content = content;
    }
}
