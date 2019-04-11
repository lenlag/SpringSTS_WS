package formation.afpa.fr.security;





import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity // securité activé
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

//	
//	@Autowired // A partir de Spring Boot 2.0, les login/Mdp sont sur-encryptés via le
//				// protocole BCrypt. Il faut donc déclarer un système de cryptage BCrypt.
//	private BCrypt crypt;
//
//	@Override // on dit que les requêtes nécessitent une authentification en Basic et, en cas
//				// d’erreur envoie sur le authenticationEntryPoint
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic()
//				.authenticationEntryPoint(authEntryPoint);
//	}
//
//	@Override // Là, on définit les user/mdp acceptés (user/user et admin/admin). On leur
//				// donne aussi un role, ce qui n’a pas d’utilité pour l’authentification (mais
//				// qui en aura pour les authorisations).
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password(crypt.encode("user")).roles("USER").and()
//				.withUser("admin").password(crypt.encode("admin")).roles("ADMIN");
//	}
	
	
	
	@Autowired //Ici, on déclare la Datasource, c.a.d la liaison vers la BDD.
	private DataSource dataSource;
	
	@Override //on dit qu’on utilise un système d’authentification jdbc via la dataSource
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().passwordEncoder(encoder()).dataSource(dataSource);
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}


}
