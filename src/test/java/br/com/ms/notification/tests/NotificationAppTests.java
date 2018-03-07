package br.com.ms.notification.tests;

import static org.mockito.Mockito.*;
import br.com.ms.notification.manager.TemplateManager;
import br.com.ms.notification.tests.mocks.TemplateFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import br.com.ms.notification.controller.NotificationController;
import br.com.ms.notification.entity.Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NotificationAppTests {

	@InjectMocks
	private NotificationController controller = new NotificationController();
	

	@Mock
	private TemplateManager templateManager;
	
	@Test
	public void saveTemplateHappyFlowTest() {
		Template template = TemplateFactory.newTemplateMockHappyFlow();
		when(templateManager.saveTemplate(template)).thenReturn(Long.valueOf(1));
		Assert.assertEquals(new ResponseEntity<Template>(template, HttpStatus.OK) , controller.saveTemplate(template));
	}

	@Test
	public void saveTemplateEmptyFields() {
		Template template = TemplateFactory.newTemplateMockEmptySubject();
		Assert.assertEquals(new ResponseEntity<Template>(HttpStatus.BAD_REQUEST), controller.saveTemplate(template));

		template = TemplateFactory.newTemplateMockEmptyObject();
		Assert.assertEquals(new ResponseEntity<Template>(HttpStatus.BAD_REQUEST), controller.saveTemplate(template));

		template = null;
		Assert.assertEquals(new ResponseEntity<Template>(HttpStatus.BAD_REQUEST), controller.saveTemplate(template));
	}

	@Test
	public void listTemplateHappyFlow() {
		List<Template> templates = TemplateFactory.getListHappyFlow();
		//Iterable<Template> iterable = templates;
		when(templateManager.getTemplates()).thenReturn(templates);
		Assert.assertEquals( new ResponseEntity<>(templates, HttpStatus.OK), controller.listTemplates());
	}

	@Test
	public void getTemplateByIdHappyFlow() {
		Long id = Long.valueOf(1);
		Template template = TemplateFactory.breakfastTemplate();
		when(templateManager.getTemplateById(anyLong())).thenReturn(template);
		Assert.assertEquals(new ResponseEntity<>(template, HttpStatus.OK), controller.getTemplateById(id));
	}

	@Test
	public void deleteTemplateHappyFlow() {
		Template template = TemplateFactory.dinnerTemplate();
		doNothing().when(templateManager).removeTemplate(template);
		Assert.assertEquals(HttpStatus.OK, controller.deleteTemplate(template));
	}

	@Test
	public void receivedMessageHappyFlow() throws MessagingException {
		when(templateManager.getTemplateByKey(anyString())).thenReturn(TemplateFactory.breakfastTemplate());
		doNothing().when(templateManager).sendEmail(any(Template.class));
		controller.receiveMessage(anyString());
		Assert.assertTrue(controller.sendNotification(TemplateFactory.KEY_TEMPLATE_MOCK));
	}

}
