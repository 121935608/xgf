import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageTest
{
    private static MessageSource resources;

    public static void main(String[] args)
    {

        resources = new ClassPathXmlApplicationContext("classpath:spring/spring-mvc.xml");

        String message = resources.getMessage("user.not.exists", args, null);

        System.out.println(message);

        String message1 = resources.getMessage("msg.argument.required", new Object[] { "'联系方式'" }, null, Locale.CHINA);

        System.out.println(message1);

    }
}
