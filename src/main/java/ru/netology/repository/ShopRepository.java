package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.errors.AlreadyExistsException;
import ru.netology.errors.NotFoundException;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        for (Product prod : products) {
            if (product.getId() == prod.getId()) {
                throw new AlreadyExistsException(
                        "Товар с id: " + product.getId() + " уже существует");
            }
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // удаление товара из репозитория по его ID
    public void removeById(int id) {
        if (findById(id) == null) { //проверка: есть ли товар с таким ID
            throw new NotFoundException("Товар с id: " + id + " не найден");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    //Поиск товара в репозитории по его ID
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}