package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.CreateToolTypeDTO;

public class CreateToolType {
    private ToolTypeRepository toolTypeRepository;

    public CreateToolType(ToolTypeRepository toolTypeRepository) {
        this.toolTypeRepository = toolTypeRepository;
    }

    public void perform(CreateToolTypeDTO toolTypeData) {
        ToolType toolType = ToolType.create(toolTypeData.name());
        this.toolTypeRepository.save(toolType);
    }
}
