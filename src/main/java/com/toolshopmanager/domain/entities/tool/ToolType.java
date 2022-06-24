package com.toolshopmanager.domain.entities.tool;

import com.toolshopmanager.domain.entities.Entity;
import com.toolshopmanager.domain.entities.tool.valueObjects.Name;

import java.util.UUID;

public class ToolType extends Entity {
    private UUID id;
    private final Name name;

    public ToolType(Name name) {
        super();
        this.name = name;
    }

    public ToolType(UUID id, Name name) {
        super(id);
        this.name = name;
    }

    public static ToolType create(String name) {
        Name validName = new Name(name);
        return new ToolType(validName);
    }

    public static ToolType create(UUID id, String name) {
        Name validName = new Name(name);
        return new ToolType(id, validName);
    }

    public String getName() {
        return name.getValue();
    }
}
