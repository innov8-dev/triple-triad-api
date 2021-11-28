package dev.innov8.triple_triad.common.util;

import dev.innov8.triple_triad.common.models.card.Creature;
import dev.innov8.triple_triad.common.models.card.Type;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LevelTypeConstraintValidator implements ConstraintValidator<LevelTypeConstraint, Creature> {

    @Override
    public boolean isValid(Creature creature, ConstraintValidatorContext constraintValidatorContext) {

        int level = creature.getLevel();
        Type type = creature.getType();

        if (level <= 0 || level > 10) return false;
        if (level <= 5 && !type.equals(Type.MONSTER)) return false;
        if ((level == 6 || level == 7) && !type.equals(Type.BOSS)) return false;
        if ((level == 8 || level == 9) && !type.equals(Type.GF)) return false;
        return level != 10 || type.equals(Type.PLAYER);

    }

}
