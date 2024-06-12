package com.journal.services.interfaces;

public interface FindableById<T> {
    T find(String id);
}
