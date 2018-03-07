package br.com.ms.notification.controller;

import java.util.List;

import br.com.ms.notification.manager.TemplateManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ms.notification.entity.Template;

@RestController
@RequestMapping(value="/notification")
public class NotificationController {
	
	private final Logger LOGGER = Logger.getLogger(NotificationController.class);
	
	@Autowired
	private TemplateManager templateManager;

	/**
	 * get all templates avaliable
	 * @param
	 * */
	@GetMapping("/listTemplates")
	public ResponseEntity<List<Template>> listTemplates() {
		LOGGER.info("## [NotificationController.listTemplates]: initialized");
		List<Template> templates = templateManager.getTemplates();
		LOGGER.info("## [NotificationController.listTemplates]: finished");
		return new ResponseEntity<>(templates, HttpStatus.OK);
	}
	
	/**
	 * save/update template
	 * @param template
	 * */
	@PostMapping("/saveTemplate")
	public ResponseEntity<Template> saveTemplate(@RequestBody Template template) {
		LOGGER.info("## [NotificationController.saveTemplate]: initialized");
		
		if (template == null || verifyFields(template)) {
			LOGGER.error("## [NotificationController.saveTemplate]: template is null or empty");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		templateManager.saveTemplate(template);
		LOGGER.info("## [NotificationController.saveTemplate]: finished");
		return new ResponseEntity<>(template, HttpStatus.OK);
	}

	/**
	 * source for template by id
	 * @param id
	 * */
	@GetMapping("/getTemplate")
	public ResponseEntity<Template> getTemplateById(@RequestParam("id") Long id) {
		LOGGER.info("## [NotificationController.listTemplateById]: initialized");
		
		if (id == null){
			LOGGER.error("## [NotificationController.listTemplateById]: id cannot be null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("## [NotificationController.listTemplateById]: getting template by id");
		Template template = templateManager.getTemplateById(id);
		if (template == null){
			LOGGER.info("## [NotificationController.listTemplateById]: no template found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		LOGGER.info("## [NotificationController.listTemplateById]: finished");
		return new ResponseEntity<>(template, HttpStatus.OK);
	}
	
	/**
	 * remove template
	 * @param template
	 * */
	@PostMapping("/deleteTemplate")
	public HttpStatus deleteTemplate(@RequestBody Template template) {
		LOGGER.info("## [NotificationController.deleteTemplate]: initialized");
		if (template == null || template.getIdTemplate() == null){
			LOGGER.error("## [NotificationController.deleteTemplate]: template cannot be null or empty");
			return HttpStatus.BAD_REQUEST;
		}
		templateManager.removeTemplate(template);
		LOGGER.info("## [NotificationController.deleteTemplate]: finished");
		return HttpStatus.OK;
	}

	/**
	 * get message from SQS queue and send notification
	 * @param message
	 */
	@JmsListener(destination = "${aws.sqs.queue.name}", containerFactory = "jmsQueueListenerContainerFactory")
	public void receiveMessage(String message) {
		LOGGER.info("## [NotificationController.receiveMessage]: initialized");
		try {
			LOGGER.info("## [NotificationController.receiveMessage]: parsing message from queue");
			sendNotification(message);
			LOGGER.info("## [NotificationController.receiveMessage]: finished");
		}  catch (Exception e) {
			LOGGER.error("## [NotificationController.receiveMessage]: error while processing sqs message: ", e);
		}
	}

	/**
	 * send email notification by template key
	 * @param templateKey
	* */
	public boolean sendNotification (String templateKey) {
		try {
			LOGGER.info("## [NotificationController.sendNotification]: initialized");
			Template template = templateManager.getTemplateByKey(templateKey);
			templateManager.sendEmail(template);
			LOGGER.info("## [NotificationController.sendNotification]: finished");
			return true;
		} catch (Exception e) {
			LOGGER.error("## [NotificationController.sendNotification]: An exception occur: ", e);
			return false;
		}
	}

	/* method to verify main fields from template class*/
	private boolean verifyFields(Template template) {
		if (template.getContent() == null || template.getContent().isEmpty()
				|| template.getEmailTo() == null || template.getEmailTo().isEmpty()
				|| template.getSubject() == null || template.getSubject().isEmpty()
				|| template.getKeyTemplate() == null || template.getKeyTemplate().isEmpty())
			return true;
		
		return false;
	}
	
}
