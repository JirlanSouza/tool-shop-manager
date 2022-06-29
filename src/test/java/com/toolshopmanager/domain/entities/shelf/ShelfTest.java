package com.toolshopmanager.domain.entities.shelf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShelfTest {
    @Test
    void ShouldThrowIllegalArgumentExceptionWhenNameContainsDigit() {
        String nameWithDigit = "A1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithDigit));
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

        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterOne));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterTwo));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterThree));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterFour));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterFive));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterSix));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterSeven));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameWithEspecialCharacterEight));
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenNameExceedThreeCharacter() {
        String nameExceededThreeCharacter = "ABDB";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Shelf.create(nameExceededThreeCharacter));
    }

    @Test
    void ShouldCreateShelfWithValidName() {
        String validName = "A";
        Shelf shelf = Shelf.create(validName);
    }
}