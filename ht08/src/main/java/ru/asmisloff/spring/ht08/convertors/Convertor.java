package ru.asmisloff.spring.ht08.convertors;

public interface Convertor<TARGET, SOURCE>  {
    TARGET convert(SOURCE source);
}
