package com.toolshopmanager.domain.entities.shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shelf {
    private String name;
    private List<Parttion> parttions;

    private Shelf(String name) {
        this.name = name;
        this.parttions = new ArrayList<Parttion>();
    }

    public static Shelf create(String name) {
        String validName = Shelf.validateName(name);
        return new Shelf(validName);
    }

    private static String validateName(String name) {
        Pattern pattern = Pattern.compile( "^[a-zA-Z]{1,3}+$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Name is invalid");
        }

        return matcher.toString();
    }
}
