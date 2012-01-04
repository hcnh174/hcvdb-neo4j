package edu.hiro.hcv.sequences;

import java.util.Collection;

/**
 * 
 */
public interface SequenceService 
{
	Collection<Sequence> makeSomeSequences();
	Sequence sequence(String name, String seq);
	Sequence findSequenceNamed(String name);
}