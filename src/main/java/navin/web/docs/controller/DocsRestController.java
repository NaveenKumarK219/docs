package navin.web.docs.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import navin.web.docs.model.APIStatus;
import navin.web.docs.model.MenuDetails;
import navin.web.docs.service.DocsService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class DocsRestController {

	private static final Log log = LogFactory.getLog(DocsRestController.class);
	
	@Autowired
	DocsService docsService;
	
	@GetMapping("/menu")
	public APIStatus getMenu() {
		APIStatus apiStatus = APIStatus.initApiStatus("MENU API");
		apiStatus.setData(docsService.getMenuDetails());
		apiStatus.setStatus("Recieved Menu Data!");
		APIStatus.closeApiStatus(apiStatus);
		log.debug("Menu Details API:  " + apiStatus.toString());
		return apiStatus;
		
	}
	
	@GetMapping("/{fileName}")
	public APIStatus getDocOnTitle(@PathVariable("fileName")String fileName) throws IOException {
		APIStatus apiStatus = APIStatus.initApiStatus("DOC CONTENT API");
		apiStatus.setData(docsService.getDocContentOnFileName(fileName));
		apiStatus.setStatus("Retrieved Documnet Content");
		APIStatus.closeApiStatus(apiStatus);
		log.debug("Doc Content API:: fileName: "+fileName+ "API Status: " + apiStatus.toString());
		return apiStatus;
	}
}
