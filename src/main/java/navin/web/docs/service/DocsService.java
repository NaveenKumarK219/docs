package navin.web.docs.service;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import navin.web.docs.model.DocsData;
import navin.web.docs.repository.DataRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import navin.web.docs.model.MenuDetails;
import navin.web.docs.repository.DocsRepository;

@Service("docsService")
public class DocsService {

	private static final Log log = LogFactory.getLog(DocsService.class);

	@Autowired
	private DocsRepository docsRepo;

	@Autowired
	private DataRepository dataRepository;

	public String markdownToHtmlConverter(String fileName){

		PegDownProcessor pegdown = new PegDownProcessor(Extensions.ALL, Long.MAX_VALUE);
		DocsData data = dataRepository.findByFileName(fileName);
		String markdownText = data.getContent();
		return pegdown.markdownToHtml(markdownText);
	}
	
	public List<MenuDetails> getMenuDetails(){
		return docsRepo.getMenuDetails();
	}

	public String getDocContentOnFileName(String fileName){
		DocsData data = dataRepository.findByFileName(fileName);
		return data != null? data.getContent() : null;
	}
}
