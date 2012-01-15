package edu.hiro.hcv.neo4j;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface TagNodeRepository extends GraphRepository<TagNode> {

	/*
    Person findByUsername(String username);

    @Query("start project=node({0}) match project<--person return person")
    Set<Person> findByProject(Project project);

    @Query(
        "start person=node({0}) " +
        "match person-[:MEMBER_OF]->project<-[:MEMBER_OF]-collaborator " +
        "return collaborator")
    Set<Person> findCollaborators(Person person);
    */
}
