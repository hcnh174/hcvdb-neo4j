package edu.hiro.hcv.sequences;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.hiro.hcv.morphia.Sequence;

@Service("sequenceService")
@Transactional
public class SequenceServiceImpl implements SequenceService
{
	public List<Sequence> getSequencesByGene(String gene)
	{
		List<Sequence> sequences=Lists.newArrayList();
		return sequences;
	}
}