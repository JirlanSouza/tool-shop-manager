package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.CreateToolDTO;
import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import com.toolshopmanager.infra.database.repository.ToolInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class CreateToolTest {
    private ToolRepository toolRepository;
    private ToolTypeRepository toolTypeRepository;

    @BeforeEach
    void setUp() {
        this.toolRepository = new ToolInMemoryRepository();
        this.toolTypeRepository = new TooTypeInMemoryRepository();
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTypeNotExist() {
        CreateToolDTO createToolDTO = new CreateToolDTO("Lixadeira", UUID.randomUUID().toString());
        CreateTool createTool = this.makeCreateTool();
        Assertions.assertThrows(IllegalArgumentException.class, () -> createTool.perform(createToolDTO));
    }

    @Test
    void mustHaveThePropertiesOfTheTypeEqualToPassedType() {
        ToolType tooltype = ToolType.create("Eletrica");
        this.toolTypeRepository.save(tooltype);
        CreateToolDTO createToolDTO = new CreateToolDTO("Lixadeira", tooltype.getId().toString());
        CreateTool createTool = this.makeCreateTool();
        createTool.perform(createToolDTO);
        List<Tool> createdTool = this.toolRepository.findAll();

        Assertions.assertEquals(tooltype, createdTool.get(0).getType());
    }

    private CreateTool makeCreateTool() {
        return new CreateTool(this.toolRepository, this.toolTypeRepository);
    }


}