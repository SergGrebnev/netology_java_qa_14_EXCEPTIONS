package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.errors.AlreadyExistsException;
import ru.netology.errors.NotFoundException;

public class ShopRepositoryTest {
    Product prod1 = new Product(10, "Молоко", 50);
    Product prod2 = new Product(20, "Хлеб", 40);
    Product prod3 = new Product(30, "Масло", 200);
    Product prod4 = new Product(40, "Шоколад", 100);

    ShopRepository shop = new ShopRepository();

    @BeforeEach //выполнять перед каждым тестом
    public void setup() {
        shop.add(prod1);
        shop.add(prod2);
        shop.add(prod3);
    }

    @Test
    public void successfulProductRemoval() { //успешное удаление существующего товара

        shop.removeById(20);

        Product[] expected = {prod1, prod3};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void deletingNonExistentProduct() { //попытка удаления несуществующего товара


        Assertions.assertThrows(NotFoundException.class, () -> {
            shop.removeById(25);
        });

    }

    @Test
    public void successfulProductAddition() { //успешное добавление товара

        shop.add(prod4);

        Product[] expected = {prod1, prod2, prod3, prod4};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void addingExistingProduct() { //попытка добавления уже существующего товара


        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shop.add(prod2);
        });

    }
}
