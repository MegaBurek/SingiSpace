package singispace.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.service.PostService;
import singispace.service.ThemesService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemesService themesService;

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Theme> getById(@PathVariable String id) {
        Optional<Theme> theme = themesService.getById(id);
        if (theme.isPresent()) {
            return new ResponseEntity<Theme>(theme.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Theme>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/feed/{id}")
    public ResponseEntity<Iterable<Post>> getThemeFeed(@PathVariable String id) {
        return new ResponseEntity<>(postService.getThemeFeed(id), HttpStatus.OK);
    }

//    @GetMapping(value="/theme/{name}")
//    public ResponseEntity<Theme> getThemeByName(@PathVariable String name){
//        Optional<Theme> theme = themesService.getThemeByName(name);
//        if(theme.isPresent()) {
//            return new ResponseEntity<Theme>(theme.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<Theme>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Theme> removeTheme(@PathVariable String id) {
        try {
            themesService.removeTheme(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Theme> addTheme(@RequestBody Theme theme) {
        themesService.createTheme(theme);
        return new ResponseEntity<>(theme, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateTheme(@PathVariable String id, @RequestBody Theme theme) {
        themesService.updateTheme(id, theme);
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }

    @GetMapping(value = "/user-subscribed/{id}")
    public ResponseEntity<Iterable<Theme>> getUserSubscribedThemes(@PathVariable String id) {
        return new ResponseEntity<>(themesService.getThemeSubsByUserId(id), HttpStatus.OK);
    }
}
