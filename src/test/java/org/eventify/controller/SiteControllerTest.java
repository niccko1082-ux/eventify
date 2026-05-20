package org.eventify.controller;

import org.eventify.Service.SiteService;
import org.eventify.models.Site;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SiteController.class)
public class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

    @Test
    void testGetAllSites() throws Exception {
        // Arrange: Preparamos los datos simulados (Mock)
        Site site1 = new Site(1L, "Concert Hall", "123 Main St", "New York");
        Site site2 = new Site(2L, "Stadium", "456 Broad St", "Los Angeles");
        Page<Site> sitePage = new PageImpl<>(Arrays.asList(site1, site2));
        when(siteService.getAll(any(Pageable.class))).thenReturn(sitePage);

        // Act & Assert: Ejecutamos la llamada HTTP y verificamos el resultado
        mockMvc.perform(get("/site/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(2))
                .andExpect(jsonPath("$.content[0].siteName").value("Concert Hall"));
    }

    @Test
    void testGetSiteById() throws Exception {
        // Arrange
        Site site = new Site(1L, "Concert Hall", "123 Main St", "New York");
        when(siteService.getByID(1L)).thenReturn(Optional.of(site));

        // Act & Assert
        mockMvc.perform(get("/site/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.siteName").value("Concert Hall"))
                .andExpect(jsonPath("$.city").value("New York"));
    }
}
