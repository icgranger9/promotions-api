package com.example.promotions.controller;

import com.example.promotions.model.Promotion;
import com.example.promotions.model.PromotionStatus;
import com.example.promotions.model.RewardType;
import com.example.promotions.service.PromotionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PromotionsController.class)
class PromotionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromotionService promotionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Promotion samplePromotion() {
        Promotion p = new Promotion();
        p.setId(1L);
        p.setCouponCode("SAVE10");
        p.setStatus(PromotionStatus.ACTIVE);
        p.setRewardType(RewardType.PERCENT_OFF);
        p.setDiscountValue(new BigDecimal("10"));
        p.setStartDate(ZonedDateTime.now());
        p.setEndDate(ZonedDateTime.now().plusDays(7));
        return p;
    }

    @Test
    void getDefault_returnsOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Testing Promotions API"));
    }

    @Test
    void getPromotions_returnsOkWithArray() throws Exception {
        Promotion p = samplePromotion();
        when(promotionService.getPromtions()).thenReturn(new Promotion[] { p });

        mockMvc.perform(get("/promotions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getPromotions_returnsNoContentWhenEmpty() throws Exception {
        when(promotionService.getPromtions()).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/promotions"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPromotion_returnsOk_whenFound() throws Exception {
        Promotion p = samplePromotion();
        when(promotionService.getPromtion(1L)).thenReturn(p);

        mockMvc.perform(get("/promotion/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getPromotion_returnsNotFound_whenMissing() throws Exception {
        when(promotionService.getPromtion(2L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/promotion/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPromotion_returnsOk_whenCreated() throws Exception {
        Promotion incoming = samplePromotion();
        incoming.setId(null); // simulate new entity
        Promotion saved = samplePromotion();
        when(promotionService.createPromotion(any(Promotion.class))).thenReturn(saved);

        mockMvc.perform(post("/promotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incoming)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void createPromotion_returns500_onException() throws Exception {
        Promotion incoming = samplePromotion();
        incoming.setId(null);
        when(promotionService.createPromotion(any(Promotion.class))).thenThrow(new RuntimeException("boom"));

        mockMvc.perform(post("/promotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incoming)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updatePromotion_returnsOk_whenUpdated() throws Exception {
        Promotion incoming = samplePromotion();
        Promotion updated = samplePromotion();
        updated.setCouponCode("UPDATED");
        when(promotionService.updatePromotion(eq(1L), any(Promotion.class))).thenReturn(updated);

        mockMvc.perform(put("/promotion/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incoming)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void updatePromotion_returnsNotFound_whenMissing() throws Exception {
        Promotion incoming = samplePromotion();
        when(promotionService.updatePromotion(eq(99L), any(Promotion.class))).thenThrow(new NoSuchElementException());

        mockMvc.perform(put("/promotion/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incoming)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePromotion_returnsNoContent_whenDeleted() throws Exception {
        // no exception means success
        mockMvc.perform(delete("/promotion/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePromotion_returnsNotFound_whenMissing() throws Exception {
        doThrow(new NoSuchElementException()).when(promotionService).deletePromotion(42L);

        mockMvc.perform(delete("/promotion/42"))
                .andExpect(status().isNotFound());
    }
}