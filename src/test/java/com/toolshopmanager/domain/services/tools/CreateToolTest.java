package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import com.toolshopmanager.infra.database.repository.ToolInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void perform_ShouldThrowIllegalArgumentExceptionWhenTypeNotExist() {
        CreateTool createTool = new CreateTool(this.toolRepository, toolTypeRepository);
        CreateToolDTO createToolDTO = new CreateToolDTO("Lixadeira", UUID.randomUUID().toString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {createTool.perform(createToolDTO);});
    }
}