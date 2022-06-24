package com.toolshopmanager.domain.entities.tool.valueObjects;

public class Name {
    private String value;

    public Name(String value) {
        this.value = this.validate(value);
    }

    private String validate(String name) throws IllegalArgumentException {
        String trimmedName = name.trim();
        if (trimmedName.length() < 3) {
            throw new IllegalArgumentException("Name is less than 3 characters");
        }
        return trimmedName;
    }

    public String getValue() {
        return value;
    }
}
