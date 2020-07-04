package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.repositories.PostRepository;
import singispace.service.LikeService;
import singispace.service.PostService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikeService likeService;

    @PostMapping(value = "/theme/{id}")
    public ResponseEntity<Theme> createThemePost(@PathVariable String id, @RequestBody Post post) {
        Optional<Theme> retTheme = postService.createThemePost(id, post);
        return new ResponseEntity<Theme>(retTheme.get(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/page/{id}")
    public ResponseEntity<Post> createPagePost(@PathVariable String id, @RequestBody Post post) {
        postService.createPagePost(id, post);
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @PostMapping(value = "/like/{id}")
    public ResponseEntity<String> likePost(@PathVariable String id, @RequestBody String userId) {
        likeService.likePost(userId, id);
        return new ResponseEntity<>("Liked", HttpStatus.OK);
    }

    @PostMapping(value = "/unlike/{id}")
    public ResponseEntity<String> unlikePost(@PathVariable String id, @RequestBody String userId) {
        likeService.unlikePost(userId, id);
        return new ResponseEntity<>("Unliked", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getById(@PathVariable String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping(value = "/page") //post to user feed
//    public ResponseEntity<Page> createPagePost(@PathVariable String id, @RequestBody Post post){
//        try {
//            postService.createPagePost(id, post);
//        }catch (Exception e){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

}
