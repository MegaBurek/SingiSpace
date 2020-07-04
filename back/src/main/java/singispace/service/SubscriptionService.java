package singispace.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import singispace.domain.Page;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.repositories.PagesRepository;
import singispace.repositories.ThemesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private ThemesRepository themesRepository;

    @Autowired
    private ThemesService themesService;

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private PagesService pagesService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void subscribeToTheme(String id, String name) {
        Query query = new Query();
        Optional<Theme> a = themesRepository.findByName(name);
        if (a.isPresent()) {
            query.addCriteria(Criteria.where("name").is(name));
            mongoTemplate.updateFirst(query, new Update().push("members", id), Theme.class);
        }
    }

    public void subscribeToPage(String id, String name) {
        Query query = new Query();
        Optional<Page> a = pagesRepository.findByName(name);
        if (a.isPresent()) {
            query.addCriteria(Criteria.where("name").is(name));
            mongoTemplate.updateFirst(query, new Update().push("members", id), Page.class);
        }
    }

}
