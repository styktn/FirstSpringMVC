package com.springmvcproject.core.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yektan on 29.12.2016.
 */

@Entity
@Table(name = "content")
public class Content {

    private int id;
    private String title;
    private String text;
    private Menu menu;
    private Set<File> files;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "menu_id")
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @OneToMany(mappedBy = "content")
    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

}
