package com.toolshopmanager.infra.database.repository;


import com.toolshopmanager.domain.entities.tool.ToolType;
import com.toolshopmanager.domain.services.tools.ToolTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TooTypeInMemoryRepository implements ToolTypeRepository {
    private final List<ToolType> toolTypes;

    public TooTypeInMemoryRepository() {
        this.toolTypes = new ArrayList<ToolType>();
    }

    @Override
    public List<ToolType> findAll() {
        return toolTypes;
    }

    @Override
    public Optional<ToolType> findById(UUID id) {
        return toolTypes.stream().filter(tool -> tool.getId().equals(id)).findFirst();
    }

    @Override
    public void save(ToolType entity) {
        toolTypes.add(entity);
    }

    @Override
    public void update(UUID id, ToolType entity) {
        int toolTypeIndex = this.getIndex(id);
        toolTypes.set(toolTypeIndex, entity);
    }

    @Override
    public void delete(UUID id) {
        int toolTypeIndex = this.getIndex(id);
        toolTypes.remove(toolTypeIndex);
    }

    private int getIndex(UUID id) {
        var optionalTool = toolTypes.stream().filter(tool -> tool.getId().equals(id)).findFirst();
        if (optionalTool.isEmpty()) {
            throw new IllegalArgumentException("Tool is not exist");
        }

        return toolTypes.indexOf(optionalTool.get());
    }
}
