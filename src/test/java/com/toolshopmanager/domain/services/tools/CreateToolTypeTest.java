package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.CreateToolTypeDTO;
import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CreateToolTypeTest {
    private CreateToolType createToolType;
    private ToolTypeRepository toolTypeRepository;

    @BeforeEach
    void setUp() {
        this.toolTypeRepository = new TooTypeInMemoryRepository();
        this.createToolType = new CreateToolType(this.toolTypeRepository);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNameIsInvalid() {
        CreateToolTypeDTO createToolTypeDTO = new CreateToolTypeDTO("ab");

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.createToolType.perform(createToolTypeDTO));
    }

    @Test
    void mustHaveToolTypeNameSameWithPassedNameToNameInRepository() {
        CreateToolTypeDTO createToolTypeDTO = new CreateToolTypeDTO("Elétrica");

        this.createToolType.perform(createToolTypeDTO);
        List<ToolType> createdToolType = this.toolTypeRepository.findAll();

        Assertions.assertEquals(createToolTypeDTO.name(), createdToolType.get(0).getName());
    }
}