package navin.web.docs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import navin.web.docs.model.MenuDetails;
import navin.web.docs.service.DocsService;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DocsRestController {

	private static final Log log = LogFactory.getLog(DocsRestController.class);
	
	@Autowired
	DocsService docsService;
	
	@GetMapping("/menu")
	public List<MenuDetails> getMenu() {
		
		return docsService.getMenuDetails();
		
	}
	
	/*@GetMapping("/welcome-page")
	public String home(HttpSession session) throws IOException{
		log.info("~~~~~~~~~~~Docs Welcome Page~~~~~~~~~~~");
		
		String docFileContent = docsService.markdownToHtmlConverter("welcomePage");
		
		return docFileContent;

	}*/
	
	@GetMapping("/{fileName}")
	public String getDocOnTitle(@PathVariable("fileName")String fileName) throws IOException {
		log.info("~~~~~~~~~Get Doc : " + fileName);
		return docsService.getDocContentOnFileName(fileName);
	}
}
