package MASTER_SISPRING_AP22;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MaConfig {
	
	@Configuration

	@EnableWebSecurity

	public class MaConfiguration extends WebSecurityConfigurerAdapter {
		protected void configureHttp(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers("/","/portail").permitAll()
			.anyRequest().authenticated().and()
			.formLogin().loginPage("/login").permitAll().and()
			.logout().permitAll();
		}

	@Autowired

	private DataSource dataSource;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)

		.passwordEncoder(new BCryptPasswordEncoder())

		.usersByUsernameQuery("SELECT nom, mdp, actif from comptes where nom=?")

		.authoritiesByUsernameQuery("SELECT c.nom, r.role FROM roles r, comptes c " +

		"WHERE c.nom = ? AND c.id = r.compte_id");
	}
	
		
	@Override

	protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
	//Utiliser une authentification JDBC
	//L’URL « /admin » est accessible uniquement pour les utilisateurs ayant le rôle « ADMIN » ;
	//L’URL « /portail » est accessible uniquement pour les utilisateurs ayant le rôle « USER » ;


	.antMatchers("/admin").permitAll()

	.anyRequest().authenticated().and()

	.formLogin().loginPage("/login").permitAll().and()

	.logout().permitAll();

	}


	}
}
