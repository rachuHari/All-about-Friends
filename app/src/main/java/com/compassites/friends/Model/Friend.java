package com.compassites.friends.Model;

/**
 * Created by admin on 01-03-2016.
 */
public class Friend {

    String type;
    String image_url;
    String location_url;
    String name;
    String title;
    String more_images_url;
    String content;
    boolean isSimple;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLocation_url() {
        return location_url;
    }

    public void setLocation_url(String location_url) {
        this.location_url = location_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMore_images_url() {
        return more_images_url;
    }

    public void setMore_images_url(String more_images_url) {
        this.more_images_url = more_images_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSimple() {
        return isSimple;
    }

    public void setIsSimple(boolean isSimple) {
        this.isSimple = isSimple;
    }
}
