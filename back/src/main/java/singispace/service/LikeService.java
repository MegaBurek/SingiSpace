package singispace.service;


import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import singispace.domain.Like;
import singispace.domain.Post;
import singispace.repositories.PostRepository;

import java.util.Optional;

@Service
public class LikeService {

    Query query = new Query();

    @Autowired
    PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void likePost(String userId, String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Like like = new Like(userId);
            query.addCriteria(Criteria.where("id").is(postId));
            mongoTemplate.updateFirst(query, new Update().push("likes", like), Post.class);
        }
    }

    public void unlikePost(String userId, String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            query.addCriteria(Criteria.where("likes").elemMatch(Criteria.where("owner").is(userId)));
            Update update = new Update().pull("likes", new BasicDBObject("owner", userId));
            mongoTemplate.updateMulti(query, update, Post.class);
        }
    }
}
