package ru.job4j.accident;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.DataConfig;
import ru.job4j.accident.config.SecurityConfig;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Objects;

public class User {
    private int id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class WebInit implements WebApplicationInitializer {
        @Override
        public void onStartup(ServletContext servletCxt) throws ServletException {
            AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
            ac.register(WebConfig.class, DataConfig.class, SecurityConfig.class);
            ac.refresh();
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            filter.setForceRequestEncoding(true);
            FilterRegistration.Dynamic encoding = servletCxt.addFilter("encoding", filter);
            encoding.addMappingForUrlPatterns(null, false, "/*");
            DispatcherServlet servlet = new DispatcherServlet(ac);
            ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
            registration.setLoadOnStartup(1);
            registration.addMapping("/");
        }

        public static class SecurityInit extends AbstractSecurityWebApplicationInitializer {
        }
    }
}
