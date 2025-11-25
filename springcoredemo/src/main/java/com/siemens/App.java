package com.siemens;

import com.siemens.models.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*
          //IOC
        //Step1
        Resource resource = new ClassPathResource("user-config.xml");
        //Step2
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //Step3
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        //Step4
        //accessing the bean
        //actual ioc
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
        */

        ApplicationContext context = new ClassPathXmlApplicationContext("user-config.xml");
        //getting bean from container @component
        User user = (User) context.getBean("user");
        //DI
        user.setPassword("1234");
        user.setUserName("user");
        user.setEmail("email");
        System.out.println(user);

    }
}
