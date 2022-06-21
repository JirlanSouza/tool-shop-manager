package com.toolshopmanager.domain.entities.tool;

import com.toolshopmanager.domain.entities.Entity;

import java.util.UUID;

public class ToolType extends Entity {
    private UUID id;
    private final String name;

    public ToolType(String name) {
        super();
        this.name = name;
    }

    public ToolType(UUID id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
