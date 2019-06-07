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
@CrossOrigin("http://localhost:4200")
public class DocsRestController {

	private static final Log log = LogFactory.getLog(DocsRestController.class);
	private static final String FOLDER_PATH = System.getenv("HOME")+"/docs";
	
	@Autowired
	DocsService docsService;
	
	@GetMapping("/menu")
	public List<MenuDetails> getMenu() {
		
		return docsService.getMenuDetails();
		
	}
	
	@GetMapping("/welcome-page")
	public String home(HttpSession session) throws IOException{
		log.info("~~~~~~~~~~~Docs Welcome Page~~~~~~~~~~~");
		
		String docFileContent = docsService.markdownToHtmlConverter("welcomePage", session.getServletContext().getRealPath("WEB-INF/classes/static/init"));
		
		return docFileContent;

	}
	
	@GetMapping("/{title}")
	public String getDocOnTitle(@PathVariable("title")String title) throws IOException {
		log.info("~~~~~~~~~Get Doc : " + title);
		return docsService.markdownToHtmlConverter(title, FOLDER_PATH);
	}
}
