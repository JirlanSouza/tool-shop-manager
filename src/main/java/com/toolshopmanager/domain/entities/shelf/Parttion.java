package com.toolshopmanager.domain.entities.shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Parttion {
    private short code;
    private List<UUID> itens;

    public Parttion(short code) {
        this.code = code;
        this.itens = new ArrayList<UUID>();
    }

    public void addItem(UUID item) {
        this.itens.add(item);
    }
}
