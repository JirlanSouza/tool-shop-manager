package com.toolshopmanager.domain.entities.shelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
        System.out.println(this.itens.size());
    }

    public List<UUID> getItems() {
        return this.itens;
    }
}
