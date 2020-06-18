package singispace.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.User;
import singispace.dto.FriendDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    @Autowired
    UserAccService userAccService;

    @Autowired
    ModelMapper modelMapper;

//    public void removeFriend(String id){
//
//    }

//    public void addFriend(String id){
//
//    }

    public Iterable<FriendDTO> getUsersFriends(String id) {
        List<FriendDTO> friends = new ArrayList<>();
        List<String> friend_ids;

        Optional<User> user = userAccService.getById(id);
        friend_ids = user.get().getFriends();
        for (String friend_id : friend_ids) {
            friends.add(convertToDto(this.userAccService.getById(friend_id).get()));
        }

        return friends;
    }

    private FriendDTO convertToDto(User user) {
        FriendDTO friendDTO = modelMapper.map(user, FriendDTO.class);
        return friendDTO;
    }
}
