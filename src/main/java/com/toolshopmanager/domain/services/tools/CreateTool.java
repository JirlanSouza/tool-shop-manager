package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.CreateToolDTO;

import java.util.Optional;
import java.util.UUID;

public class CreateTool {
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;

    public CreateTool(ToolRepository repository, ToolTypeRepository toolTypeRepository) {
        this.toolRepository = repository;
        this.toolTypeRepository = toolTypeRepository;
    }

    public void perform(CreateToolDTO toolDTO) throws IllegalArgumentException {
        UUID toolTypeId = UUID.fromString(toolDTO.type());
        Optional<ToolType> toolType = toolTypeRepository.findById(toolTypeId);

        if (toolType.isEmpty()) {
            throw new IllegalArgumentException("Tool type can not exist");
        }

        Tool tool = Tool.create(toolDTO.name(), toolType.get());
        toolRepository.save(tool);
    }
}
