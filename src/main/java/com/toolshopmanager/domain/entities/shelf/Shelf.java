package com.toolshopmanager.domain.entities.shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shelf {
    private String name;
    private List<Partition> partitions;

    private Shelf(String name, short patitionsQuantity) {
        this.name = name;
        this.partitions = new ArrayList<Partition>();

        for (short i = 1; i <= patitionsQuantity; i++) {
            this.partitions.add(new Partition(i));
        }
    }

    public static Shelf create(String name, short partitionsQuantity) {
        String validName = Shelf.validateName(name);
        return new Shelf(validName, partitionsQuantity);
    }

    private static String validateName(String name) {
        Pattern pattern = Pattern.compile( "^[a-zA-Z]{1,3}+$");
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
}
