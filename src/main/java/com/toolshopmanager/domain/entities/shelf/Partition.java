package com.toolshopmanager.domain.entities.shelf;

import java.util.ArrayList;
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
}
