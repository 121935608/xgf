package org.apache.shiro.service;

import java.io.File;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 邮件发送方法
 * 
 * @author y
 */
@Component
public class MailSenderService
{

    private static final Log logger = LogFactory.getLog(MailSenderService.class);
    @Autowired
    private JavaMailSender mailSender;// spring配置中定义
    @Autowired
    private VelocityEngine velocityEngine;// spring配置中定义
    @Autowired
    private SimpleMailMessage simpleMailMessage;// spring配置中定义
    private String to;
    private String subject;
    private String content;
    private String templateName;
    // 是否需要身份验证
    private boolean validate = false;

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public boolean isValidate()
    {
        return validate;
    }

    public void setValidate(boolean validate)
    {
        this.validate = validate;
    }

    public SimpleMailMessage getSimpleMailMessage()
    {
        return simpleMailMessage;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage)
    {
        this.simpleMailMessage = simpleMailMessage;
    }

    public JavaMailSender getMailSender()
    {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine()
    {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine)
    {
        this.velocityEngine = velocityEngine;
    }

    /**
     * 发送模板邮件
     * 
     * @throws CoreException
     * 
     */
    public void sendWithTemplate(Map<String, Object> model) throws Exception
    {
        try
        {
            this.sendHtml(model);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("Send Email exception" + e.getMessage());

        }

    }

    /**
     * 发送普通文本邮件
     * 
     */
    public void sendText(Map<String, Object> model)
    {
        mailSender = this.getMailSender();
        simpleMailMessage.setTo(this.getTo()); // 接收人
        simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
        simpleMailMessage.setSubject(this.getSubject());
        simpleMailMessage.setText(this.getContent());
        String result = null;
        result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), this.getTemplateName(), "UTF-8",
                model);
        simpleMailMessage.setText(result);
        // mailSender.send(simpleMailMessage);
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送普通Html邮件
     * 
     * @throws MessagingException
     * 
     */
    public void sendHtml(Map<String, Object> model) throws Exception
    {
        mailSender = this.getMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        String result = null;
        messageHelper.setTo(this.getTo());
        logger.info("email address: " + this.getTo());
        // messageHelper.setFrom(simpleMailMessage.getFrom(), Constants.EMAIL_NAME);
        messageHelper.setFrom(simpleMailMessage.getFrom());
        messageHelper.setSubject(this.getSubject());
        result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), this.getTemplateName(), "UTF-8",
                model);
        messageHelper.setText(result, true);
        mailSender.send(mimeMessage);
        logger.info("send email sucess !");
    }

    /**
     * 发送普通带一张图片的Html邮件
     * 
     */
    public void sendHtmlWithImage(String imagePath)
    {
        mailSender = this.getMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try
        {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(this.getTo());
            messageHelper.setFrom(simpleMailMessage.getFrom());
            messageHelper.setSubject(this.getSubject());
            messageHelper.setText(this.getContent(), true);
            // Content="<html><head></head><body><img src=\"cid:image\"/></body></html>";
            // 图片必须这样子：<img src='cid:image'/>
            FileSystemResource img = new FileSystemResource(new File(imagePath));
            messageHelper.addInline("image", img);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 发送普通带附件的Html邮件
     * 
     */
    public void sendHtmlWithAttachment(String filePath)
    {
        mailSender = this.getMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try
        {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(this.getTo());
            messageHelper.setFrom(simpleMailMessage.getFrom());
            messageHelper.setSubject(this.getSubject());
            messageHelper.setText(this.getContent(), true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            logger.debug("file.getFilename===" + file.getFilename());
            messageHelper.addAttachment(file.getFilename(), file);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    public boolean sendWithTemplate(String email, Map<String, Object> model) throws Exception
    {
        setTo(email);
        setSubject(model.get("subject").toString());
        setTemplateName("vm/" + model.get("template"));// 设置的邮件模板
        sendWithTemplate(model);
        return true;
    }

}
