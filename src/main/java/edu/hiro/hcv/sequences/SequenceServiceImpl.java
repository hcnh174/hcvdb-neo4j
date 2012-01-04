package edu.hiro.hcv.sequences;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 */
@Service
@Transactional
public class SequenceServiceImpl implements SequenceService
{

    @Autowired
	private SequenceRepository repository;
    
    public Collection<Sequence> makeSomeSequences() {
    	
        ArrayList<Sequence> sequences = new ArrayList<Sequence>();

        // solar sequences
        sequences.add(sequence("Mercury", "acgtctcgtca"));
        sequences.add(sequence("Venus", "acgtctcgtca"));
        Sequence earth = sequence("Earth", "acgtctcgtca");
        sequences.add(earth);
        
        Sequence mars = sequence("Mars", "acgtctcgtca");
        repository.save(mars);
        
        sequences.add(mars);
        sequences.add(sequence("Jupiter", "acgtctcgtca"));
        sequences.add(sequence("Saturn", "acgtctcgtca"));
        sequences.add(sequence("Uranus", "acgtctcgtca"));
        sequences.add(sequence("Neptune", "acgtctcgtca"));

        // Norse sequences
        sequences.add(sequence("Alfheimr", "acgtctcgtca"));
        sequences.add(sequence("Midgard", "acgtctcgtca"));
        sequences.add(sequence("Muspellheim", "acgtctcgtca"));
        sequences.add(sequence("Asgard", "acgtctcgtca"));
        sequences.add(sequence("Hel", "acgtctcgtca"));
        
        repository.save(sequences);

        return sequences;
    }
 
    public Sequence sequence(String name, String seq) {
    	
    	Sequence sequence = findSequenceNamed(name);
        if (sequence == null) {
        	sequence = new Sequence(name, seq);
        	repository.save(sequence);
        }
    	return sequence;
    }
    
    public Sequence findSequenceNamed(String name) {
        return repository.findByPropertyValue("name", name);
    }
}