package com.unimagdalena.android.app.uphotossocial;

/*
 * Created by albert on 2/12/2015.
 */

public class Images {

    private int image;
    private String title;
    private String author;
    private int likeButton;

    public Images(int image) {
        this.image = image;
    }

    public Images(int image, String title, String author, int likeButton) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.likeButton = likeButton;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikeButton() {
        return likeButton;
    }

    public void setLikeButton(int likeButton) {
        this.likeButton = likeButton;
    }
}
