package edu.hiro.hcv.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;

import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.sequences.SequenceService;
import edu.hiro.hcv.util.ExtDirectHelper;

@Controller
public class HcvDirect {

	@Resource(name="sequenceService")
	private SequenceService sequenceService; 

	@ExtDirectMethod
	public long multiply(long num) {
		return num * 8;
	}

	@ExtDirectMethod
	public void loadSampleData() {
		sequenceService.loadSampleData(1000);
	}
	
//	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
//	public ExtDirectStoreResponse<Sequence> getSequences(ExtDirectStoreReadRequest request) {
//		System.out.println("request1: "+request.toString());
//		//Pageable paging=new PageRequest(request.getPage(),request.getLimit());
//		List<Sequence> sequences=sequenceService.getSequences();
//		return new ExtDirectStoreResponse<Sequence>(sequences.size(), sequences);
//	}

	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<Sequence> getSequences(ExtDirectStoreReadRequest request) {
		System.out.println("request1: "+request.toString());
		Pageable paging=ExtDirectHelper.getPageable(request);
		Page<Sequence> page=sequenceService.getSequences(paging);
		return ExtDirectHelper.getResponse(page);
	}
	
	@ExtDirectMethod(value = ExtDirectMethodType.STORE_READ)
	public List<Suggestion> getSuggestions(@RequestParam(value="query", required=false, defaultValue="") String query) {
		System.out.println("query: "+query);
		List<Suggestion> suggestions=Lists.newArrayList();
		suggestions.add(new Suggestion("hcv","HCV"));
		suggestions.add(new Suggestion("hbv","HBV"));
		return suggestions;
	}
	
	public static class Suggestion
	{
		protected String identifier;
		protected String keyword;
		protected String type="item";		
		
		public Suggestion(String identifier, String keyword)
		{
			this.identifier=identifier;
			this.keyword=keyword;			
		}
		
		public String getIdentifier() {
			return identifier;
		}

		public String getKeyword() {
			return keyword;
		}
		
		public String getType() {
			return keyword;
		}
	}
}
