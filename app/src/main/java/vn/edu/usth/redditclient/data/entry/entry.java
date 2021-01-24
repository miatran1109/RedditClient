package vn.edu.usth.redditclient.data.entry;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
@Root(name = "entry", strict = false)
public class entry implements Serializable {
    @Element(required = false, name = "author")
    private author author;
    @Element(required = false, name = "category")
    private String category;
    @Element(name = "content")
    private String content;
    @Element(name = "updated")
    private String updated;
    @Element(name = "title")
    private String title;
    @Element(name = "id")
    private String id;

    public entry() {

    }

    public entry(author author, String category, String content, String updated, String title, String id) {
        this.author = author;
        this.category = category;
        this.content = content;
        this.updated = updated;
        this.title = title;
        this.id = id;
    }

    public author getAuthor() {
        return author;
    }

    public void setAuthor(author author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entry{" +
                "author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", updated='" + updated + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
