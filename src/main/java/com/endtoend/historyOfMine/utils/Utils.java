package com.endtoend.historyOfMine.utils;

import java.text.ParseException;

public interface Utils<E, T> {

    E create(T form) throws ParseException;

}
