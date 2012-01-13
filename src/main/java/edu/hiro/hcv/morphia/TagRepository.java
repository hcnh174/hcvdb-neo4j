package edu.hiro.hcv.morphia;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

public class TagRepository extends BasicDAO<Tag, String> {
	
    public TagRepository(Morphia morphia, Mongo mongo, String db) {
        super(mongo, morphia, db);
    }
}