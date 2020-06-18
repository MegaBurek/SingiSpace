package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import singispace.dto.FriendDTO;
import singispace.service.FriendsService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    FriendsService friendsService;

    @GetMapping(value = "/friends/{id}")
    public ResponseEntity<Iterable<FriendDTO>> getUserFriends(@PathVariable String id) {
        return new ResponseEntity<Iterable<FriendDTO>>(this.friendsService.getUsersFriends(id), HttpStatus.OK);
    }
}
