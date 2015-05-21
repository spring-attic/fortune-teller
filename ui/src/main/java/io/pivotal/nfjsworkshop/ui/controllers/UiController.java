package io.pivotal.nfjsworkshop.ui.controllers;

import io.pivotal.nfjsworkshop.ui.services.fortunes.Fortune;
import io.pivotal.nfjsworkshop.ui.services.fortunes.FortuneService;
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
