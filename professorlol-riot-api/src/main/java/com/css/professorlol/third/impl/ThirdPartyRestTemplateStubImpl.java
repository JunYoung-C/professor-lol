package com.css.professorlol.third.impl;

import com.css.professorlol.third.ThirdPartyRestTemplate;
import lombok.extern.slf4j.Slf4j;

//TODO : TEST CODE 작성하기
@Slf4j
public class ThirdPartyRestTemplateStubImpl implements ThirdPartyRestTemplate {

    public ThirdPartyRestTemplateStubImpl() {
        log.info("ThirdPartyRestTemplate created.");
    }

    @Override
    public String getThirdPartyCode(final String encryptedSummonerId) {
        return null;
    }

}