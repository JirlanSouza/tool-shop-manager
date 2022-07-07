package com.toolshopmanager.domain.entities.shelf;

import java.util.UUID;

public class PartitionItem {
    private UUID itemId;
    private PartitionItemStatus status;

    public PartitionItem(UUID itemId) {
        this.itemId = itemId;
        this.status = PartitionItemStatus.AVAILABLE;
    }

    public UUID getItemId() {
        return itemId;
    }

    public PartitionItemStatus getStatus() {
        return status;
    }

    public void setStatus(PartitionItemStatus status) {
        this.status = status;
    }
}
