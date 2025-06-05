package com.escuela.colegio.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ColegioContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("App name", "Colegio");
        map.put("App Description", "Colegio");
        map.put("App Version", "1.0.0");
        map.put("Contact Email", "info@colegio.com");
        map.put("Contact Mobile", "+34 123 456 789");
        builder.withDetail("colegio-info", map);
    }
}
