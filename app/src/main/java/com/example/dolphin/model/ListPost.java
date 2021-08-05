package com.example.dolphin.model;

public class ListPost {
    private String head;
    private String description;

    public ListPost(String head, String description) {
        this.head = head;
        this.description = description;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }
}
