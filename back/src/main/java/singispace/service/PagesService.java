package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import singispace.domain.Page;
import singispace.domain.User;
import singispace.repositories.PagesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagesService {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public PagesService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private UserAccService userAccService;

    @Autowired
    private PagesRepository pagesRepository;

    public Iterable<Page> getAll() {
        return pagesRepository.findAll();
    }

    public Optional<Page> getById(String id) {
        return pagesRepository.findById(id);
    }

    public void createPage(Page page) {
        pagesRepository.save(page);
    }

    public void removePage(String id) {
        Optional<Page> page = pagesRepository.findById(id);
        pagesRepository.delete(page.get());
    }

    public void updatePage(String id, Page page) {
        Optional<Page> a = pagesRepository.findById(id);

        if (a.isPresent()) {
            page.setId(a.get().getId());

            pagesRepository.save(page);
        }
    }

    // get all pages that logged in user is subscribed to
    public Iterable<Page> getPageSubsByUserId(String id) {
        List<Page> page_subs = new ArrayList<>();
        List<String> page_subs_ids;
        Page page;

        Optional<User> user = userAccService.getById(id);

        page_subs_ids = user.get().getPage_subs();
        for (String page_sub_id : page_subs_ids) {
            page = getById(page_sub_id).get();
            page_subs.add(page);
        }
        return page_subs;
    }

}
