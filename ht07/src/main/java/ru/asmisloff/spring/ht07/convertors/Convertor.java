package ru.asmisloff.spring.ht07.convertors;

public interface Convertor<TARGET, SOURCE>  {
    TARGET convert(SOURCE source);
}
