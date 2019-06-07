package navin.web.docs.service;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
	DocsRepository docsRepo;

	public String markdownToHtmlConverter(String fileName, String filePath) throws IOException {

		PegDownProcessor pegdown = new PegDownProcessor(Extensions.ALL, Long.MAX_VALUE);
		DataInputStream dis = new DataInputStream(new FileInputStream(filePath + "/" + fileName + ".md"));

		byte[] markdownByte = null;
		try {
			markdownByte = new byte[dis.available()];
			dis.readFully(markdownByte);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			dis.close();
		}

		String markdownText = new String(markdownByte);
		return pegdown.markdownToHtml(markdownText);
	}
	
	public List<MenuDetails> getMenuDetails(){
		return docsRepo.getMenuDetails();
	}
}
