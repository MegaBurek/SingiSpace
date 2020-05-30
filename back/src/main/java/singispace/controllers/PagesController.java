package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Page;
import singispace.service.PagesService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private PagesService pagesService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Page> getById(@PathVariable String id) {
        Optional<Page> page = pagesService.getById(id);
        if(page.isPresent()) {
            return new ResponseEntity<Page>(page.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Page>(HttpStatus.NOT_FOUND);
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
