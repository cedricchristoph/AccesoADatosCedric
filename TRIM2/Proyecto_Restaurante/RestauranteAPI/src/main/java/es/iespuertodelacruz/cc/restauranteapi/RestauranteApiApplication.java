package es.iespuertodelacruz.cc.restauranteapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.google.common.base.Predicates;

import es.iespuertodelacruz.cc.restauranteapi.security.FiltroJWT;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class RestauranteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApiApplication.class, args);
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    public void configure(WebSecurity webSecurity) throws Exception
	    {
	    	webSecurity
            .ignoring()
            .antMatchers(HttpMethod.POST, "/api/login")
            .antMatchers("/api/v1/**")
            .antMatchers("/swagger-ui/**")
            .antMatchers("/v3/api-docs/**",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**")
            //.antMatchers("/api/**")
            ;
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http 
	    	.addFilterBefore(new CustomCorsFilter(), WebAsyncManagerIntegrationFilter.class)
			.csrf().disable()
			.addFilterBefore(new FiltroJWT(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.requestMatchers(CorsUtils::isCorsRequest).permitAll()
			.antMatchers(HttpMethod.OPTIONS, "**").permitAll()				
			.antMatchers("/api/v3/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			;	    
		
	    }	
	    
	    @Bean
	    public Docket api() {
	        // @formatter:off
	        //Register the controllers to swagger
	        //Also it is configuring the Swagger Docket
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                // .apis(RequestHandlerSelectors.any())
	                .apis(Predicates.not(RequestHandlerSelectors.basePackage("es.iespuertodelacruz.cc.restauranteapi")))
	                // .paths(PathSelectors.any())
	                // .paths(PathSelectors.ant("/swagger2-demo"))
	                .build();
	        // @formatter:on
	    }
	 
	    @Bean
	    public LinkDiscoverers discoverers() {
	        List<LinkDiscoverer> plugins = new ArrayList<>();
	        plugins.add(new CollectionJsonLinkDiscoverer());
	        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	    }
	    
	    public void addResourceHandlers(ResourceHandlerRegistry registry)
	    {
	        //enabling swagger-ui part for visual documentation
	        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	    }
	}
}
