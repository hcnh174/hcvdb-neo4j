package edu.hiro.hcv.sequences;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.hiro.hcv.morphia.Sequence;

@Transactional(readOnly=false)
public interface SequenceService
{
	List<Sequence> getSequencesByGene(String gene);
}