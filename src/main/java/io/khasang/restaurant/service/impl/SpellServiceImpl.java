package io.khasang.restaurant.service.impl;

import io.khasang.restaurant.service.SpellService;
import net.yandex.speller.services.spellservice.CheckTextRequest;
import net.yandex.speller.services.spellservice.SpellServiceSoap;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;


@org.springframework.stereotype.Service
@Qualifier("spellService")
public class SpellServiceImpl implements SpellService {
    private final static String ADDRESS = "http://speller.yandex.net/services/spellservice?WSDL";

    public String checkword(String word) throws MalformedURLException {
        URL url = new URL(ADDRESS);
        QName qName = new QName("http://speller.yandex.net/services/spellservice", "SpellService");
        Service service = Service.create(url, qName);

        SpellServiceSoap soap = service.getPort(SpellServiceSoap.class);
        CheckTextRequest checkTextRequest = new CheckTextRequest();
        checkTextRequest.setFormat("plain");
        checkTextRequest.setLang("en");
        checkTextRequest.setText(word);

        return soap.checkText(checkTextRequest).getSpellResult().getError().get(0).getS().toString();
    }
}
