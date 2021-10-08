package com.gepardec.training.microprofile;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("mpUri")
@ApplicationScoped
public class MicroprofileSpecificationUri {

    private static final String FAULT_TOLERANCE_URI = "https://download.eclipse.org/microprofile/microprofile-fault-tolerance-3.0/microprofile-fault-tolerance-spec-3.0.html";

    private static final String JWT_URI = "https://download.eclipse.org/microprofile/microprofile-jwt-auth-1.2/microprofile-jwt-auth-spec-1.2.html";

    private static final String CONFIG_URI = "https://download.eclipse.org/microprofile/microprofile-config-2.0/microprofile-config-spec-2.0.html";

    public String getFaultToleranceUri() {
        return FAULT_TOLERANCE_URI;
    }

    public String getJwtUri() {
        return JWT_URI;
    }

    public String getConfigUri() {
        return CONFIG_URI;
    }

}
