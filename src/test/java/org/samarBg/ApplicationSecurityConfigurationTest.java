package org.samarBg;

import org.junit.jupiter.api.Test;
import org.samarBg.config.ApplicationSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(ApplicationSecurityConfiguration.class)
public class ApplicationSecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationManagerBuilder auth;

    @Autowired
    private HttpSecurity http;

    @Test
    public void testLoginForm() throws Exception {
        mockMvc.perform((RequestBuilder) httpBasic("user", "password"))
                .andExpect(authenticated().withUsername("user"));
    }

    @Test
    public void testInvalidLoginForm() throws Exception {
        mockMvc.perform((RequestBuilder) httpBasic("user", "wrongpassword"))
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl("**/login?error=**"));
    }

    @Test
    public void testAccessDeniedRedirect() throws Exception {
        mockMvc.perform(get("/admin").with(httpBasic("user", "password")))
                .andExpect(status().isForbidden())
                .andExpect(redirectedUrl("/login?error=AccessDenied+>>>it+is+only+available+to+administrators<<<"));
    }

    @Test
    public void testAuthenticationEntryPointRedirect() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isUnauthorized())
                .andExpect(redirectedUrl("/login?error=AccessDenied+>>>Need+to+LOGIN+IN+first+!<<<"));
    }
}
