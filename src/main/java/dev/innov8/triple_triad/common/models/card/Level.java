package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.exceptions.CardTypeLevelMismatchException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.util.Objects;

// TODO: Review fields

@Embeddable
public class Level {

    @Column(columnDefinition = "check level > 0 AND level <= 10")
    private int level;

    @Enumerated
    private Type type;

    protected Level() {
        super();
    }

    public Level(int level) {
        setLevel(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level > 0 && level <= 5) {
            this.level = level;
            this.type = Type.MONSTER;
        } else if (level == 6 || level == 7) {
            this.level = level;
            this.type = Type.BOSS;
        } else if (level == 8 || level == 9) {
            this.level = level;
            this.type = Type.GF;
        } else if (level == 10) {
            this.level = level;
            this.type = Type.PLAYER;
        } else {
            throw new CardTypeLevelMismatchException();
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {

        boolean monsterTypeLevelMismatch = type.equals(Type.MONSTER) && level > 5;
        boolean bossTypeLevelMismatch = type.equals(Type.BOSS) && (level < 6 || level > 7);
        boolean gfTypeLevelMismatch = type.equals(Type.GF) && (level < 8 || level > 9);
        boolean playerTypeLevelMismatch = type.equals(Type.PLAYER) && level != 10;

        if (monsterTypeLevelMismatch || bossTypeLevelMismatch || gfTypeLevelMismatch || playerTypeLevelMismatch) {
            throw new CardTypeLevelMismatchException();
        }

        this.type = type;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level1 = (Level) o;
        return level == level1.level && type == level1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, type);
    }

    @Override
    public String toString() {
        return "Level{" +
                "level=" + level +
                ", type=" + type +
                '}';
    }
}
