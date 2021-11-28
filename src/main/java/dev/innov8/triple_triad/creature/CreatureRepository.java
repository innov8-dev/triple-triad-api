package dev.innov8.triple_triad.creature;

import dev.innov8.triple_triad.common.models.card.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Monster, String> {
}
