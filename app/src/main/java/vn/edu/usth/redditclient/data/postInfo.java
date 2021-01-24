package vn.edu.usth.redditclient.data;

public class postInfo {
    private String title;
    private String author;
    private String updated;
    private String postURL;
    private String imgURL;

    public postInfo(String title, String author, String updated, String postURL, String imgURL) {
        this.title = title;
        this.author = author;
        this.updated = updated;
        this.postURL = postURL;
        this.imgURL = imgURL;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

}
