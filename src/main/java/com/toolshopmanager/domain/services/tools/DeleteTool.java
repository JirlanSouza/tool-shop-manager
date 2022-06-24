package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.services.tools.dtos.DeleteToolDTO;

import java.util.UUID;

public class DeleteTool {
    private ToolRepository toolRepository;

    public DeleteTool(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public void perform(DeleteToolDTO toolData) {
        UUID toolId = UUID.fromString(toolData.id());
        this.toolRepository.delete(toolId);
    }
}
