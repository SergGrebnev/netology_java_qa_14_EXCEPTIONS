package ru.netology.errors;

//Исключение: товар с таким id не существует
public class NotFoundException extends RuntimeException {

    public NotFoundException(String s) {
        super(s);
    }

}
