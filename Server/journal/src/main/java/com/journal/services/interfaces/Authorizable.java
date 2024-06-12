package com.journal.services.interfaces;

import java.util.Map;

public interface Authorizable {

    String getUserPasswordByEmail(String email);

    Map<String, Object> getUserByEmail(String email);
}
