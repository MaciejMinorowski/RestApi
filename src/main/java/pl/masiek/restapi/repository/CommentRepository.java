package pl.masiek.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.masiek.restapi.model.Comment;
import pl.masiek.restapi.model.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdIn(List<Long> ids);
}
