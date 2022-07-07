package com.toolshopmanager.domain.entities.shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shelf {
    private final List<Partition> partitions;
    private String name;

    private Shelf(String name, short partitionsQuantity) {
        this.name = name;
        this.partitions = new ArrayList<Partition>();

        for (short i = 1; i <= partitionsQuantity; i++) {
            this.partitions.add(new Partition(i));
        }
    }

    public static Shelf create(String name, short partitionsQuantity) {
        String validName = Shelf.validateName(name);
        return new Shelf(validName, partitionsQuantity);
    }

    private static String validateName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{1,3}+$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Name is invalid");
        }

        return matcher.group();
    }

    public String getName() {
        return this.name;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public void addItemToPartition(short partitionCode, UUID itemId) {
        this.partitions.get(partitionCode).addItem(itemId);
    }

    public void addManyItemsToPartition(short partitionCode, UUID[] itemsId) {
        this.partitions.get(partitionCode).addManyItems(itemsId);
    }

    public List<PartitionItem> getPartitionItems(short partitionCode) {
        return this.partitions.get(partitionCode).getItems();
    }

    public void RemoveItemOfPartition(short partitionCode, UUID itemId) {
        this.partitions.get(partitionCode).removeItem(itemId);
    }
}
