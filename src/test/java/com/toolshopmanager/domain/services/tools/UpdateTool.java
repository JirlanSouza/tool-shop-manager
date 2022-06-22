package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolDTO;

import java.util.Optional;
import java.util.UUID;

public class UpdateTool {
    private final ToolRepository toolRepository;
    private  final ToolTypeRepository toolTypeRepository;

    public UpdateTool(ToolRepository toolRepository, ToolTypeRepository toolTypeRepository) {
        this.toolRepository = toolRepository;
        this.toolTypeRepository = toolTypeRepository;
    }

    public void perform(UpdateToolDTO toolData) {
        UUID toolId = UUID.fromString(toolData.getId());
        UUID toolTypeId = UUID.fromString(toolData.getTypeId());
        Optional<Tool> tool = this.toolRepository.findById(toolId);
        Optional<ToolType> toolType = this.toolTypeRepository.findById(toolTypeId);

        Tool updatedTool = Tool.create(toolId, toolData.getName(), toolType.get());
        this.toolRepository.update(toolId, updatedTool);
    }
}