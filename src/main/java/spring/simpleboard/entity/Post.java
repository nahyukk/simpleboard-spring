package spring.simpleboard.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SQLRestriction("deleted = false")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    private Boolean deleted = false;

    public Post(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void softDelete() {
        this.deleted = true;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
