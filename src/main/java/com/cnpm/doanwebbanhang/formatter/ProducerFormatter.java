package com.cnpm.doanwebbanhang.formatter;

import com.cnpm.doanwebbanhang.model.Producer;
import com.cnpm.doanwebbanhang.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ProducerFormatter implements Formatter<Optional<Producer>> {
    private ProducerService producerService;

    @Autowired
    public ProducerFormatter(ProducerService producerService) {
        this.producerService = producerService;

    }

    public ProducerFormatter() {

    }

    @Override
    public Optional<Producer> parse(String text, Locale locale) throws ParseException {
        return producerService.findById((long) Integer.parseInt(text));
    }

    @Override
    public String print(Optional<Producer> object, Locale locale) {
        return null;
    }
}
