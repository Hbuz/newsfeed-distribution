package com.mbas.csdmassignment.entities;

import javax.persistence.*;

@Entity
@Table(name="news")
public class News {
    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private String pubblicationData;

    private String image;

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

    public String getPubblicationData() {
        return pubblicationData;
    }

    public void setPubblicationData(String pubblicationData) {
        this.pubblicationData = pubblicationData;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
