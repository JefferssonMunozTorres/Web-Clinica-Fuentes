package com.edu.pe;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource data;
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates");
        return bean;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(data)
                .usersByUsernameQuery("select username , pass , estado from usuario where username = ?")
                .authoritiesByUsernameQuery("select u.username, t.nombre_perfil "
                		+ " from perfil t inner join usuario u  on u.id_perfil = t.id_perfil "
                		+ " where u.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/assets/**", "/vendor/**","/login")
        .permitAll() 
                .antMatchers("/paciente/**").hasAnyAuthority("Recepcion", "Administrador","Medico","Ventas")
                .antMatchers("/personal/**").hasAnyAuthority("Administrador")
                .antMatchers("/cita/atender/**").hasAnyAuthority("Medico")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/").defaultSuccessUrl("/home").permitAll().
                failureUrl("/?error=true").and().
                logout().permitAll().logoutSuccessUrl("/?logout");

    }
}
