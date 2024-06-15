package com.bruno.enums;

public enum Category {
    BACK_END("Back-End"), FRONT_END("Front-End");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    // toString
    @Override
    public String toString() {
        return this.value;
    }
}
