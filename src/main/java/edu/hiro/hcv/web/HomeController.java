package edu.hiro.hcv.web;

//import java.util.logging.Logger;

//import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hiro.util.WebHelper;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//@Autowired SequenceService sequenceService;
	//@Autowired SequenceRepository sequenceRepository;
	//@Autowired Neo4jTemplate template;
	//@Autowired TagRepository tagRepository;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dflt(Model model) {
		return index(model);
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model) {
		
		/*
		Page<Sequence> page = sequenceRepository.findAll(new PageRequest(0, 10));
	    System.out.println(page.isFirstPage());
	    model.addAttribute("page", page);
	    model.addAttribute("sequences", page.getContent());
	    */
		model.addAttribute("title","Hiroshima Hepatitis C Virus Database");
		return "index";
	}
	
	@RequestMapping(value = "/tree.html", method = RequestMethod.GET)
	public String tree(Model model) {		
		return "tree";
	}
	
	@RequestMapping(value = "/test.html", method = RequestMethod.GET)
	public String test(Model model) {
		
		logger.info("Welcome home! from Thymeleaf");
		return "test";
	}
	
	@RequestMapping(value = "/direct.html", method = RequestMethod.GET)
	public String direct(Model model) {
		
		return "direct";
	}
	
	@RequestMapping(value = "/load.html", method = RequestMethod.POST)
	public String load(Model model) {
		
//		Tag malaria = template.save(new Tag("malaria"));
//		Tag tag = template.findOne(malaria.getId(), Tag.class);
//		logger.info(tag.toString());
//		System.out.println(tag.toString());

		//sequenceService.makeSomeSequences();
		return "index";
		//return "redirect:index.html";
	}
	
	@RequestMapping("/announcements.xml")
	public void announcementsRss(HttpServletResponse response)
	{
		response.setContentType(WebHelper.ContentType.XML);
		String rssFeed="http://groups.google.com/group/vardb-announce/feed/rss_v2_0_msgs.xml";
		int rssMax=10;
		WebHelper.write(response,WebHelper.readRss(rssFeed,rssMax));
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
	
	 /** Error page. */ 
    @RequestMapping("/error.html") 
    public String error(HttpServletRequest request, Model model) { 
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code")); 
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception"); 
        String errorMessage = null; 
        if (throwable != null) { 
            errorMessage = throwable.getMessage(); 
        } 
        model.addAttribute("errorMessage", errorMessage.toString()); 
        return "error"; 
    }

}