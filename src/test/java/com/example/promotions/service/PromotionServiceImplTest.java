package com.example.promotions.service;

import com.example.promotions.model.Promotion;
import com.example.promotions.repository.PromotionRepository;
import com.example.promotions.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.time.ZonedDateTime;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PromotionServiceImplTest {

    @Mock
    PromotionRepository promotionRepository;

    @InjectMocks
    PromotionServiceImpl service;

    @Test
    void getPromtion_returnsPromotionWhenFound() {
        Promotion p = new Promotion();
        p.setCouponCode("X");
        p.setStartDate(ZonedDateTime.now());
        p.setEndDate(ZonedDateTime.now().plusDays(1));
        p.setDiscountValue(new BigDecimal("5"));

        when(promotionRepository.findById(1L)).thenReturn(Optional.of(p));

        Promotion result = service.getPromtion(1L);
        assertSame(p, result);
        verify(promotionRepository, times(1)).findById(1L);
    }

    @Test
    void getPromtion_throwsWhenNotFound() {
        when(promotionRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getPromtion(2L));
        verify(promotionRepository, times(1)).findById(2L);
    }
}