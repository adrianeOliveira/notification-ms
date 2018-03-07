package br.com.ms.notification.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ms.notification.entity.Template;
import org.springframework.data.repository.query.Param;

public interface TemplateRepository extends CrudRepository<Template, Long> {
    Template findTemplateByKey(@Param("key")String key);
}
