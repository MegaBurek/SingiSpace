package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import singispace.domain.User;
import singispace.dto.FriendDTO;
import singispace.dto.UserViewDTO;
import singispace.service.FriendsService;
import singispace.service.UserAccService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserAccService userAccService;

    @GetMapping(value = "/friends/{id}")
    public ResponseEntity<Iterable<FriendDTO>> getUserFriends(@PathVariable String id) {
        return new ResponseEntity<Iterable<FriendDTO>>(this.friendsService.getUsersFriends(id), HttpStatus.OK);
    }


    @GetMapping(value="/{username}")
    public ResponseEntity<UserViewDTO> findUserByUsername(@PathVariable String username) {
        Optional<UserViewDTO> accountData = userAccService.findUserViewByUsername(username);
        if(accountData.isPresent()) {
            return new ResponseEntity<UserViewDTO>(accountData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<UserViewDTO>(HttpStatus.NOT_FOUND);
    }
}
