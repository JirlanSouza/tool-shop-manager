package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolDTO;
import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import com.toolshopmanager.infra.database.repository.ToolInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

public class UpdateToolTest {
    private UpdateTool updateTool;
    private ToolRepository toolRepository;
    private ToolTypeRepository toolTypeRepository;

    @BeforeEach
    void setUp() {
        this.toolRepository = new ToolInMemoryRepository();
        this.toolTypeRepository = new TooTypeInMemoryRepository();
        this.updateTool = new UpdateTool(this.toolRepository, this.toolTypeRepository);
    }

    @Test
    void ExpectTheNameAndTypeIdOfToolToBeTheSameAsTheOnePassed() {
        ToolType oldToolType = ToolType.create("Mecânica");
        ToolType newToolType = ToolType.create("Mecânica");

        Tool tool = Tool.create("Alavanca", oldToolType);
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(
            tool.getId().toString(),
            "Lixadeira",
            oldToolType.getId().toString()
        );

        this.toolTypeRepository.save(oldToolType);
        this.toolTypeRepository.save(newToolType);
        this.toolRepository.save(tool);
        this.updateTool.perform(updateToolDTO);

        Optional<Tool> updatedTool = this.toolRepository.findById(tool.getId());

        Assertions.assertEquals(updateToolDTO.name(), updatedTool.get().getName());
        Assertions.assertEquals(updateToolDTO.typeId(), updatedTool.get().getType().getId().toString());

    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenTypeIdIsInvalid() {
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(
            UUID.randomUUID().toString(),
            "Lixadeira",
            "invalid-UUID"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.updateTool.perform(updateToolDTO));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenToolTypeNotExist() {
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(
            UUID.randomUUID().toString(),
            "Lixadeira",
            UUID.randomUUID().toString()
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.updateTool.perform(updateToolDTO));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenIdIsInvalid() {
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(
            "invalid-UUID",
            "Lixadeira",
            UUID.randomUUID().toString()
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.updateTool.perform(updateToolDTO));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenToolNotExist() {
        ToolType toolType = ToolType.create("Elétrica");
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(
            UUID.randomUUID().toString(),
            "Lixadeira",
            toolType.getId().toString()
        );

        this.toolTypeRepository.save(toolType);

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.updateTool.perform(updateToolDTO));
    }
}
