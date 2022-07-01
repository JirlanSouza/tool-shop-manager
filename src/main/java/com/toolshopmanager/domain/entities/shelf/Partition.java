package com.toolshopmanager.domain.entities.shelf;

import java.util.*;

public class Partition {
    private short code;
    private List<UUID> itens;

    public Partition(short code) {
        this.code = code;
        this.itens = new ArrayList<UUID>();
    }

    public void addItem(UUID item) {
        this.itens.add(item);
    }

    public void addManyItems(UUID[] items) {
        this.itens.addAll(Arrays.stream(items).toList());
    }

    public List<UUID> getItems() {
        return this.itens;
    }

    public void removeItem(UUID itemId) {
        Optional<UUID> item = this.itens.stream().filter(it -> it.equals(itemId)).findFirst();

        if(item.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.itens.remove(item.get());
    }
}
