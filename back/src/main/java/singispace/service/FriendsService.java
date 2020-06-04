package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Friend;
import singispace.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    @Autowired
    UserAccService userAccService;

    public Optional<Friend> getFriend(String id){
        Friend friend = new Friend();
        Optional<User> user = userAccService.getById(id);
        friend.setName(user.get().getName());
        friend.setSurname(user.get().getSurname());
        friend.setEmail(user.get().getEmail());
        friend.setImgUrl(user.get().getImgUrl());
        friend.setTheme_subs(user.get().getTheme_subs());
        friend.setPage_subs(user.get().getTheme_subs());
        friend.setFriends(user.get().getFriends());

        return  Optional.of(friend);
    }

//    public void removeFriend(String id){
//
//    }

//    public void addFriend(String id){
//
//    }

    public Iterable<Friend> getFriends(String id){
        List<Friend> friends = new ArrayList<>();
        List<String> friends_ids;
        Friend friend;

        Optional<User> user = userAccService.getById(id);
        friends_ids = user.get().getFriends();
        for (String friend_id: friends_ids){
            friend = getFriend(friend_id).get();
            friends.add(friend);
        }
        return friends;

    }
}
