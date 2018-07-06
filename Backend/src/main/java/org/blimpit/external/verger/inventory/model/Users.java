package org.blimpit.external.verger.inventory.model;



import org.blimpit.utils.usermanagement.model.User;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users{
    private String username;
    private String name;
    private String password;
    private String designation;
    private String status;
    private String deparment;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name;  }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }
}
