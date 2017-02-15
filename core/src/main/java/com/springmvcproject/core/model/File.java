package com.springmvcproject.core.model;

import javax.persistence.*;

/**
 * Created by yektan on 13.02.2017.
 */
@Entity
@Table(name = "file")
public class File {
    private int id;
    private byte[] file;
    private String description;
    private Menu menu;
    private Announcement announcement;
    private Content content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "file")
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "announcement_id",nullable = true)
    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id",nullable = true)
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id",nullable = true)
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
