package com.springmvcproject.core.model;

import com.springmvcproject.core.account.model.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yektan on 29.12.2016.
 */
@Entity
@Table(name = "menu")
public class Menu {

    private int id;
    private String title;
    private int status;
    private User user;
    private Set<Content> contents;
    private Set<File> files;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "menu")
    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }

    @OneToMany(mappedBy = "menu")
    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }
}
