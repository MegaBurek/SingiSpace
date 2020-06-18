package singispace.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Page {

    @Id
    private String id;

    private String name;

    private String desc;

    private String owner;

    private List<String> members;

    private List<Post> feed;

    private List<String> categories;

    public Page() {
    }

    public Page(String id, String name, String desc, String owner, List<String> members, List<Post> feed, List<String> categories) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.owner = owner;
        this.members = members;
        this.feed = feed;
        this.categories = categories;
    }

    public Page(String name, String desc, String owner, List<String> members, List<Post> feed, List<String> categories) {
        this.name = name;
        this.desc = desc;
        this.owner = owner;
        this.members = members;
        this.feed = feed;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<Post> getFeed() {
        return feed;
    }

    public void setFeed(List<Post> feed) {
        this.feed = feed;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
