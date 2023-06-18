package com.ucmportal.dto;

import java.sql.Date;

public class FormDto {
    private Long id;
    private String title;
    private String type;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private String linkGoogleForm;
    private String status;
    private byte[] logo;

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public FormDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkGoogleForm() {
        return linkGoogleForm;
    }

    public void setLinkGoogleForm(String linkGoogleForm) {
        this.linkGoogleForm = linkGoogleForm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FormDto(Long id, String title, String type, Date dateStart, Date dateEnd, String description,
            String linkGoogleForm, String status, byte[] logo) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.linkGoogleForm = linkGoogleForm;
        this.status = status;
        this.logo = logo;
    }

}
