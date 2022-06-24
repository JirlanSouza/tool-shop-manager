package com.toolshopmanager.infra.database.repository;

import com.toolshopmanager.domain.entities.tool.Tool;
import com.toolshopmanager.domain.services.tools.ToolRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ToolInMemoryRepository implements ToolRepository {
    private final List<Tool> tools;

    public ToolInMemoryRepository() {
        this.tools = new ArrayList<Tool>();
    }

    @Override
    public List<Tool> findAll() {
        return tools;
    }

    @Override
    public Optional<Tool> findById(UUID id) {
        return tools.stream().filter(tool -> tool.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Tool entity) {
        tools.add(entity);
    }

    @Override
    public void update(UUID id, Tool entity) {
        int toolIndex = this.getIndex(id);
        tools.set(toolIndex, entity);
    }

    @Override
    public void delete(UUID id) {
        int toolIndex = this.getIndex(id);
        tools.remove(toolIndex);
    }

    private int getIndex(UUID id) {
        var optionalTool = tools.stream().filter(tool -> tool.getId().equals(id)).findFirst();

        if (optionalTool.isEmpty()) {
            throw new IllegalArgumentException("Tool is not exist");
        }

        return tools.indexOf(optionalTool.get());
    }
}
