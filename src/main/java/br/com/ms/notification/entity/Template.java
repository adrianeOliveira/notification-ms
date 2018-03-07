package br.com.ms.notification.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({ 
		@NamedQuery(name = "Template.findTemplateByKey",
				query = "from Template tp where tp.keyTemplate = :key")
})

@Entity
@Table(name="template")
public class Template implements Serializable {
	
	@Id
	@Column(name="id_template")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTemplate;
	
	@Column(name="key_template")
	private String keyTemplate;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="email_to")
	private String emailTo;
	
	@Column(name="content")
	private String content;
	
	@Column(name="cc_field")
	private String ccField;
	
	@Column(name="bcc_field")
	private String bccField;

	public Long getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getKeyTemplate() {
		return keyTemplate;
	}

	public void setKeyTemplate(String keyTemplate) {
		this.keyTemplate = keyTemplate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCcField() {
		return ccField;
	}

	public void setCcField(String ccField) {
		this.ccField = ccField;
	}

	public String getBccField() {
		return bccField;
	}

	public void setBccField(String bccField) {
		this.bccField = bccField;
	}

	@Override
	public boolean equals(Object obj) {
		Template temp = (Template) obj;
		if (temp.getIdTemplate() != null && temp.getIdTemplate().equals(this.getIdTemplate()))
			return true;
		return false;
	}
}
