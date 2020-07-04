package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import singispace.domain.Theme;
import singispace.domain.User;
import singispace.repositories.ThemesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemesService {

    @Autowired
    private UserAccService userAccService;

    @Autowired
    private ThemesRepository themesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Iterable<Theme> getAll() {
        return themesRepository.findAll();
    }

    public Optional<Theme> getById(String id) {
        return themesRepository.findById(id);
    }

    public void createTheme(Theme theme) {
        themesRepository.save(theme);
    }

    public void removeTheme(String id) {
        Optional<Theme> page = themesRepository.findById(id);
        themesRepository.delete(page.get());
    }

    public void updateTheme(String id, Theme theme) {
        Optional<Theme> a = themesRepository.findById(id);

        if (a.isPresent()) {
            theme.setId(a.get().getId());

            themesRepository.save(theme);
        }
    }

    public Optional<Theme> getThemeByName(String name){
        return themesRepository.findByName(name);
    }

    // get all themes that logged in user is subscribed to
    public Iterable<Theme> getThemeSubsByUserId(String id) {
        List<Theme> theme_subs = new ArrayList<>();
        List<String> theme_subs_ids;
        Theme theme;

        Optional<User> user = userAccService.getById(id);

        theme_subs_ids = user.get().getTheme_subs();
        for (String theme_sub_id : theme_subs_ids) {
            theme = getById(theme_sub_id).get();
            theme_subs.add(theme);
        }
        return theme_subs;
    }

    // get all themes that logged in user owns
    public Iterable<Theme> getUserOwnedThemes(String id) {
        Query query = new Query();
        List<Theme> owned_themes;
        query.addCriteria(Criteria.where("owner").is(id));
        owned_themes = mongoTemplate.find(query, Theme.class);
        return owned_themes;
    }
}
