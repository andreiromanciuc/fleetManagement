package com.andrei.fleetManagement.domain;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;
    private String dateOfMessage;
    private String content;
    private String owner;
    private boolean unread;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateOfMessage() {
        return dateOfMessage;
    }

    public void setDateOfMessage(String dateOfMessage) {
        this.dateOfMessage = dateOfMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
