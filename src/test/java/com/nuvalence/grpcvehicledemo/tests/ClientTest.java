package com.nuvalence.grpcvehicledemo.tests;

import com.nuvalence.grpcvehicledemo.client.VehicleClient;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientTest {

    @MockBean
    private VehicleClient vehicleClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllMakes() throws Exception {
        Mockito
                .when(vehicleClient.allMakes())
                .thenReturn(
                        Mono.just(Arrays.asList(
                                    "ASTON MARTIN",
                                    "TESLA",
                                    "JAGUAR",
                                    "MASERATI",
                                    "LAND ROVER",
                                    "ROLLS ROYCE"))
                );

        mockMvc
                .perform(get("/vehicle/allMakes"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllMakesStream() throws Exception {
        Mockito
                .when(vehicleClient.allMakesStream(3))
                .thenReturn(Flux.just(
                                Arrays.asList(
                                        "ASTON MARTIN",
                                        "TESLA",
                                        "JAGUAR"
                                )
                ));

        String[] response = mockMvc
                                .perform(get("/vehicle/allMakesStream?batchSize={batchSize}",3))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse().getContentAsString().replace("data:[","").replace("]","")
                                .split(",");

        assertEquals(3, response.length);
    }

    @Test
    public void getModels() throws Exception {
        String[] fordModels = {
                "FORD Crown Victoria",
                "FORD Focus",
                "FORD Fusion",
                "FORD Mustang",
                "FORD Taurus",
                "FORD E-150",
                "FORD Edge",
                "FORD Escape",
                "FORD Expedition",
                "FORD Explorer",
                "FORD F-150",
                "FORD Flex",
                "FORD Ranger",
                "FORD Explorer Sport Trac" };
        String[] kiaModels = {
                "KIA Rio",
                "KIA Soul",
                "KIA Borrego",
                "KIA Forte",
                "KIA Rondo" };

        Mockito
                .when(vehicleClient.modelsForMakes(List.of("ford","kia")))
                .thenReturn(Mono.just(
                                Arrays.asList(
                                        Arrays.asList(kiaModels),
                                        Arrays.asList(fordModels)
                                )
                ));

        var response = mockMvc
                .perform(get("/vehicle/models?makes=ford, kia").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getAsyncResult();

        assertEquals(
                Arrays.asList(
                        Arrays.asList(kiaModels).toString(),
                        Arrays.asList(fordModels).toString()
                ).toString(),
                response.toString()
        );
    }

    @Test
    public void getModels_EmptyParams() throws Exception {
        mockMvc
                .perform(get("/vehicle/models?makes="))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllMakesStream400() throws Exception {
        mockMvc
                .perform(get("/vehicle/allMakesStream"))
                .andExpect(status().isBadRequest());

        mockMvc
                .perform(get("/vehicle/allMakesStream?batchSize="))
                .andExpect(status().isBadRequest());
    }

}
