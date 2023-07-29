package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.config.SecurityConfig;
import com.fastcampus.projectboard.service.ArticleService;
import com.fastcampus.projectboard.service.PaginationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("View 컨트롤러 - 인증")
@Import(SecurityConfig.class)
@WebMvcTest
public class AuthControllerTest {
    private final MockMvc mvc;

    @MockBean
    private ArticleService articleService;
    @MockBean private PaginationService paginationService;

    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 로그인 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenTryingToLogIn_thenReturnsLogInView() throws Exception {
        // given

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }
}
