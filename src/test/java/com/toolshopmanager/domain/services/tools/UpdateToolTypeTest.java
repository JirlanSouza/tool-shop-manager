package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolTypeDTO;
import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

public class UpdateToolTypeTest {
    private UpdateToolType updateToolType;
    private ToolTypeRepository toolTypeRepository;

    @BeforeEach
    void setUp() {
        this.toolTypeRepository = new TooTypeInMemoryRepository();
        this.updateToolType = new UpdateToolType(this.toolTypeRepository);
    }

    @Test
    void ExpectTheNameOfToolToBeTheSameAsTheOnePassed() {
        ToolType toolType = ToolType.create("Mecânica");

        UpdateToolTypeDTO updateToolTypeDTO = new UpdateToolTypeDTO(
            toolType.getId().toString(),
            "Lixadeira"
        );

        this.toolTypeRepository.save(toolType);
        this.updateToolType.perform(updateToolTypeDTO);

        Optional<ToolType> updatedTool = this.toolTypeRepository.findById(toolType.getId());
        Assertions.assertEquals(updateToolTypeDTO.name(), updatedTool.get().getName());

    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenIdIsInvalid() {
        UpdateToolTypeDTO updateToolTypeDTO = new UpdateToolTypeDTO(
            "invalid id",
            "Lixadeira"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.updateToolType.perform(updateToolTypeDTO));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenToolTypeNotExist() {
        UpdateToolTypeDTO updateToolTypeDTO = new UpdateToolTypeDTO(
            UUID.randomUUID().toString(),
            "Lixadeira"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.updateToolType.perform(updateToolTypeDTO));
    }
}
