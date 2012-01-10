package com.xiaoxiang.ticket.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class SenderMail {
    private static final Logger logger = LoggerFactory.getLogger(SenderMail.class);

    private SimpleMailMessage message;
    private JavaMailSenderImpl sender;

    public void sendMail(String title, String text) {
        if (message == null) {
            logger.error("message,不能为空");
            return;
        }
        SimpleMailMessage smMessage = new SimpleMailMessage(message);
        smMessage.setSubject("火车票预定_" + title);
        //设置email内容,
        smMessage.setText(text);

        try {
            sender.send(smMessage);
            logger.info("邮件发送成功");
        } catch (MailException e) {
            logger.error("发送Email失败了....", e);
        }
    }


    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }

    public void setSender(JavaMailSenderImpl sender) {
        this.sender = sender;
    }
}
