package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Page;
import singispace.domain.Post;
import singispace.domain.Theme;

import java.util.Optional;

@Service
public class PostService{

    @Autowired
    private ThemesService themesService;

    @Autowired
    private PagesService pagesService;

    public Optional<Theme> createThemePost(String id, Post post){
        System.out.println("This should be the post" + post.toString());
        Optional<Theme> selectedTheme = themesService.getById(id);
        selectedTheme.get().getFeed().add(post);
        themesService.updateTheme(id, selectedTheme.get());
        return selectedTheme;
    }

    public Optional<Page> createPagePost(String id, Post post){
        Optional<Page> selectedPage = pagesService.getById(id);
        selectedPage.get().getFeed().add(post);
        pagesService.updatePage(id, selectedPage.get());
        return selectedPage;
    }
}