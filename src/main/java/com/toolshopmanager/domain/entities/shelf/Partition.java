package com.toolshopmanager.domain.entities.shelf;

import java.util.*;

public class Partition {
    private short code;
    private List<PartitionItem> items;

    public Partition(short code) {
        this.code = code;
        this.items = new ArrayList<PartitionItem>();
    }

    public void addItem(UUID itemId) {
        this.items.add(new PartitionItem(itemId));
    }

    public void addManyItems(UUID[] itemsId) {
        this.items.addAll(Arrays.stream(itemsId).map(PartitionItem::new).toList());
    }

    public List<PartitionItem> getItems() {
        return this.items;
    }

    public void removeItem(UUID itemId) {
        Optional<PartitionItem> item = this.items.stream().filter(
            currentItem -> currentItem.getItemId().equals(itemId)
        ).findFirst();

        if(item.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.items.remove(item.get());
    }
}
