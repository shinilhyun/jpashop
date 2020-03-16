package aiden.jpashop.admin;

import aiden.jpashop.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemAdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void 아이템_BOOK_생성_테스트() throws Exception{

        ItemAdminDto.CreateBook create = ItemAdminDto.CreateBook.builder()
                .name("book test")
                .author("aiden")
                .isbn("1230")
                .price(10000)
                .stockQuantity(10)
                .build();

        mockMvc.perform(post("/api/v1/admin/book")
                .header(TestUtils.AUTH_HEADER, TestUtils.AUTH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}