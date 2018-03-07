package br.com.ms.notification.manager.impl;

import br.com.ms.notification.entity.Template;
import br.com.ms.notification.manager.TemplateManager;
import br.com.ms.notification.repositories.TemplateRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class TemplateManagerImpl implements TemplateManager {
    private final Logger LOGGER = Logger.getLogger(TemplateManager.class);

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private JavaMailSender sender;

    private String emailFrom;

    public TemplateManagerImpl(@Value(value = "${mail.address.from}")String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public Template getTemplateById(Long id) {
        LOGGER.info("## [TemplateManager.getTemplateById]: finding template by id " + id);
        Template template = templateRepository.findOne(id);
        return template;
    }

    public List<Template> getTemplates() {
        LOGGER.info("## [TemplateManager.getTemplates]: finding all templates avaliable");
        List<Template> templates = (List<Template>) templateRepository.findAll();
        return templates;
    }

    public Long saveTemplate(Template template) {
        if (template.getIdTemplate() == null)
            LOGGER.info("## [NotificationController.saveTemplate]: saving new template key = " + template.getKeyTemplate());
        else
            LOGGER.info("## [NotificationController.saveTemplate]: updating template id = " + template.getIdTemplate());
        Template newTemplate = templateRepository.save(template);
        return newTemplate.getIdTemplate();
    }

    public void removeTemplate(Template template) {
        LOGGER.info("## [TemplateManager.removeTemplate]: removing template " + template.getKeyTemplate());
        templateRepository.delete(template);
    }

    public void sendEmail(Template template) throws MessagingException {
        try {
            LOGGER.info("## [TemplateManager.sendEmail]: Preparing message to be sent");
            MimeMessage message = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(template.getEmailTo());
            helper.setBcc(template.getBccField());
            if (template.getCcField() != null)
                helper.setCc(template.getCcField());
            helper.setSubject(template.getSubject());
            helper.setText(template.getContent());
            helper.setFrom(emailFrom);
            LOGGER.info("## [TemplateManager.sendEmail]: Sending email");
            sender.send(message);
            LOGGER.info("## [TemplateManager.sendEmail]: Email sent");
        } catch (Exception e) {
            LOGGER.error("## [TemplateManager.sendEmail]: Unable to send email: {}", e);
            throw e;
        }
    }

    public Template getTemplateByKey(String templateKey) {
        LOGGER.info("## [TemplateManager.getTemplateByKey]: Getting template by key ");
        Template template = templateRepository.findTemplateByKey(templateKey);
        return template;
    }
}
