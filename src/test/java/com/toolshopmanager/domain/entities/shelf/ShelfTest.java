package com.toolshopmanager.domain.entities.shelf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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

        Assertions.assertEquals(partitionsQuantity, shelf.getPartitions().size());
    }

    @Test
    void ShouldAddItemInShelfPartition() {
        short partitionsQuantity = 5;
        Shelf shelf = Shelf.create("A", partitionsQuantity);
        short partitionCode = 1;
        UUID itemId = UUID.randomUUID();

        shelf.addItemToPartition(partitionCode, itemId);

        UUID addedItemId = shelf.getPartitionItems(partitionCode).get(0).getItemId();
        Assertions.assertEquals(itemId, addedItemId);
    }

    @Test
    void ShouldAddManyItemsInShelfPartition() {
        short partitionsQuantity = 5;
        Shelf shelf = Shelf.create("A", partitionsQuantity);
        short partitionCode = 1;
        UUID[] itemsId = new UUID[5];

        for (int i = 0; i < itemsId.length; i++) {
            itemsId[i] = UUID.randomUUID();
        }

        shelf.addManyItemsToPartition(partitionCode, itemsId);

        for (int i = 0; i < itemsId.length; i++) {
            UUID itemId = shelf.getPartitionItems(partitionCode).get(i).getItemId();
            Assertions.assertEquals(itemsId[i], itemId);
        }
    }

    @Test
    void ShouldRemoveItemOfShelfPartition() {
        short partitionsQuantity = 5;
        Shelf shelf = Shelf.create("A", partitionsQuantity);
        short partitionCode = 1;
        UUID itemId = UUID.randomUUID();
        shelf.addItemToPartition(partitionCode, itemId);

        shelf.RemoveItemOfPartition(partitionCode, itemId);

        Assertions.assertEquals(0, shelf.getPartitionItems(partitionCode).size());
    }
}