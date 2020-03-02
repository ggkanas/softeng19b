package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class User {

    private String username;
    private String password;
    private String email;
    private int requestsPerDayQuota; //negative values indicate no quota
    private Timestamp period;
    private int remainingRequests;
    private String token;

    public User() {
        //Keep this for json encoding/decoding
    }

    public User(String username, String email, String password, int requestsPerDayQuota, Timestamp period, int remainingRequests) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.requestsPerDayQuota = requestsPerDayQuota;
        this.period = period;
        this.remainingRequests = remainingRequests;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRequestsPerDayQuota(int requestsPerDayQuota) {
        this.requestsPerDayQuota = requestsPerDayQuota;
    }

    public int getRequestsPerDayQuota() {
        return requestsPerDayQuota;
    }

    public void setRemainingRequests(int remainingRequests) {
        this.remainingRequests = remainingRequests;
    }

    public int getRemainingRequests() {
        return remainingRequests;
    }

    public Timestamp getPeriod() {
        return period;
    }

    public void setPeriod(Timestamp period) {
        this.period = period;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
