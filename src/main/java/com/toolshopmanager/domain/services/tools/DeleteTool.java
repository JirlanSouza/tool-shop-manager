package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.services.tools.dtos.DeleteToolDTO;

import java.util.Optional;
import java.util.UUID;

public class DeleteTool {
    private final ToolRepository toolRepository;

    public DeleteTool(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public void perform(DeleteToolDTO toolData) {
        UUID toolId = UUID.fromString(toolData.id());
        Optional<Tool> tool = this.toolRepository.findById(toolId);

        if (tool.isEmpty()) {
            throw new IllegalArgumentException("Tool is not exist");
        }

        this.toolRepository.delete(toolId);
    }
}
