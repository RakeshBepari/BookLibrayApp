package com.example.mybooklibrary;

public class Book {
    private int id;
    private String name;
    private String author;
    private String imageUrl;
    private int pages;
    private String shorDes;
    private String longDes;
    private boolean isExpanded;

    public Book(int id, String name, String author, String imageUrl, int pages, String shorDes, String longDes) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.pages = pages;
        this.shorDes = shorDes;
        this.longDes = longDes;
        isExpanded=false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getShorDes() {
        return shorDes;
    }

    public void setShorDes(String shorDes) {
        this.shorDes = shorDes;
    }

    public String getLongDes() {
        return longDes;
    }

    public void setLongDes(String longDes) {
        this.longDes = longDes;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", pages=" + pages +
                ", shorDes='" + shorDes + '\'' +
                ", longDes='" + longDes + '\'' +
                '}';
    }
}
