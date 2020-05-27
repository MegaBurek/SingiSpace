package singispace.domain;

public class Post {

    private String title;

    private String img_url;

    private String owner;

    public Post(){

    }

    public Post( String title, String img_url, String owner) {
        this.title = title;
        this.img_url = img_url;
        this.owner = owner;
    }

    public Post(String title, String owner) {
        this.title = title;
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
