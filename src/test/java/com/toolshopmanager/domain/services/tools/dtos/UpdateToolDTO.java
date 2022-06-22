package com.toolshopmanager.domain.services.tools.dtos;

public class UpdateToolDTO {
    private final String id;
    private final String name;
    private final String typeId;

    public UpdateToolDTO(String id, String name, String typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeId() {
        return typeId;
    }
}
