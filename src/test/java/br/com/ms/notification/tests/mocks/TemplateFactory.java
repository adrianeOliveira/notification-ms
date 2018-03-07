package br.com.ms.notification.tests.mocks;

import br.com.ms.notification.entity.Template;

import java.util.ArrayList;
import java.util.List;

public class TemplateFactory {

    public static final String KEY_TEMPLATE_MOCK = "KEY_TEMPLATE_MOCK";

    private static Template baseDataTemplate() {
        Template temp = new Template();
        temp.setBccField("drikkaro0drigues@gmail.com");
        temp.setContent("Email content for Unit test");
        temp.setEmailTo("rodrigues.adriane@outlook.com");
        return temp;
    }

    public static Template newTemplateMockHappyFlow() {
        Template template = baseDataTemplate();
        template.setKeyTemplate("KEY_NEW_TEMPLATE_MOCK");
        template.setSubject("Subject new template mock");
        return template;
    }

    public static Template newTemplateMockEmptySubject() {
        Template template = baseDataTemplate();
        template.setKeyTemplate("KEY_NEW_TEMPLATE_MOCK");
        template.setSubject("");
        return template;
    }

    public static Template newTemplateMockEmptyObject() {
        return new Template();
    }

    public static Template breakfastTemplate(){
        Template temp = baseDataTemplate();
        temp.setIdTemplate(new Long(1));
        temp.setKeyTemplate("KEY_BREAKFAST_TEMPLATE");
        temp.setSubject("Café da manhã");
        return temp;
    }

    public static Template dinnerTemplate(){
        Template temp = baseDataTemplate();
        temp.setIdTemplate(new Long(2));
        temp.setKeyTemplate("KEY_DINNER_TEMPLATE");
        temp.setSubject("Jantar");
        return temp;
    }

    public static Template lunchTemplate(){
        Template temp = baseDataTemplate();
        temp.setIdTemplate(new Long(3));
        temp.setKeyTemplate("KEY_LUNCH_TEMPLATE");
        temp.setSubject("Almoço");
        return temp;
    }

    public static List<Template> getListHappyFlow() {
        List<Template> templates = new ArrayList<>();
        templates.add(TemplateFactory.breakfastTemplate());
        templates.add(TemplateFactory.dinnerTemplate());
        templates.add(TemplateFactory.lunchTemplate());
        return templates;
    }

    public static String breakfasTemplateJson() {
        return "{\"idTemplate\": 0," +
                "\"keyTemplate\": \"KEY_BREAKFAST_TEMPLATE\"," +
                "\"subject\": \"Café da manhã!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"content\": \"Json content for Unit test\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\"}";
    }

    public static String lunchTemplateJson() {
        return "{\"idTemplate\": 0," +
                "\"keyTemplate\": \"KEY_LUNCH_TEMPLATE\"," +
                "\"subject\": \"Almoço!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"content\": \"Json content for Unit test\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\"}";
    }

    public static String dinnerTemplateJson() {
        return "{\"idTemplate\": 0," +
                "\"keyTemplate\": \"KEY_DINNER_TEMPLATE\"," +
                "\"subject\": \"Jantar!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"content\": \"Json content for Unit test\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\"}";
    }

    public static String TemplateJsonEmptyCCfield() {
        return "{\"idTemplate\": 0," +
                "\"keyTemplate\": \"KEY_DINNER_TEMPLATE\"," +
                "\"subject\": \"Jantar!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"content\": \"Json content for Unit test\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\", " +
                "\"ccField\": \"\",}";
    }

    public static String TemplateJsonWithoutKey() {
        return "{\"idTemplate\": 0," +
                "\"subject\": \"Jantar!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"content\": \"Json content for Unit test\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\"}";
    }

    public static String TemplateJsonWithoutContent() {
        return "{\"idTemplate\": 0," +
                "\"keyTemplate\": \"KEY_DINNER_TEMPLATE\"," +
                "\"subject\": \"Jantar!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\"}";
    }

    public static String TemplateJsonEmptyContent() {
        return "{\"idTemplate\": 0," +
                "\"keyTemplate\": \"KEY_DINNER_TEMPLATE\"," +
                "\"subject\": \"Jantar!\"," +
                "\"emailTo\": \"rodrigues.adriane@outlook.com\",\n" +
                "\"content\": \"\",\n" +
                "\"bccField\": \"drikkaro0drigues@gmail.com\"}";
    }

}
