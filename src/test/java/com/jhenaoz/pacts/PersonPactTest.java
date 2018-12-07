package com.jhenaoz.pacts;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.junit.target.Target;
import com.jhenaoz.component.PersonDAO;
import com.jhenaoz.core.Person;
import com.jhenaoz.resources.PersonResource;
import io.dropwizard.testing.junit.DropwizardClientRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

/**
 * We are using the last stable version of pact V 3.0 for more info about the schema or specification go to
 * https://github.com/pact-foundation/pact-specification/tree/version-3
 * That define the json schema and new functionalities supported in the pact.json files.
 */
@RunWith(PactRunner.class)
@Provider("person_provider")
@PactFolder("pacts")
public class PersonPactTest {


    private static final PersonDAO personDAO = mock(PersonDAO.class);

    /**
     * We mock db responses to check contract against some situations.
     * We don't use real DB for that we have the component test that hit and in memory db to check that.
     */
    @ClassRule
    public static final DropwizardClientRule dropwizard = new DropwizardClientRule(new PersonResource(personDAO));

    /**
     * This is the Arrange phase for the contract test, we prepare the responses accoarding the input, for id 1 return
     * a resource, otherwise return 404 status code, this allow us to test different interactions.
     *
     * TODO: REFACTOR FOR USE @State instead of use @Before that affects all the test cases.
     * We set up the conditions for return certains values, this can be refactor for use @State for a specific request
     * insted of mock and interaction for all interactions.
     */
    @Before
    public void setup() {
        Person person = new Person();
        person.setFullName("Juan David");
        person.setJobTitle("Software Engineer");

        Person personToCreate = new Person();
        personToCreate.setFullName("Jhon Doe");
        personToCreate.setJobTitle("SDET");

        when(personDAO.findById(eq(1L))).thenReturn(person);
        when(personDAO.create(any())).thenReturn(personToCreate);
    }

    /**
     * The resources go up with a random port, so we need to get the port dinamically
     */
    @TestTarget
    public final Target target = new HttpTarget(dropwizard.baseUri().getPort());

}
