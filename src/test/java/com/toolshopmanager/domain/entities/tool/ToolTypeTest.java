package com.toolshopmanager.domain.entities.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ToolTypeTest {
    @Test
    public void ShouldReturnNewToolTypeInstanceWIthNotNullId() {
        ToolType tool = ToolType.create("Elétrica");

        Assertions.assertInstanceOf(UUID.class, tool.getId());
    }

    @Test
    public void ShouldHaveEqualsIdToPassedID() {
        UUID id = UUID.randomUUID();
        ToolType tool = ToolType.create(id, "Elétrica");

        Assertions.assertEquals(id, tool.getId());
    }

    @Test
    public void ShouldThrowIllegalArgumentExceptionWhenNameIsLessThan3Character() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ToolType.create("ab"));
    }
}