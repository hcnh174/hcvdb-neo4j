package edu.hiro.hcv.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;

import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.morphia.SequenceRepository;
import edu.hiro.hcv.morphia.Ref;
import edu.hiro.hcv.morphia.RefRepository;
import edu.hiro.hcv.morphia.Suggestion;
import edu.hiro.hcv.sequences.SequenceService;
import edu.hiro.hcv.setup.SetupService;

@Controller
public class HcvDirect {

	@Resource(name="sequenceService")
	private SequenceService sequenceService; 

	@Resource(name="setupService")
	private SetupService setupService;
	
	@Autowired
	private RefRepository refRepository;
	
	@ExtDirectMethod
	public long multiply(long num) {
		return num * 8;
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
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<Sequence> getSequences(ExtDirectStoreReadRequest request) {
		System.out.println("request1: "+request.toString());
		List<Sequence> sequences=sequenceService.getSequences();
		return new ExtDirectStoreResponse<Sequence>(sequences.size(), sequences);
	}

	@ExtDirectMethod//(ExtDirectMethodType.STORE_READ)
	public List<Ref> getRefs() {//ExtDirectStoreReadRequest request) {
		List<Ref> refs=refRepository.find().asList();
		return refs;
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
