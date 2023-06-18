package com.ucmportal.dto;

import java.sql.Date;

public class MyActivityData {
    private Long id;
    private String title;
    private String type;
    private Date date_start;
    private Date date_end;
    private String status;
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyActivityData() {
    }
    
    public MyActivityData(Long id, String title, String type, Date date_start, Date date_end,
            String status) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date_start = date_start;
        this.date_end = date_end;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
    public Date getDate_start() {
        return date_start;
    }
    public Date getDate_end() {
        return date_end;
    }
    public String getStatus() {
        return status;
    }

}
