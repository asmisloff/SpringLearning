package ru.asmisloff.spring.ht07.entities.views;

public final class CustomerView extends CommonView {

    public interface IdName extends CommonFull{}

    public interface IdNameContactInfo extends IdName {}

    public interface FullInfo extends IdNameContactInfo, OrderView.IdCode {}

}
