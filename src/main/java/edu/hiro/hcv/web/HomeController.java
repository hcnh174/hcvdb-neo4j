package edu.hiro.hcv.web;

//import java.util.logging.Logger;

//import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hiro.hcv.sequences.SequenceRepository;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired SequenceRepository sequenceRepository;
	@Autowired Neo4jTemplate template;
	//@Autowired TagRepository tagRepository;
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String homeForm(Model model) {
		
		//CFileHelper()
		/*
		Tag malaria = template.save(new Tag("malaria"));
		Tag tag = template.findOne(malaria.getId(), Tag.class);
		logger.info(tag.toString());
		System.out.println(tag.toString());
		
		logger.info("Welcome home! from Thymeleaf");
		System.out.println("Welcome home! from Thymeleaf");

		model.addAttribute("messageInfo", new MessageForm());

		Page<Sequence> sequences = sequenceRepository.findAll(new PageRequest(0, 10));
	    System.out.println(sequences.isFirstPage());
		
	    model.addAttribute("sequences", sequences);
	    */
		
		return "index";
	}

	/*
	@RequestMapping(value = "/resourceNotFound")
	public String resourceNotFound(Model model) {
		return "resourceNotFound";
	}
	
	@RequestMapping(value = "/uncaughtException")
	public String uncaughtException(Model model) {
		return "uncaughtException";
	}
	*/
}