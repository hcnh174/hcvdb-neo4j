package edu.hiro.hcv.sequences;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.google.common.collect.Sets;
import com.mongodb.Mongo;

import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.morphia.SequenceRepository;
import edu.hiro.hcv.setup.GenbankSequenceBuilder;
import edu.hiro.hcv.util.MathHelper;
import edu.hiro.hcv.util.StringHelper;

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
	
	public List<Sequence> getSequences()
	{
		List<Sequence> sequences = sequenceRepository.find().asList();
		List<Key<Sequence>> ids = sequenceRepository.find().asKeyList();
		System.out.println("ids: "+ids);
		return sequences;
	}
	
	public Page<Sequence> getSequences(Pageable pageable)
	{
		int total=sequenceRepository.find().asKeyList().size(); // hack! - must do the same query but return all the results
		Query<Sequence> query=sequenceRepository.createQuery().limit(pageable.getPageSize()).offset(pageable.getOffset());
		List<Sequence> sequences = query.asList();
		Page<Sequence> page=new PageImpl<Sequence>(sequences,pageable,total);
		return page;
	}

}