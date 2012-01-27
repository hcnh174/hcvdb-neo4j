package edu.hiro.hcv.sequences;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import edu.hiro.hcv.morphia.SequenceRepository;
import edu.hiro.hcv.neo4j.SequenceNode;
import edu.hiro.hcv.neo4j.SequenceNodeRepository;
import edu.hiro.util.Neo4jHelper;

@Service("sequenceService")
@Transactional
public class SequenceServiceImpl implements SequenceService
{
	@Resource(name="mongo")
	private Mongo mongo;
	
	@Resource(name="morphia")
	private Morphia morphia;
	
	@Resource(name="sequenceRepository")
	private SequenceRepository sequenceRepository;
	
	@Autowired
	private SequenceNodeRepository sequenceNodeRepository;

	public List<SequenceNode> getSequences()
	{
		return Neo4jHelper.asList(sequenceNodeRepository.findAll());
	}
	
	public Page<SequenceNode> getSequences(Pageable pageable)
	{
		return sequenceNodeRepository.findAll(pageable);
	}
	
	/*
	public Page<Sequence> getSequences(Pageable pageable)
	{
		int total=sequenceRepository.find().asKeyList().size(); // hack! - must do the same query but return all the results
		Query<Sequence> query=sequenceRepository.createQuery().limit(pageable.getPageSize()).offset(pageable.getOffset());
		List<Sequence> sequences = query.asList();
		Page<Sequence> page=new PageImpl<Sequence>(sequences,pageable,total);
		return page;
	}
	*/
//	
//
//	public List<Sequence> getSequences()
//	{
//		List<Sequence> sequences = sequenceRepository.find().asList();
//		List<Key<Sequence>> ids = sequenceRepository.find().asKeyList();
//		System.out.println("ids: "+ids);
//		return sequences;
//	}
//	
//	public Page<Sequence> getSequences(Pageable pageable)
//	{
//		int total=sequenceRepository.find().asKeyList().size(); // hack! - must do the same query but return all the results
//		Query<Sequence> query=sequenceRepository.createQuery().limit(pageable.getPageSize()).offset(pageable.getOffset());
//		List<Sequence> sequences = query.asList();
//		Page<Sequence> page=new PageImpl<Sequence>(sequences,pageable,total);
//		return page;
//	}
//
}