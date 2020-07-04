package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Page;
import singispace.domain.Post;
import singispace.domain.Theme;
import singispace.repositories.PagesRepository;
import singispace.repositories.PostRepository;
import singispace.repositories.ThemesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private ThemesService themesService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ThemesRepository themesRepository;

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private PagesService pagesService;

    public Optional<Post> getById(String id){
        return postRepository.findById(id);
    }

    public Optional<Theme> createThemePost(String id, Post post) {
        postRepository.save(post);
        Optional<Theme> selectedTheme = themesService.getById(id);
        selectedTheme.get().getFeed().add(post.getId());
        themesService.updateTheme(id, selectedTheme.get());
        return selectedTheme;
    }

    public Iterable<Post> getThemeFeed(String name) {
        List<String> postIds;
        List<Post> posts = new ArrayList();
        Post post;

        Optional<Theme> selectedTheme = themesRepository.findByName(name);
        postIds = selectedTheme.get().getFeed();
        for (String postId : postIds) {
            post = getById(postId).get();
            posts.add(post);
        }
        return posts;
    }

    public Iterable<Post> getPageFeed(String name) {
        List<String> postIds;
        List<Post> posts = new ArrayList();
        Post post;

        Optional<Page> selectedPage = pagesRepository.findByName(name);
        postIds = selectedPage.get().getFeed();
        for (String postId : postIds) {
            post = getById(postId).get();
            posts.add(post);
        }
        return posts;
    }

    public Optional<Page> createPagePost(String id, Post post) {
        postRepository.save(post);
        Optional<Page> selectedPage = pagesService.getById(id);
        selectedPage.get().getFeed().add(post.getId());
        pagesService.updatePage(id, selectedPage.get());
        return selectedPage;
    }
}