package com.toolshopmanager.domain.entities.shelf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShelfTest {
    @Test
    void ShouldThrowIllegalArgumentExceptionWhenNameContainsDigit() {
        String nameWithDigit = "A1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithDigit, (short) 1));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenNameContainsEspecialCharacter() {
        String nameWithEspecialCharacterOne = "A_";
        String nameWithEspecialCharacterTwo = "A-";
        String nameWithEspecialCharacterThree = "A@";
        String nameWithEspecialCharacterFour = "A#";
        String nameWithEspecialCharacterFive = "A$";
        String nameWithEspecialCharacterSix = "A%";
        String nameWithEspecialCharacterSeven = "A¨";
        String nameWithEspecialCharacterEight = "A&";

        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterOne, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterTwo, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterThree, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterFour, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterFive, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterSix, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterSeven, (short) 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterEight, (short) 1));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenNameExceedThreeCharacter() {
        String nameExceededThreeCharacter = "ABDB";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameExceededThreeCharacter, (short) 1));
    }

    @Test
    void ShouldCreateShelfWithValidName() {
        String validName = "A";
        Shelf shelf = Shelf.create(validName, (short) 1);

        Assertions.assertEquals(validName, shelf.getName());
    }

    @Test
    void ShouldCreateShelfWithQuantityPartitions() {
        short partitionsQuantity = 5;
        Shelf shelf = Shelf.create("A", partitionsQuantity);

        Assertions.assertEquals(partitionsQuantity, shelf.getPartitions().stream().count());
    }
}