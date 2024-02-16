package com.pensionerDisbursementMicroservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan("com.*")
public class MessageBundleConfig {
	
		@Bean
	   public MessageSource messageSource() {
	      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	      messageSource.setBasename("classpath:messages");
	      messageSource.setDefaultEncoding("UTF-8");
	      return messageSource;
	   }
		
		@Bean
		public LocalValidatorFactoryBean getValidator() {
		    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		    bean.setValidationMessageSource(messageSource());
		    return bean;
		}

}
