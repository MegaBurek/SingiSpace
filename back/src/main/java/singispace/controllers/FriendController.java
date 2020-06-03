package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Friend;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.service.FriendsService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    FriendsService friendsService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Friend> createThemePost(@PathVariable String id) {
        Optional<Friend> friend = friendsService.getFriend(id);
        return new ResponseEntity<Friend>(friend.get(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all/{id}")
    public ResponseEntity<Iterable<Friend>> getAllFriends(@PathVariable String id){
        return new ResponseEntity<Iterable<Friend>>(friendsService.getFriends(id), HttpStatus.OK);
    }


}
