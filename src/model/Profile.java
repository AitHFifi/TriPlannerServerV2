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

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    private Long id;
    private String fullName;
    private String address;
    private String emergencyContact;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Profile() {
    
    }

    public Profile(String fullName, String address, String emergencyContact, User user) {
        this.fullName = fullName;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
