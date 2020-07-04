package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Page;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.repositories.PagesRepository;
import singispace.service.PagesService;
import singispace.service.PostService;
import singispace.service.SubscriptionService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private PagesService pagesService;

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    private PostService postService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Page> getById(@PathVariable String id) {
        Optional<Page> page = pagesService.getById(id);
        if(page.isPresent()) {
            return new ResponseEntity<Page>(page.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Page>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value ="/page/{name}")
    public ResponseEntity<Page> getPageByName(@PathVariable String name) {
        Optional<Page> page = pagesRepository.findByName(name);
        if(page.isPresent()) {
            return new ResponseEntity<Page>(page.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Page>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/feed/{name}")
    public ResponseEntity<Iterable<Post>> getThemeFeed(@PathVariable String name) {
        return new ResponseEntity<>(postService.getPageFeed(name), HttpStatus.OK);
    }

    @GetMapping(value = "/owner/{id}")
    public ResponseEntity<Iterable<Page>> getUserOwnedPages(@PathVariable String id) {
        return new ResponseEntity<>(pagesService.getUserOwnedPages(id), HttpStatus.OK);
    }

    @PostMapping(value = "/subscribe/{name}")
    public ResponseEntity<String> subscribeToPage(@PathVariable String name, @RequestBody String id) {
        subscriptionService.subscribeToPage(id, name);
        return new ResponseEntity<>("Subscribed", HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Page> removePage(@PathVariable String id){
        try {
            pagesService.removePage(id);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value="/create")
    public ResponseEntity<Page> addPage(@RequestBody Page page){
        pagesService.createPage(page);
        return new ResponseEntity<Page>(page, HttpStatus.CREATED);
    }

    @PutMapping(value="{id}")
    public ResponseEntity<?> updatePage(@PathVariable String id, @RequestBody Page page) {
        pagesService.updatePage(id,page);
        return new ResponseEntity<Page>(page,HttpStatus.OK);
    }

    @GetMapping(value="/user-subscribed/{id}")
    public ResponseEntity<Iterable<Page>> getUserSubscribedPages(@PathVariable String id){
        return new ResponseEntity<Iterable<Page>>(pagesService.getPageSubsByUserId(id),HttpStatus.OK);
    }
}
