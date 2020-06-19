package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import singispace.domain.Permission;
import singispace.domain.Theme;
import singispace.repositories.PagesRepository;
import singispace.repositories.ThemesRepository;
import singispace.repositories.users.UserAccRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private ThemesRepository themesRepository;

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private UserAccRepository userAccRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Number countAllThemeRecords() {
        return themesRepository.count();
    }

    public Number countAllPageRecords() {
        return pagesRepository.count();
    }

    public Iterable<Number> getSocialGroupCount() {
        ArrayList<Number> socialGroupCount = new ArrayList<>();
        socialGroupCount.add(countAllPageRecords());
        socialGroupCount.add(countAllThemeRecords());
        return socialGroupCount;
    }

    public Iterable<Number> countAllUserRecords() {
        ArrayList<Number> roleCount = new ArrayList<>();
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("authority").is("ROLE_ADMIN"));
        List<Permission> adminPermissions = mongoTemplate.find(query1, Permission.class);
        roleCount.add(adminPermissions.size());

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("authority").is("ROLE_TUTOR"));
        List<Permission> tutorPermissions = mongoTemplate.find(query2, Permission.class);
        roleCount.add(tutorPermissions.size());

        Query query3 = new Query();
        query3.addCriteria(Criteria.where("authority").is("ROLE_LEARNER"));
        List<Permission> learnerPermissions = mongoTemplate.find(query3, Permission.class);
        roleCount.add(learnerPermissions.size());

        return roleCount;
    }
}
