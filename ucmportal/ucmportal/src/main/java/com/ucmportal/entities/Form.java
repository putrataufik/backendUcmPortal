package com.ucmportal.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "form")
@Getter
@Setter
@NoArgsConstructor
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_form")
    private Long id;

    @Column(name = "title_form")
    private String title;

    @Column(name = "type_form")
    private String type;

    @Column(name = "date_start_form")
    private Date dateStart;

    @Column(name = "date_end_form")
    private Date dateEnd;

    @Column(name = "description_form", length = 1500)
    private String description;

    @Lob
    @Column(name = "logo_form", length = 5071520)
    private byte[] logo;

    @Column(name = "link_google_form")
    private String link_google_form;

    @Column(name = "status_form")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_nim")
    @JsonIgnoreProperties("forms")
    private User user;


    public Form(Long id, String title, String type, Date date_start, Date date_end, String description, byte[] logo,
            String link_google_form, String status, User user) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.dateStart = date_start;
        this.dateEnd = date_end;
        this.description = description;
        this.logo = logo;
        this.link_google_form = link_google_form;
        this.status = status;
        this.user = user;
    }

}
