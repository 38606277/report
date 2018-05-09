package root.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import root.report.interceptor.RestInterceptor;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class RestWebMvcConfigurationSupport extends WebMvcConfigurationSupport {
//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
//        FastJsonConfig fastConfig =  new FastJsonConfig();
//        fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastConverter.setFastJsonConfig(fastConfig);
//        converters.add(fastConverter);
//    }

    @Autowired
    private AppConstants appConstants;

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50*1024*1024);//50M
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/report/static/**").addResourceLocations("file:"+appConstants.getStaticReportPath()+ File.separator);
        registry.addResourceHandler("/report/dynamic/**").addResourceLocations("file:"+appConstants.getDynamicReportPath()+File.separator);
        registry.addResourceHandler("/ibas2/**").addResourceLocations("file:"+appConstants.getClientInstallFile()+File.separator);
        registry.addResourceHandler("/**").addResourceLocations("file:"+appConstants.getReport2()+File.separator);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/reportServer/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(36000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] EXCLUDE_URL = {"/reportServer/fonts/*","/reportServer/css/*","/reportServer/js/*",
                                "/reportServer/DBConnection/test","/reportServer/DBConnection/save",
                                "/reportServer/user/encodePwd","/reportServer/user/login"};
        registry.addInterceptor(new RestInterceptor())
                .addPathPatterns("/reportServer/**")
                .excludePathPatterns(EXCLUDE_URL);
    }
}
