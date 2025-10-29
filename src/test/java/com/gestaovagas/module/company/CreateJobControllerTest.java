package com.gestaovagas.module.company;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestaovagas.module.utils.TesteUtils;
import com.gestaovagas.modules.company.dto.CreateJobDTO;
import com.gestaovagas.modules.company.services.JobService;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {


    private MockMvc mvc;

    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {


        var createdJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TesteUtils.objectToJson(createdJobDTO))
                        .header("Authorization", TesteUtils.generateToken(""))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }


}
