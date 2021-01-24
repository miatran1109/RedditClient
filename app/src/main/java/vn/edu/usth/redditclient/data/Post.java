package vn.edu.usth.redditclient.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

import vn.edu.usth.redditclient.data.entry.entry;

@Root(name = "Post", strict = false)
public class Post implements Serializable {
    @Element(name = "id")
    private String id;
    @Element(name = "title")
    private String title;
    @Element(name = "updated")
    private String updated;
    @Element(name = "subtitle")
    private String subtitle;
    @ElementList(inline = true,name = "entries")
    private List<entry> entries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<entry> getEntries() {
        return entries;
    }

    public void setEntries(List<entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Post: \n [Entry: \n" + entries +"]";
    }
}
