package ru.netology.errors;

//Исключение: товар с таким id уже существует
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String s) {
        super(s);
    }

}