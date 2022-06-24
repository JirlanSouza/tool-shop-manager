package com.toolshopmanager.domain.entities.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ToolTest {
    private ToolType toolType;

    @BeforeEach
    public void setup() {
        this.toolType = ToolType.create("Elétrica");
    }

    @Test
    public void ShouldReturnNewToolInstanceWIthNotNullId() {
        Tool tool = Tool.create("Lixadeira", this.toolType);

        Assertions.assertInstanceOf(UUID.class, tool.getId());
    }

    @Test
    public void ShouldHaveEqualsIdToPassedID() {
        UUID id = UUID.randomUUID();
        Tool tool = Tool.create(id, "Lixadeira", this.toolType);

        Assertions.assertEquals(id, tool.getId());
    }

    @Test
    public void ShouldThrowIllegalArgumentExceptionWhenNameIsLessThan3Character() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tool.create("ab", toolType));
    }
}