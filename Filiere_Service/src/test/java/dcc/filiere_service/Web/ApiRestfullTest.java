package dcc.filiere_service.Web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dcc.filiere_service.DTO.RequestFiliereDTO;
import dcc.filiere_service.DTO.ResponseFiliereDTO;
import dcc.filiere_service.Service.FiliereServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ApiRestfull.class)
@AutoConfigureMockMvc(addFilters = false)
class ApiRestfullTest {

    @MockBean
    private FiliereServiceImpl filiereService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    List<ResponseFiliereDTO> responseFiliereDTOS;

    @BeforeEach
    void setUp() {
        responseFiliereDTOS=List.of(
          new ResponseFiliereDTO(1,"DCC","DevOps et cloud"),
          new ResponseFiliereDTO(2,"SMI","Math Info")
        );
    }

    @Test
    void add() throws Exception {
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("DCC","DevOps et cloud");
        ResponseFiliereDTO response = new ResponseFiliereDTO(1,"DCC","DevOps et cloud");
        // action
        Mockito.when(filiereService.addFiliere(Mockito.any(RequestFiliereDTO.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/filieres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestFiliereDTO)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));

    }

    @Test
    void getAll() throws Exception {
        Mockito.when(filiereService.getAllFilieres()).thenReturn(responseFiliereDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/filieres"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseFiliereDTOS)));

    }

    @Test
    void getById() throws Exception{
        Integer id=1;
        ResponseFiliereDTO response = responseFiliereDTOS.get(0);

        Mockito.when(filiereService.getFiliereById(id)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/filieres/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));


    }

    @Test
    void delete() throws Exception{
        Integer id =1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/filieres/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update() throws Exception {
        Integer id=1;
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("DCC","DEVOPS");

        Mockito.when(filiereService.updateFiliere(Mockito.any(Integer.class),Mockito.any(RequestFiliereDTO.class))).thenReturn(responseFiliereDTOS.get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/filieres/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestFiliereDTO)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseFiliereDTOS.get(0))));

    }
}