package pl.masiek.restapi.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.masiek.restapi.DTO.PostDto;
import pl.masiek.restapi.DTO.PostDtoMapper;
import pl.masiek.restapi.model.Post;
import pl.masiek.restapi.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

      @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
       Integer pageNumber = page != null && page >= 0 ? page : 0;
       Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sortDirection));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        Integer pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getPostByID(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post){
        return postService.editPost(post);

    }

    @DeleteMapping("posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }



}
