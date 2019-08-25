package com.css.professorlol.config.resttemplate;

import com.css.professorlol.config.RiotRestTemplateBuilder;
import com.css.professorlol.config.properties.XRiotTokenProperties;
import com.css.professorlol.summoner.SummonerRestTemplate;
import com.css.professorlol.summoner.impl.SummonerRestTemplateImpl;
import com.css.professorlol.summoner.impl.SummonerRestTemplateStubImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;

@Configuration
@Slf4j
public class SummonerRestTemplateConfig {

    @Profile("dev")
    @RequiredArgsConstructor
    @Configuration
    public static class SummonerRestTemplateConfiguration {

        private static final Duration ONE_SEC = Duration.ofMillis(1000);
        private static final Duration TWO_SEC = Duration.ofMillis(2000);

        private final XRiotTokenProperties xRiotTokenProperties;
        private final RestTemplateBuilder restTemplateBuilder;

        @Bean
        public SummonerRestTemplate summonerRestTemplate() {
            log.info("Summoner RestTemplate Bean created");
            RestTemplateBuilder restTemplateBuilder = RiotRestTemplateBuilder.get(this.restTemplateBuilder, this.xRiotTokenProperties);
            return new SummonerRestTemplateImpl(restTemplateBuilder.setConnectTimeout(ONE_SEC)
                    .setReadTimeout(TWO_SEC));
        }

    }

    @Profile("stub")
    @RequiredArgsConstructor
    @Configuration
    public static class SummonerRestTemplateStubConfiguration {

        @Bean
        public SummonerRestTemplate summonerRestTemplate() {
            log.info("Summoner RestTemplate Stub Bean created");
            return new SummonerRestTemplateStubImpl();
        }

    }

}