package singispace.dto;

import java.util.List;

public class UserViewDTO {
    private String name;
    private String surname;
    private String email;
    private String imgUrl;
    private List<String> page_subs;
    private List<String> theme_subs;
    private List<String> friends;

    public UserViewDTO() {

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
