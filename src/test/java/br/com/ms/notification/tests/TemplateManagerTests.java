package br.com.ms.notification.tests;

import br.com.ms.notification.entity.Template;
import br.com.ms.notification.manager.TemplateManager;
import br.com.ms.notification.manager.impl.TemplateManagerImpl;
import br.com.ms.notification.repositories.TemplateRepository;
import br.com.ms.notification.tests.mocks.TemplateFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TemplateManagerTests {
    @InjectMocks
    private TemplateManager templateManager = new TemplateManagerImpl("notifier.MS@email.com");

    @Mock
    private TemplateRepository templateRepository;

    @Mock
    private JavaMailSender sender;

    @Test
    public void saveTemplateTeHappyFlow() {
        Template template = TemplateFactory.newTemplateMockHappyFlow();
        Template newTemplate = TemplateFactory.newTemplateMockHappyFlow();
        newTemplate.setIdTemplate(Long.valueOf(0));
        when(templateRepository.save(template)).thenReturn(newTemplate);
       Assert.assertSame(anyLong(), templateManager.saveTemplate(template));
    }

    @Test
    public void listTemplatesHappyFlow() {
        List<Template> templates = TemplateFactory.getListHappyFlow();
        Iterable<Template> iterable = templates;
        when(templateRepository.findAll()).thenReturn(iterable);
        Assert.assertSame(templates, templateManager.getTemplates());
    }

    @Test
    public void getTemplateByIdHappyFlow() {
        Template template = TemplateFactory.dinnerTemplate();
        when(templateRepository.findOne(anyLong())).thenReturn(template);
        Assert.assertSame(template, templateManager.getTemplateById(anyLong()));
    }

    @Test
    public void getTemplateByKeyHappyFlow() {
        Template template = TemplateFactory.breakfastTemplate();
        when(templateRepository.findTemplateByKey(anyString())).thenReturn(template);
        Assert.assertSame(template, templateManager.getTemplateByKey(anyString()));
    }

    @Test
    public void sendEmailHappyFlow() throws MessagingException {
        JavaMailSender localSender = new JavaMailSenderImpl();
        Template template = TemplateFactory.lunchTemplate();
        when(sender.createMimeMessage()).thenReturn(localSender.createMimeMessage());
        doNothing().when(sender).send(any(MimeMessage.class));
        templateManager.sendEmail(template);
    }
}
