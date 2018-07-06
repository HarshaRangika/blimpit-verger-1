package org.blimpit.external.verger.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Log {
    private String username;
    private String logcount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogcount() {
        return logcount;
    }

    public void setLogcount(String logcount) {
        this.logcount = logcount;
    }
}
