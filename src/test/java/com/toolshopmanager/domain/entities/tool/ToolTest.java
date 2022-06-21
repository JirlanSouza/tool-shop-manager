package com.toolshopmanager.domain.entities.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ToolTest {
    static ToolType toolType;

    @BeforeEach
    public void setup() {
        ToolTest.toolType = new ToolType("Elétrica");
    }

    @Test
    public void ShouldReturnNewToolInstanceWIthNotNullId() {
        Tool tool = Tool.create("Lixadeira", toolType);

        Assertions.assertInstanceOf(UUID.class, tool.getId());
    }

    @Test
    public void ShouldThrowIllegalArgumentExceptionWhenNameIsLessThan3Character() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tool.create("ab", toolType));
    }
}