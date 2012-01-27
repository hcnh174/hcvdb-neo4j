package edu.hiro.hcv.sequences;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import edu.hiro.hcv.neo4j.SequenceNode;

@Transactional(readOnly=false)
public interface SequenceService
{
	List<SequenceNode> getSequences();
	Page<SequenceNode> getSequences(Pageable pageable);
	//Page<Sequence> getSequences(Pageable pageable);
}
