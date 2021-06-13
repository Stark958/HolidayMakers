package com.holidaymakers.model;

public class Image {

    private Long id;
    private String name;
    private String content_type;
    private Long size;
    private byte[] data;
    private Long tour_id;
    public Image() {
    }
    public Image(Long id, String name, String content_type, Long size, byte[] data, Long tour_id) {
        this.id = id;
        this.name = name;
        this.content_type = content_type;
        this.size = size;
        this.data = data;
        this.tour_id = tour_id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent_type() {
        return content_type;
    }
    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public Long getTour_id() {
        return tour_id;
    }
    public void setTour_id(Long tour_id) {
        this.tour_id = tour_id;
    }

    

}
