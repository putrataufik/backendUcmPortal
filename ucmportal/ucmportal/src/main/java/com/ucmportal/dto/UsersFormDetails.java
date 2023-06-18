package com.ucmportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UsersFormDetails
 */

 @Getter
 @Setter
 @NoArgsConstructor
public class UsersFormDetails {
    private String userName;
    private Long nim;
    private String major;
    private String formTitle;
    private String status;
    
    public UsersFormDetails(String userName, Long nim, String major, String formTitle, String status) {
        this.userName = userName;
        this.nim = nim;
        this.major = major;
        this.formTitle = formTitle;
        this.status = status;
    }

    


}