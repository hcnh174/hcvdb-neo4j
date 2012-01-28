package edu.hiro.hcv.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.hiro.hcv.morphia.Suggestion;
import edu.hiro.hcv.neo4j.RefNode;
import edu.hiro.hcv.neo4j.RefNodeRepository;
import edu.hiro.hcv.neo4j.SequenceNode;
import edu.hiro.hcv.neo4j.TaxonNode;
import edu.hiro.hcv.neo4j.TaxonNodeRepository;
import edu.hiro.hcv.sequences.SequenceService;
import edu.hiro.hcv.setup.SetupService;
import edu.hiro.util.ExtDirectHelper;
import edu.hiro.util.StringHelper;

@Controller
public class HcvDirect {

	@Resource(name="sequenceService")
	private SequenceService sequenceService; 

	@Resource(name="setupService")
	private SetupService setupService;
	
	@Autowired
	private RefNodeRepository refNodeRepository;
	
	@Autowired
	private TaxonNodeRepository taxonNodeRepository;
	
	@ExtDirectMethod
	public long multiply(long num) {
		return num * 8;
	}
	
	@ExtDirectMethod
	public Map<String,String> getPage(String id) {
		Map<String,String> map=Maps.newHashMap();
		map.put("id",id);
		map.put("title","Sample page");
		map.put("text","Loaded from server with id: "+id);
		return map;
	}

	@ExtDirectMethod
	public void loadGenbankFile(String filename) {
		setupService.loadGenbankFile(filename);
	}
	
	@ExtDirectMethod
	public void getTaxids() {
		setupService.updateTaxa();
	}
	
	@ExtDirectMethod
	public void getRefids() {
		setupService.updateRefs();
	}
	
	@ExtDirectMethod
	public void clearDatabase() {
		setupService.clearDatabase();
	}
	
	/*
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<Sequence> getSequences(ExtDirectStoreReadRequest request) {
		System.out.println("request1: "+request.toString());
		List<Sequence> sequences=sequenceService.getSequences();
		return new ExtDirectStoreResponse<Sequence>(sequences.size(), sequences);
	}
	*/
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<SequenceNode> getSequences(ExtDirectStoreReadRequest request) {
		System.out.println("request: "+request.toString());
		List<SequenceNode> sequences=sequenceService.getSequences();
		for (SequenceNode sequence : sequences)
		{
			System.out.println(sequence.toString());
		}
		return new ExtDirectStoreResponse<SequenceNode>(sequences.size(), sequences);
	}
	
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<SequenceNode> getSequencesWithPaging(ExtDirectStoreReadRequest request) {
		System.out.println("request: "+request.toString());
		Pageable paging=ExtDirectHelper.getPageable(request);
		System.out.println("paging="+StringHelper.toString(paging));
		Page<SequenceNode> page=sequenceService.getSequences(paging);
		System.out.println("page="+StringHelper.toString(page));
		for (SequenceNode sequence : page.getContent())
		{
			System.out.println(sequence.toString());
		}
		return ExtDirectHelper.getResponse(page);
	}

	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public List<RefNode> getRefs() {
		List<RefNode> refs=Lists.newArrayList(IteratorUtil.asCollection(refNodeRepository.findAll()));
		return refs;
	}
	
	@ExtDirectMethod
	public void getTaxa() {
		System.out.println("Listing taxa");
    	for (TaxonNode node: taxonNodeRepository.findAll())
    	{
    		System.out.println("found node: "+node.toString());
    	}
    	System.out.println("done");
	}
	
//	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
//	public ExtDirectStoreResponse<Sequence> getSequences(ExtDirectStoreReadRequest request) {
//		System.out.println("request1: "+request.toString());
//		Pageable paging=ExtDirectHelper.getPageable(request);
//		Page<Sequence> page=sequenceService.getSequences(paging);
//		return ExtDirectHelper.getResponse(page);
//	}
//	
	@ExtDirectMethod(value = ExtDirectMethodType.STORE_READ)
	public List<Suggestion> getSuggestions(@RequestParam(value="query", required=false, defaultValue="") String query) {
		System.out.println("query: "+query);
		List<Suggestion> suggestions=Lists.newArrayList();
		suggestions.add(new Suggestion("hcv","HCV"));
		suggestions.add(new Suggestion("hbv","HBV"));
		return suggestions;
	}
}
