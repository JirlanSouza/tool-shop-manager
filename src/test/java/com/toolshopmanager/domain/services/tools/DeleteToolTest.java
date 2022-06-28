package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.DeleteToolDTO;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolDTO;
import com.toolshopmanager.infra.database.repository.ToolInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

public class DeleteToolTest {
    private DeleteTool deleteTool;
    private ToolRepository toolRepository;

    @BeforeEach
    void setup() {
        this.toolRepository = new ToolInMemoryRepository();
        this.deleteTool = new DeleteTool(this.toolRepository);
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenIdIsInvalid() {
        DeleteToolDTO updateToolDTO = new DeleteToolDTO("invalid-UUID");

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.deleteTool.perform(updateToolDTO));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenToolNotExist() {
        DeleteToolDTO deleteToolDTO = new DeleteToolDTO(
            UUID.randomUUID().toString()
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.deleteTool.perform(deleteToolDTO));
    }

    @Test
    void ShouldDeleteTool() {
        ToolType toolType = ToolType.create("Elétrica");
        Tool tool = Tool.create("Lixadeira", toolType);
        DeleteToolDTO deleteToolDTO = new DeleteToolDTO(tool.getId().toString());
        this.toolRepository.save(tool);

        this.deleteTool.perform(deleteToolDTO);
        Optional<Tool> toolFromRepository = this.toolRepository.findById(tool.getId());

        Assertions.assertTrue(toolFromRepository.isEmpty());
    }
}
