package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Element;
import dev.innov8.triple_triad.common.models.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardServiceTests {

    CardService sut;

    @BeforeEach
    public void setup() {
        sut = new CardService();
    }

    @Test
    public void test_isCardValid_returnsFalse_givenNullCard() {
        assertFalse(sut.isCardValid(null));
    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithNullOrEmptyName() {

        Card invalidCard_1 = new Card.CardBuilder()
                                       .setName(null)
                                       .setTopRank(1)
                                       .setRightRank(1)
                                       .setBottomRank(1)
                                       .setLeftRank(1)
                                       .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                       .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                       .setLevel(1)
                                       .setType(Type.MONSTER)
                                       .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("")
                                        .setTopRank(1)
                                        .setRightRank(1)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));

    }


    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidTopRank() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(0)
                                        .setRightRank(1)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(11)
                                        .setRightRank(1)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));

    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidRightRank() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(0)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(11)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));

    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidBottomRank() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(1)
                                        .setBottomRank(0)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(1)
                                        .setBottomRank(11)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));

    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidLeftRank() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(1)
                                        .setBottomRank(1)
                                        .setLeftRank(0)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(1)
                                        .setBottomRank(1)
                                        .setLeftRank(11)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));

    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidObverseImageUrl() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(0)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl(null)
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(11)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_3 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(11)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://fake-site-wont-work-test.com")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));
        assertFalse(sut.isCardValid(invalidCard_3));

    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidReverseImageUrl() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(0)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl(null)
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(11)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        Card invalidCard_3 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(11)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://fake-site-wont-work-test.com")
                                        .setLevel(1)
                                        .setType(Type.MONSTER)
                                        .build();

        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));
        assertFalse(sut.isCardValid(invalidCard_3));

    }

    @Test
    public void test_isCardValid_returnsFalse_givenCardWithInvalidLevel() {

        Card invalidCard_1 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(0)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(0)
                                        .build();

        Card invalidCard_2 = new Card.CardBuilder()
                                        .setName("Valid Name")
                                        .setTopRank(1)
                                        .setRightRank(0)
                                        .setBottomRank(1)
                                        .setLeftRank(1)
                                        .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                        .setLevel(11)
                                        .build();


        assertFalse(sut.isCardValid(invalidCard_1));
        assertFalse(sut.isCardValid(invalidCard_2));

    }

    @Test
    public void test_isCardValid_returnsTrue_givenValidCard() {

        Card validCard = new Card.CardBuilder()
                                    .setName("Valid Name")
                                    .setTopRank(1)
                                    .setRightRank(1)
                                    .setBottomRank(1)
                                    .setLeftRank(1)
                                    .setObverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                    .setReverseImageUrl("https://docs.oracle.com/javase/8/docs/api")
                                    .setLevel(1)
                                    .setType(Type.MONSTER)
                                    .build();

        assertNotNull(validCard.getId());
        assertNotEquals("", validCard.getId());
        assertEquals(Element.NONE, validCard.getElement());
        assertTrue(sut.isCardValid(validCard));

    }

}
