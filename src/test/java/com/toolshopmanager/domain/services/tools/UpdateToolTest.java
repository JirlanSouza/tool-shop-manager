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

public class UpdateToolTest {
    private ToolRepository toolRepository;
    private ToolTypeRepository toolTypeRepository;

    @BeforeEach
    void setUp() {
        this.toolRepository = new ToolInMemoryRepository();
        this.toolTypeRepository = new TooTypeInMemoryRepository();
    }

    @Test
    void ExpectTheNameOfToolToBeTheSameAsTheOnePassed() {
        UpdateTool updateTool = new UpdateTool(this.toolRepository, this.toolTypeRepository);
        ToolType mechanicalToolType = new ToolType("Mecânica");
        ToolType electricalToolType = new ToolType("Mecânica");
        Tool tool = Tool.create("Alavanca", mechanicalToolType);
        UpdateToolDTO updateToolDTO = new UpdateToolDTO(tool.getId().toString(),"Lixadeira", electricalToolType.getId().toString());

        this.toolTypeRepository.save(mechanicalToolType);
        this.toolTypeRepository.save(electricalToolType);
        this.toolRepository.save(tool);
        updateTool.perform(updateToolDTO);

        Optional<Tool> updatedTool = this.toolRepository.findById(tool.getId());

        Assertions.assertEquals(updateToolDTO.getName(), updatedTool.get().getName());

    }
}
