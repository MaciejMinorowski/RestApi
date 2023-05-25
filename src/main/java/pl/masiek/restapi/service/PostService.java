package pl.masiek.restapi.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.masiek.restapi.model.Comment;
import pl.masiek.restapi.model.Post;
import pl.masiek.restapi.repository.CommentRepository;
import pl.masiek.restapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                )
        );
    }

    public Post getPostByID(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id")
        ));
        List<Long> ids = allPosts.stream()
                .map(post -> post.getId())
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }

    public Post addPost(Post post) {
         return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setComment(post.getComment());
        return postEdited;
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}


