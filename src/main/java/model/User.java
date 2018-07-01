package model;

import util.MD5Encoder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class User {
    public interface Status{
        int
                ONLINE=0x1,
                OFFLINE=0x2;
    }

    public User() {
    }

    private String username;
    private String id;

    public User(String username,String password) {
        this.username = username;
        try {
            this.id=MD5Encoder.encode(username+new Date().getTime());
            this.password = MD5Encoder.encode(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String password;
    private int status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
