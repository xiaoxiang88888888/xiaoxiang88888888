package com.xiaoxiang.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.jtester.testng.JTester;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

@SpringApplicationContext({"classpath:bean/spring-common.xml"})
public class KaptchaTest extends JTester {
    @SpringBeanByType
    private DefaultKaptcha captchaProducer;
    
    public void genTextTest() throws IOException {
        String text = captchaProducer.createText();
        System.out.println(text);
        BufferedImage bi = captchaProducer.createImage(text);
        System.out.println(bi);
        File file = new File("/home/admin/kaptcha.jpg");
        ImageIO.write(bi, "jpg", file);
        System.out.println(file.getAbsolutePath());
    }
}
