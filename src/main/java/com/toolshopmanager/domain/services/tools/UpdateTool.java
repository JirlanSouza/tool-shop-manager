package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolDTO;

import java.util.Optional;
import java.util.UUID;

public class UpdateTool {
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;

    public UpdateTool(ToolRepository toolRepository, ToolTypeRepository toolTypeRepository) {
        this.toolRepository = toolRepository;
        this.toolTypeRepository = toolTypeRepository;
    }

    public void perform(UpdateToolDTO toolData) {
        UUID toolId = UUID.fromString(toolData.id());
        UUID toolTypeId = UUID.fromString(toolData.typeId());
        Optional<Tool> tool = this.toolRepository.findById(toolId);
        Optional<ToolType> toolType = this.toolTypeRepository.findById(toolTypeId);

        if (toolType.isEmpty()) {
            throw new IllegalArgumentException("Type is not exist");
        }


        Tool updatedTool = Tool.create(toolId, toolData.name(), toolType.get());
        this.toolRepository.update(toolId, updatedTool);
    }
}
