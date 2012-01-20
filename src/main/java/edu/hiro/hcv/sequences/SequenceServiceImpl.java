package edu.hiro.hcv.sequences;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.util.MathHelper;

@Service("sequenceService")
@Transactional
public class SequenceServiceImpl implements SequenceService
{
	public List<Sequence> getSequences()
	{
		List<Sequence> sequences=Lists.newArrayList();
		for (int i=0;i<1000; i++)
		{			
			sequences.add(new Sequence("S"+MathHelper.randomInteger(1000),"acgtcttgctgtgctgctgctacctgtgctgctgctactgctactgctgctacctgctgctgctacgttgctgctgcttgctgctgctacaccacgtctcgtc"));
		}		
		return sequences;
	}
}