package com.example.prog4.service;

import com.example.prog4.model.exception.InternalServerErrorException;
import com.lowagie.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Component
public class PdfService {
  private static final String PDF_TEMPLATE_PREFIX = "/templates/pdf/";
  private final TemplateEngine templateEngine;
  private final ClassLoaderTemplateResolver templateResolver;

  public PdfService() {
    this.templateEngine = new TemplateEngine();
    this.templateResolver = new ClassLoaderTemplateResolver();
    prepareTemplate();
  }

  public byte[] generatePdfFromTemplate(String template) {
    return generatePdfFromTemplate(template, Map.of());
  }

  public byte[] generatePdfFromTemplate(String template, Map<String, Object> context) {
    var html = parseTemplateToString(template, context);

    var renderer = new ITextRenderer();
    renderer.setDocumentFromString(html);
    renderer.layout();
   
    var outputStream = new ByteArrayOutputStream();
    try {
      renderer.createPDF(outputStream);
    } catch (DocumentException e) {
      throw new InternalServerErrorException("Pdf generation ended with exception :" + e);
    }
    return outputStream.toByteArray();
  }

  private String parseTemplateToString(String template, Map<String, Object> ctx) {
    return templateEngine.process(template, toTemplateContext(ctx));
  }

  private void prepareTemplate() {
    configureHtmlResolver();
    templateResolver.setPrefix(PDF_TEMPLATE_PREFIX);
    templateEngine.setTemplateResolver(templateResolver);
  }

  private void configureHtmlResolver() {
    templateResolver.setSuffix(".html");
    templateResolver.setCharacterEncoding("UTF-8");
    templateResolver.setTemplateMode(HTML);
    templateResolver.setOrder(1);
  }

  private Context toTemplateContext(Map<String, Object> context) {
    var templateContext = new Context();
    for (var entry : context.entrySet()) {
      templateContext.setVariable(entry.getKey(), entry.getValue());
    }
    return templateContext;
  }
}
