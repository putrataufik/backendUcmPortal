package com.ucmportal.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    private Long nim;

    @Column(name = "user_name", length = 50)
    private String name;

    @Column(name = "major_name", length = 50)
    private String major;

    @Column(name = "faculty_name", length = 50)
    private String faculty;

    @Column(name = "speciality_name", length = 50)
    private String speciality;

    @Column(name = "password")
    private String password;

    @Column(name = "user_level", length = 20)
    private String level;

    @OneToMany(mappedBy = "user")
    private List<Form> forms;

    public User() {
    }

    public User(Long nim, String name, String major, String faculty, String speciality,
            String password, String level) {
        this.nim = nim;
        this.name = name;
        this.major = major;
        this.faculty = faculty;
        this.speciality = speciality;
        this.password = password;
        this.level = level;
    }

    public Long getNim() {
        return nim;
    }

    public void setNim(Long nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
