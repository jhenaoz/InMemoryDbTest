package com.zilliant.pacts;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.junit.target.Target;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(PactRunner.class)
@Provider("sample_provider")
@PactFolder("pacts")
public class PactProvider {

    @TestTarget
    public final Target target = new HttpTarget(9005);

}
