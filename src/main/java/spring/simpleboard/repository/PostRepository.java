package spring.simpleboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.simpleboard.entity.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAllByDeletedFalse();
    Optional<Post> findByIdAndDeletedFalse(Long id);
    Page<Post> findAllByDeletedFalse(Pageable pageable);
}
