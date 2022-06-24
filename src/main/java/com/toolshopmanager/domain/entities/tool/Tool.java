package com.toolshopmanager.domain.entities.tool;

import com.toolshopmanager.domain.entities.Entity;
import com.toolshopmanager.domain.entities.tool.valueObjects.Name;

import java.util.UUID;

public class Tool extends Entity {
    private final Name name;
    private final ToolType type;

    private Tool(Name name, ToolType type) {
        super();
        this.name = name;
        this.type = type;
    }

    private Tool(UUID id, Name name, ToolType type) {
        super(id);
        this.name = name;
        this.type = type;
    }

    public static Tool create(String name, ToolType type) throws IllegalArgumentException {
        Name validName = new Name(name);
        return new Tool(validName, type);
    }

    public static Tool create(UUID id, String name, ToolType type) throws IllegalArgumentException {
        Name validName = new Name(name);
        return new Tool(id, validName, type);
    }

    public String getName() {
        return name.getValue();
    }

    public ToolType getType() {
        return type;
    }
}
