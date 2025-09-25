package cat.itacademy.s04.t02.n03.controller;

import cat.itacademy.s04.t02.n03.dto.FruitRequest;
import cat.itacademy.s04.t02.n03.dto.FruitResponse;
import cat.itacademy.s04.t02.n03.service.FruitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FruitController.class)
class FruitControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FruitService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createFruit_shouldReturn201() throws Exception {
        var request = new FruitRequest("orange", 7);
        var response = new FruitResponse("1", "orange", 7);

        when(service.create(any(FruitRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("orange"))
                .andExpect(jsonPath("$.quantityKilos").value(7));
    }
}
