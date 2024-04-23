package no.ntnu.idatt1005.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketControllerTest {
   BasketController basketController = new BasketController();

    @Test
    void testGetRecipesInBasket() {
        Assert.assertEquals(0, basketController.getRecipesInBasket().size());
        basketController.getRecipesInBasket().size();
    }

}