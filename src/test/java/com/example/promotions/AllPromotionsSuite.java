package com.example.promotions;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.example.promotions")
public class AllPromotionsSuite {
    // Runs all tests in com.example.promotions and subpackages
}