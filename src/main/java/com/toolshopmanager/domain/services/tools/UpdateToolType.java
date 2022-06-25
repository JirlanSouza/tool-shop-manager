package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolTypeDTO;

import java.util.Optional;
import java.util.UUID;

public class UpdateToolType {
    private final ToolTypeRepository toolTypeRepository;

    public UpdateToolType(ToolTypeRepository toolTypeRepository) {
        this.toolTypeRepository = toolTypeRepository;
    }

    public void perform(UpdateToolTypeDTO toolTypeData) {
        UUID toolTypeId = UUID.fromString(toolTypeData.id());

        Optional<ToolType> existedToolType = this.toolTypeRepository.findById(toolTypeId);

        if (existedToolType.isEmpty()) {
            throw new IllegalArgumentException("ToolType is not exist");
        }

        ToolType toolType = ToolType.create(toolTypeId, toolTypeData.name());

        this.toolTypeRepository.update(toolTypeId, toolType);
    }
}
