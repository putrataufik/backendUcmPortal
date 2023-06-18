package com.ucmportal.dto;

import java.sql.Date;

import com.ucmportal.entities.Form;

public class FormListAccDto {
    private byte[] logo;
    private String title;
    private String type;
    private Date date_start;
    private Date date_end;
    
    
    public FormListAccDto(byte[] logo, String title, String type, Date date_start, Date date_end) {
        this.logo = logo;
        this.title = title;
        this.type = type;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public FormListAccDto() {
    }
    
    public static FormListAccDto fromForm(Form form) {
        FormListAccDto formListAccDto = new FormListAccDto();
        formListAccDto.setLogo(form.getLogo());
        formListAccDto.setTitle(form.getTitle());
        formListAccDto.setType(form.getType());
        formListAccDto.setDate_start(form.getDateStart());
        formListAccDto.setDate_end(form.getDateEnd());
        return formListAccDto;
    }
    public byte[] getLogo() {
        return logo;
    }
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_start() {
        return date_start;
    }
    
    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }
    
    public Date getDate_end() {
        return date_end;
    }
    
    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
}
