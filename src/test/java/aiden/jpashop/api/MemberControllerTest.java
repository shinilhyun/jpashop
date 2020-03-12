package aiden.jpashop.api;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.domain.MemberRepository;
import aiden.jpashop.core.support.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입테스트_성공() throws Exception {

        MemberDto.JoinRequest request = MemberDto.JoinRequest.builder()
                .username("aidenshin@hanmail.net")
                .password("1234")
                .tel("01050351234")
                .address(new Address("seout", "gdklgj", "02342"))
                .build();

        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입테스트_이미가입된_username() throws Exception {

        //  Given
        String username = "aidenshin@hanmail.net";

        memberRepository.save(Member.builder()
                .username(username)
                .password("1234")
                .tel("01023024")
                .address(new Address("seoul", "sdf", " 0123"))
                .build());

        //  When
        MemberDto.JoinRequest request = MemberDto.JoinRequest.builder()
                .username(username)
                .password("1234")
                .tel("01050351234")
                .address(new Address("seout", "gdklgj", "02342"))
                .build();

        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}