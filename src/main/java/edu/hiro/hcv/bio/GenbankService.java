package edu.hiro.hcv.bio;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import edu.hiro.hcv.util.MessageWriter;

@Transactional
public interface GenbankService
{
	void downloadGenbankEntries(Collection<String> ids, GenbankHelper.EntrezDatabase database, String filename, int batchsize, MessageWriter writer);
	//Collection<Taxon> getTaxa(List<Integer> ids, MessageWriter writer);
	//Map<Integer,Ref> getRefs(List<Integer> ids, MessageWriter writer);
}
