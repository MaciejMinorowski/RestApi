package pl.masiek.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.masiek.restapi.model.Post;
import pl.masiek.restapi.model.Comment;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p FROM Post p")
    List<Post> findAllPosts(Pageable page);
}