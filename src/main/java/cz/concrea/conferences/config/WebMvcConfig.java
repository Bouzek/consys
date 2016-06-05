package cz.concrea.conferences.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/admin", "/admin/");
		registry.addRedirectViewController("/admin/", "/admin/index");
		registry.addRedirectViewController("/admin/index", "/admin/conferences");
		
		registry.addViewController("/admin/login").setViewName("admin/login");
    }
}