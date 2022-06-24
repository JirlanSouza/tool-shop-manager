package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.services.tools.dtos.DeleteToolDTO;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolDTO;
import com.toolshopmanager.infra.database.repository.ToolInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class DeleteToolTest {
    private DeleteTool deleteTool;

    @BeforeEach
    void setup() {
        ToolRepository toolRepository = new ToolInMemoryRepository();
        this.deleteTool = new DeleteTool(toolRepository);
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
}
