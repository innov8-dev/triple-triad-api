package dev.innov8.triple_triad.common.models.card;

import static org.junit.jupiter.api.Assertions.*;

import dev.innov8.triple_triad.common.exceptions.CardTypeLevelMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class LevelModelTests {

    Level sut;

    @BeforeEach
    public void setup() {
        sut = new Level();
    }

    @Test
    public void test_setLevel_correctlySetsTheProperType_givenValidValue() {

        sut.setLevel(1);
        assertEquals(Type.MONSTER, sut.getType());

        sut.setLevel(2);
        assertEquals(Type.MONSTER, sut.getType());

        sut.setLevel(3);
        assertEquals(Type.MONSTER, sut.getType());

        sut.setLevel(4);
        assertEquals(Type.MONSTER, sut.getType());

        sut.setLevel(5);
        assertEquals(Type.MONSTER, sut.getType());

        sut.setLevel(6);
        assertEquals(Type.BOSS, sut.getType());

        sut.setLevel(7);
        assertEquals(Type.BOSS, sut.getType());

        sut.setLevel(8);
        assertEquals(Type.GF, sut.getType());

        sut.setLevel(9);
        assertEquals(Type.GF, sut.getType());

        sut.setLevel(10);
        assertEquals(Type.PLAYER, sut.getType());

    }

    @Test
    public void test_setLevel_throwsCardTypeLevelMismatchException_givenInvalidValue() {
        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(0);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            int randomNegativeVal = (new Random().nextInt(Integer.MAX_VALUE - 11) + 11) * -1;
            sut.setLevel(randomNegativeVal);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            int randomPositiveVal = new Random().nextInt(Integer.MAX_VALUE - 11) + 11;
            sut.setLevel(randomPositiveVal);
        });
    }

    @Test
    public void test_setType_setsType_givenTypeThatCompliesWithLevel() {

        sut.setLevel(1);
        sut.setType(Type.MONSTER);

        sut.setLevel(2);
        sut.setType(Type.MONSTER);

        sut.setLevel(3);
        sut.setType(Type.MONSTER);

        sut.setLevel(4);
        sut.setType(Type.MONSTER);

        sut.setLevel(5);
        sut.setType(Type.MONSTER);

        sut.setLevel(6);
        sut.setType(Type.BOSS);

        sut.setLevel(7);
        sut.setType(Type.BOSS);

        sut.setLevel(8);
        sut.setType(Type.GF);

        sut.setLevel(9);
        sut.setType(Type.GF);

        sut.setLevel(10);
        sut.setType(Type.PLAYER);

    }

    @Test
    public void test_setType_throwsCardTypeLevelMismatchException_givenTypeThatDoesNotComplyWithLevel() {

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(1);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(2);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(3);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(4);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(5);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(1);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(2);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(3);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(4);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(5);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(1);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(2);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(3);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(4);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(5);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(6);
            sut.setType(Type.MONSTER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(7);
            sut.setType(Type.MONSTER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(6);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(7);
            sut.setType(Type.GF);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(6);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(7);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(8);
            sut.setType(Type.MONSTER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(9);
            sut.setType(Type.MONSTER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(8);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(9);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(8);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(9);
            sut.setType(Type.PLAYER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(10);
            sut.setType(Type.MONSTER);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(10);
            sut.setType(Type.BOSS);
        });

        assertThrows(CardTypeLevelMismatchException.class, () -> {
            sut.setLevel(10);
            sut.setType(Type.GF);
        });

    }

}
