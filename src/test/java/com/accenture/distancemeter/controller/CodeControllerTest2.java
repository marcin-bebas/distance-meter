package com.accenture.distancemeter.controller;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CodeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CodeControllerTest2 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CodeService codeService;

    @Test
    public void testExample() throws Exception {
        when(this.codeService.getCodeById(1L))
                .thenReturn(new Code(1L,"6651EH",51.88760463,5.597723367));
        this.mockMvc.perform(get("/api/v1/code/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"code\":\"6651EH\",\"latitude\":51.88760463,\"longitude\":5.597723367}"));
    }
}
