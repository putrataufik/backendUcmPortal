package com.ucmportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSummaryDto {
    
    private String name;
    private String userLevel;
    private String major;

    public UserSummaryDto(String name, String userLevel, String major) {
        this.name = name;
        this.userLevel = userLevel;
        this.major = major;
    }


}
