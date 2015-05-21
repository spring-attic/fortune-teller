package io.spring.cloud.samples.fortuneteller.ui.controllers;

import io.spring.cloud.samples.fortuneteller.ui.services.fortunes.Fortune;
import io.spring.cloud.samples.fortuneteller.ui.services.fortunes.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UiController {

    @Autowired
    FortuneService service;

    @RequestMapping("/random")
    public Fortune randomFortune() {
        return service.randomFortune();
    }
}
