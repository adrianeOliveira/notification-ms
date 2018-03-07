package br.com.ms.notification.manager;

import br.com.ms.notification.entity.Template;

import javax.mail.MessagingException;
import java.util.List;

public interface TemplateManager {

    Template getTemplateById(Long id);

    List<Template> getTemplates();

    Long saveTemplate(Template template);

    void removeTemplate(Template template);

    void sendEmail(Template template) throws MessagingException;

    Template getTemplateByKey(String templateKey);
}
