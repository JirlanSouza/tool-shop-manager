package com.toolshopmanager.domain.entities.tool;

import com.toolshopmanager.domain.entities.Entity;

import java.util.UUID;

public class Tool extends Entity {
    private final String name;
    private final ToolType type;

    private Tool(String name, ToolType type) {
        super();
        this.name = name;
        this.type = type;
    }

    private Tool(UUID id, String name, ToolType type) {
        super(id);
        this.name = name;
        this.type = type;
    }

    public static Tool create(String name, ToolType type) throws IllegalArgumentException {
        String validName = Tool.validateName(name);
        return new Tool(validName, type);
    }

    private static String validateName(String name) throws IllegalArgumentException {
        String trimmedName = name.trim();
        if (trimmedName.length() < 3) {
            throw new IllegalArgumentException("Name is less than 3 characters");
        }
        return trimmedName;
    }

    public String getName() {
        return name;
    }

    public ToolType getType() {
        return type;
    }
}
