package edu.hiro.hcv.sequences;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

/**
 */
public interface SequenceRepository extends GraphRepository<Sequence>, NamedIndexRepository<Sequence> {
}
