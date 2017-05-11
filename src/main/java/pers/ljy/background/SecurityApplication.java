package pers.ljy.background;

import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"service","mapper","web"})//扫描指定包及下面子包 
@SpringBootApplication 
public class SecurityApplication {

	public static void main(String[] args) {
		//tomcat8.5 需要这么设置
		if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
		SpringApplication.run(SecurityApplication.class, args);
	}
}

