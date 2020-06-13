package com.swigoProject.swigobackend.model;

public class MailModel {

    private String from;
    private String to;
    private String subject;
    private String content;

    private String name;

    public MailModel() {
    }

    public MailModel(String from, String to, String subject, String content, String name) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.name = name;

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
