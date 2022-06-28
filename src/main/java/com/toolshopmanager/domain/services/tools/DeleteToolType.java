package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.DeleteToolTypeDTO;

import java.util.Optional;
import java.util.UUID;

public class DeleteToolType {
    private final ToolTypeRepository toolTypeRepository;

    public DeleteToolType(ToolTypeRepository toolTypeRepository) {
        this.toolTypeRepository = toolTypeRepository;
    }

    void perform(DeleteToolTypeDTO toolTypeData) {
        UUID toolTypeId = UUID.fromString(toolTypeData.id());
        Optional<ToolType> toolType = this.toolTypeRepository.findById(toolTypeId);

        if (toolType.isEmpty()) {
            throw  new IllegalArgumentException("ToolType is not exist");
        }

        this.toolTypeRepository.delete(toolTypeId);
    }
}
