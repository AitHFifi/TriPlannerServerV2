/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Hp
 */
import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_codes")
public class Otp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private LocalDateTime expirationTime;
    private boolean used;
    @Column(nullable = false)
    private String purpose; // e.g. "REGISTER", "RESET_PASSWORD"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Otp() {}

     public Otp(String code, LocalDateTime expirationTime, boolean used, String purpose, User user) {
        this.code = code;
        this.expirationTime = expirationTime;
        this.used = used;
        this.purpose = purpose;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
