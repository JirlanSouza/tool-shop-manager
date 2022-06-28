package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.DeleteToolTypeDTO;
import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

public class DeleteToolTypeTest {
    DeleteToolType deleteToolType;
    ToolTypeRepository toolTypeRepository;

    @BeforeEach
    void setup() {
        this.toolTypeRepository = new TooTypeInMemoryRepository();
        this.deleteToolType = new DeleteToolType(this.toolTypeRepository);
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenIdIsInvalid() {
        DeleteToolTypeDTO deleteToolTypeDTO = new DeleteToolTypeDTO("invalid id");

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.deleteToolType.perform(deleteToolTypeDTO));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenToolTYpeIsNotExist() {
        DeleteToolTypeDTO deleteToolTypeDTO = new DeleteToolTypeDTO(UUID.randomUUID().toString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.deleteToolType.perform(deleteToolTypeDTO));
    }

    @Test
    void ShouldDeleteToolType() {
        ToolType toolType = ToolType.create("Elétrica");
        DeleteToolTypeDTO deleteToolTypeDTO = new DeleteToolTypeDTO(toolType.getId().toString());
        this.toolTypeRepository.save(toolType);

        this.deleteToolType.perform(deleteToolTypeDTO);
        Optional<ToolType> toolTypeFromRepository = this.toolTypeRepository.findById(toolType.getId());

        Assertions.assertTrue(toolTypeFromRepository.isEmpty());
    }
}
