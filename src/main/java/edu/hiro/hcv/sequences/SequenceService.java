package edu.hiro.hcv.sequences;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import edu.hiro.hcv.morphia.Sequence;

@Transactional(readOnly=false)
public interface SequenceService
{
	List<Sequence> getSequences();
	Page<Sequence> getSequences(Pageable pageable);
	void loadGenbankFile(String filename);
	
	Set<Integer> getTaxids();
	Set<Integer> getRefids();
	
	void loadSampleData(int num);
}
