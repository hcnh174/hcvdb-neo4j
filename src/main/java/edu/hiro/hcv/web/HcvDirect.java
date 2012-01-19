package edu.hiro.hcv.web;

import java.util.List;

import org.springframework.stereotype.Controller;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;

import edu.hiro.hcv.morphia.Sequence;

@Controller
public class HcvDirect {

  @ExtDirectMethod
  public long multiply(long num) {
    return num * 8;
  }
  

  @ExtDirectMethod(ExtDirectMethodType.STORE_READ)
  public List<Sequence> loadSequences() {
	  List<Sequence> sequences=Lists.newArrayList();
	  sequences.add(new Sequence("ABC123","acgtctcgtc"));
	  sequences.add(new Sequence("DEF456","tgctgctgctac"));
	  return sequences;
  }

  /*
  @ExtDirectMethod(ExtDirectMethodType.STORE_READ)
  public ExtDirectStoreResponse<Sequence> loadSequences(ExtDirectStoreReadRequest request) {
	  List<Sequence> sequences=Lists.newArrayList();
	  sequences.add(new Sequence("ABC123","acgtctcgtc"));
	  sequences.add(new Sequence("DEF456","tgctgctgctac"));
	  return new ExtDirectStoreResponse<Sequence>(sequences.size(), sequences);
  }
	*/
}
