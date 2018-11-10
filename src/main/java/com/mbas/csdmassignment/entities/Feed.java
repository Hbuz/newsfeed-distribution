package com.mbas.csdmassignment.entities;

import javax.persistence.*;

@Entity
@Table(name="feed")
public class Feed {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private String pubDate;

    private String image;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", pubblication date=" + pubDate + ", author=" + image + "]";
    }
}
