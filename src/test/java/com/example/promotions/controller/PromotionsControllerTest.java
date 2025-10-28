package com.example.promotions.controller;

import com.example.promotions.model.Promotion;
import com.example.promotions.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PromotionsControllerTest {

    @Mock
    PromotionService promotionService;

    @InjectMocks
    PromotionsController controller;

    @Test
    void getPromotion_delegatesToService() {
        Promotion p = new Promotion();
        p.setCouponCode("CTRL");
        p.setStartDate(ZonedDateTime.now());
        p.setEndDate(ZonedDateTime.now().plusDays(1));
        p.setDiscountValue(new BigDecimal("1"));

        when(promotionService.getPromtion(10L)).thenReturn(p);

        ResponseEntity<Promotion> result = controller.getPromotion(10L);
        assertEquals(200, result.getStatusCode().value());
        assertSame(p, result.getBody());
        verify(promotionService, times(1)).getPromtion(10L);
    }
}