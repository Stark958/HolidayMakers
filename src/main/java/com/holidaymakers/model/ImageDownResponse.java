package com.holidaymakers.model;

public class ImageDownResponse {
    Long id;

    String url;

    

    public ImageDownResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }


    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
