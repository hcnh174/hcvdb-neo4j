package edu.hiro.hcv.bio;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import edu.hiro.hcv.util.MessageWriter;

@Transactional
public interface GenbankService
{
	enum EntrezDatabase{taxonomy,protein,nucleotide,pubmed,pmc,genome,gene,genomeprj;}

	final String GENBANK_SUFFIX=".gbk";
	final String GENPEPT_SUFFIX=".gpt";
	
	void downloadGenbankEntries(Collection<String> ids, EntrezDatabase database, String filename, int batchsize, MessageWriter writer);
	Collection<Taxon> getTaxa(List<Integer> ids, MessageWriter writer);
	Map<Integer,Ref> getRefs(List<Integer> ids, MessageWriter writer);
}
