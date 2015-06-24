package ru.koleslena.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by elenko on 23.06.15.
 */
@Entity
public class Item {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private Boolean published;
    @Column
    private String content;

    public Item() {
    }

    public Item(int format) {
        id = String.format("item %d", format);
        name = String.format("name %d", format);
        content = String.format("content %d", format);
        published = (format&1) == 0;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
