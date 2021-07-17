package am.itspace.restexample.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    private String basePath = "http://localhost:8080/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBooks() {
    }

    @Test
    void getById() {
    }

    @Test
    void create() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("title","girq1");
        objectNode.put("description","desc");
        objectNode.put("price","55");
        objectNode.put("authorName","poxos");

        ResultActions resultActions = mockMvc.perform(post(basePath + "/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString())).andExpect(status().isOk());
        resultActions.andExpect(jsonPath("id",notNullValue() ));
        resultActions.andExpect( jsonPath("title", equalTo("girq1")));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}