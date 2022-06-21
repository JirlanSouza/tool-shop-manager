package com.toolshopmanager.domain.services.tools;

public class CreateToolDTO {
    public String name;
    public String type;

    public CreateToolDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }
}