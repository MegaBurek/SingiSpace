package singispace.domain;

public class Post {

    private String title;

    private String textContent;

    private String imgContent;

    private String owner;

    private Comment[] comments;

    private Like[] likes;

    public Post(){

    }

    public Post(String title, String textContent, String imgContent, String owner, Comment[] comments, Like[] likes) {
        this.title = title;
        this.textContent = textContent;
        this.imgContent = imgContent;
        this.owner = owner;
        this.comments = comments;
        this.likes = likes;
    }

    public Post(String title, String textContent, String owner, Comment[] comments, Like[] likes) {
        this.title = title;
        this.textContent = textContent;
        this.owner = owner;
        this.comments = comments;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public Like[] getLikes() {
        return likes;
    }

    public void setLikes(Like[] likes) {
        this.likes = likes;
    }
}
