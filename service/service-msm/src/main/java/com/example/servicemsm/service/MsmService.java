package com.example.servicemsm.service;

import java.util.Map;

public interface MsmService {
    boolean send(String PhoneNumbers, Map<String, Object> param);
}
