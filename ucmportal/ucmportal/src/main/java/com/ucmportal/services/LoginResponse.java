package com.ucmportal.services;

import com.ucmportal.dto.UserSummaryDto;

public class LoginResponse {

    private String message;
    private boolean status;
    private UserSummaryDto userSummary;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserSummaryDto getUserSummary() {
        return userSummary;
    }

    public void setUserSummary(UserSummaryDto userSummary) {
        this.userSummary = userSummary;
    }

    public LoginResponse(String message, boolean status, UserSummaryDto userSummary) {
        this.message = message;
        this.status = status;
        this.userSummary = userSummary;
    }

}
