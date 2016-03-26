package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mszarlinski on 2015-12-03.
 */
@FeignClient("fortunes")
public interface FortunesClient {

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    Fortune randomFortune();
}
