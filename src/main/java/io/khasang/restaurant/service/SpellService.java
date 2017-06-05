package io.khasang.restaurant.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

public interface SpellService {
    String checkword(String word) throws MalformedURLException;
}
