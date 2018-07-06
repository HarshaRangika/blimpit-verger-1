package org.blimpit.external.verger.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FilePathModel {

    private String localFileLocation;
    private String destination;

    public String getLocalFileLocation() {
        return localFileLocation;
    }

    public void setLocalFileLocation(String localFileLocation) {
        this.localFileLocation = localFileLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
