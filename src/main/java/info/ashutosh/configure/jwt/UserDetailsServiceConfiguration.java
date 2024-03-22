package info.ashutosh.configure.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import info.ashutosh.repository.UserRepository;

@Configuration
public class UserDetailsServiceConfiguration {

	@Autowired
	UserRepository userRepository;

	@Bean
	UserDetailsService userDetailsService() {

//		return new UserDetailsService() {
//
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				Optional<MyUser> byEmail = userRepository.findByEmail(username);
//				return byEmail.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
//			}
//		};
		
		return username ->userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
		
	}

}
