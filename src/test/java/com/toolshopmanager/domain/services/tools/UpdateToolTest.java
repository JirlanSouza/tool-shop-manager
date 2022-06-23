package com.toolshopmanager.domain.services.tools;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.dtos.UpdateToolDTO;
import com.toolshopmanager.infra.database.repository.TooTypeInMemoryRepository;
import com.toolshopmanager.infra.database.repository.ToolInMemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.HashMap;
import java.util.List;
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
        ToolType oldToolType = new ToolType("Mecânica");
        ToolType newToolType = new ToolType("Mecânica");

        Tool tool = Tool.create("Alavanca", oldToolType);
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(tool.getId().toString(),"Lixadeira", oldToolType.getId().toString());

        this.toolTypeRepository.save(oldToolType);
        this.toolTypeRepository.save(newToolType);
        this.toolRepository.save(tool);
        this.updateTool.perform(updateToolDTO);

        Optional<Tool> updatedTool = this.toolRepository.findById(tool.getId());

        Assertions.assertEquals(updateToolDTO.getName(), updatedTool.get().getName());
        Assertions.assertEquals(updateToolDTO.getTypeId(), updatedTool.get().getType().getId().toString());

    }
}
