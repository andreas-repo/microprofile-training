package com.gepardec.training.microprofile.basic.faulttolerance;

import javax.enterprise.context.Dependent;

@Dependent
public class FallbackService {

    public String fails() {
        throw new IllegalStateException("I always fail, sorry :(");
    }
}
