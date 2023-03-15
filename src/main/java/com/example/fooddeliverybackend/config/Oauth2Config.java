//package com.example.fooddeliverybackend.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//public class Oauth2Config {
//
//    @Configuration
//    @EnableResourceServer
//    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//        @Autowired
//        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
//
//        @Autowired
//        private CustomLogoutSuccessHandler customLogoutSuccessHandler;
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//
//            http
//                    .exceptionHandling()
//                    .authenticationEntryPoint(customAuthenticationEntryPoint)
//                    .and()
//                    .logout()
//                    .logoutUrl("/oauth/logout")
//                    .logoutSuccessHandler(customLogoutSuccessHandler)
//                    .and()
//                    .csrf()
//                    .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
//                    .disable()
//                    .headers()
//                    .frameOptions().disable()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/hello/").permitAll()
//                    .antMatchers("/secure/**").authenticated();
//
//        }
//
//    }
//
//    @Configuration
//    @EnableAuthorizationServer
//    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements EnvironmentAware {
//
//        private static final String ENV_OAUTH = "authentication.oauth.";
//        private static final String PROP_CLIENTID = "clientid";
//        private static final String PROP_SECRET = "secret";
//        private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";
//
//        private RelaxedPropertyResolver propertyResolver;
//
//        @Autowired
//        private DataSource dataSource;
//
//        @Bean
//        public TokenStore tokenStore() {
//            return new JdbcTokenStore(dataSource);
//        }
//
//        @Autowired
//        @Qualifier("authenticationManagerBean")
//        private AuthenticationManager authenticationManager;
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//                throws Exception {
//            endpoints
//                    .tokenStore(tokenStore())
//                    .authenticationManager(authenticationManager);
//        }
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            clients
//                    .inMemory()
//                    .withClient(propertyResolver.getProperty(PROP_CLIENTID))
//                    .scopes("read", "write")
//                    .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .secret(propertyResolver.getProperty(PROP_SECRET))
//                    .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800));
//        }
//
//        @Override
//        public void setEnvironment(Environment environment) {
//            this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
//        }
//
//    }
//
//}