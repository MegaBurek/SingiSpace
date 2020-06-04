package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.service.PostService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

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
