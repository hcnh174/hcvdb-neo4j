package edu.hiro.hcv.web;

//import java.util.logging.Logger;

//import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hiro.hcv.sequences.Sequence;
import edu.hiro.hcv.sequences.SequenceRepository;
import edu.hiro.hcv.sequences.SequenceService;
import edu.hiro.hcv.tags.Tag;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired SequenceService sequenceService;
	@Autowired SequenceRepository sequenceRepository;
	@Autowired Neo4jTemplate template;
	//@Autowired TagRepository tagRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dflt(Model model) {
		return index(model);
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model) {
				
		Page<Sequence> page = sequenceRepository.findAll(new PageRequest(0, 10));
	    System.out.println(page.isFirstPage());
	    model.addAttribute("page", page);
	    model.addAttribute("sequences", page.getContent());
		return "index";
	}
	
	@RequestMapping(value = "/test.html", method = RequestMethod.GET)
	public String test(Model model) {
		
		logger.info("Welcome home! from Thymeleaf");
		System.out.println("Welcome home! from Thymeleaf");
		return "test";
	}
	
	@RequestMapping(value = "/load.html", method = RequestMethod.POST)
	public String load(Model model) {
		
//		Tag malaria = template.save(new Tag("malaria"));
//		Tag tag = template.findOne(malaria.getId(), Tag.class);
//		logger.info(tag.toString());
//		System.out.println(tag.toString());

		sequenceService.makeSomeSequences();
		return "index";
		//return "redirect:index.html";
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