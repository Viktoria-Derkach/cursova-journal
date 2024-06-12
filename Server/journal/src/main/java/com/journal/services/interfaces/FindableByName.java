package com.journal.services.interfaces;

public interface FindableByName<T> {
    T findByName(String name);
}
