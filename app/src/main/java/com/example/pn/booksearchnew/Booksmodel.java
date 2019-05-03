package com.example.pn.booksearchnew;

class Booksmodel {
    String id1,title1,author1,publisher1,publishdate1,image1;

    public Booksmodel(String id, String title, String authors, String publisher, String publishdate, String image) {
        this.id1 = id;
        this.title1 = title;
        this.author1 = authors;
        this.publisher1 = publisher;
        this.publishdate1 = publishdate;
        this.image1 = image;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getPublisher1() {
        return publisher1;
    }

    public void setPublisher1(String publisher1) {
        this.publisher1 = publisher1;
    }

    public String getPublishdate1() {
        return publishdate1;
    }

    public void setPublishdate1(String publishdate1) {
        this.publishdate1 = publishdate1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }
}
