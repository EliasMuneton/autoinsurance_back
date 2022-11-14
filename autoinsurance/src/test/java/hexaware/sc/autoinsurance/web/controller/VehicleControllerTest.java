package hexaware.sc.autoinsurance.web.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hexaware.sc.autoinsurance.AutoinsuranceApplicationTest;
import hexaware.sc.autoinsurance.web.model.VehicleDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehicleControllerTest extends AutoinsuranceApplicationTest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    private static long idVehicleUsage;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                Long.valueOf(1), null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAsaveVehicle() throws Exception {
        var vehicle = createVehicle();
        var findById = mockMvc.perform(
                post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isCreated()).andReturn();

        var savedVehicledto = objectMapper.readValue(findById.getResponse().getContentAsString(), VehicleDto.class);
        idVehicleUsage = savedVehicledto.getVehicleId();
    }

    @Test
    public void testBupdateVehicle() throws Exception {
        var vehicle = createVehicle();
        mockMvc.perform(
                put("/api/v1/vehicle/{id}", idVehicleUsage).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isOk());

    }

    @Test
    public void testCgetVehicleById() throws Exception {
        mockMvc.perform(
                get("/api/v1/vehicle/{id}", idVehicleUsage).accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testDDeleteVehicle() throws Exception {

        mockMvc.perform(delete("/api/v1/vehicle/{id}", idVehicleUsage).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private VehicleDto createVehicle() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setUserId(Long.valueOf(1));
        vehicleDto.setModelId(Long.valueOf(1));
        vehicleDto.setColorId(Long.valueOf(1));
        vehicleDto.setVehicleYear(2020);
        vehicleDto.setLicencePlate("12-234fl");
        vehicleDto.setDescription("Prueba unitTest1");
        return vehicleDto;
    }
    
}
