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
import hexaware.sc.autoinsurance.web.model.ClaimDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClaimControllerTes extends AutoinsuranceApplicationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static long idClaimUsage;

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
    public void testAsaveClaim() throws Exception {
        var claim = createClaim();
        var findById = mockMvc.perform(
                post("/api/v1/claim")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claim)))
                .andExpect(status().isCreated()).andReturn();

        var savedClaimdto = objectMapper.readValue(findById.getResponse().getContentAsString(), ClaimDto.class);
        idClaimUsage = savedClaimdto.getClaimId();
    }

    @Test
    public void testBupdateClaim() throws Exception {
        var claim = createClaim();
        mockMvc.perform(
                put("/api/v1/claim/{id}", idClaimUsage).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claim)))
                .andExpect(status().isOk());

    }

    @Test
    public void testCgetClaimById() throws Exception {
        mockMvc.perform(
                get("/api/v1/claim/{id}", idClaimUsage).accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testDDeleteClaim() throws Exception {

        mockMvc.perform(delete("/api/v1/claim/{id}", idClaimUsage).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private ClaimDto createClaim() {
        ClaimDto claimDto = new ClaimDto();
        claimDto.setClaimSubjectId(Long.valueOf(3));
        claimDto.setClaimStatusId(1);
        claimDto.setDescription("Prueba unitTest1");
        return claimDto;
    }

}
