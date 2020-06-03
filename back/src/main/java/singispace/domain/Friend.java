package singispace.domain;

import java.util.List;

public class Friend {

    private String name;
    private String surname;
    private String email;
    private String imgUrl;
    private List<String> page_subs;
    private List<String> theme_subs;
    private List<String> friends;

    public Friend(){}

    public Friend(String name, String surname, String email, String imgUrl, List<String> page_subs, List<String> theme_subs, List<String> friends) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.imgUrl = imgUrl;
        this.page_subs = page_subs;
        this.theme_subs = theme_subs;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getPage_subs() {
        return page_subs;
    }

    public void setPage_subs(List<String> page_subs) {
        this.page_subs = page_subs;
    }

    public List<String> getTheme_subs() {
        return theme_subs;
    }

    public void setTheme_subs(List<String> theme_subs) {
        this.theme_subs = theme_subs;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
