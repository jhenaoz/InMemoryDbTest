package com.jhenaoz;

import com.jhenaoz.core.Person;
import com.jhenaoz.component.PersonDAO;
import com.jhenaoz.resources.GettingStartedResource;
import com.jhenaoz.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GettingStartedApplication extends Application<GettingStartedConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GettingStartedApplication().run(args);
    }

    @Override
    public String getName() {
        return "GettingStarted";
    }

    @Override
    public void initialize(final Bootstrap<GettingStartedConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final GettingStartedConfiguration configuration,
                    final Environment environment) {
        final GettingStartedResource resource = new GettingStartedResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );

        final PersonDAO personDao = new PersonDAO(hibernate.getSessionFactory());
        final PersonResource personResource = new PersonResource(personDao);
        environment.jersey().register(personResource);
        environment.jersey().register(resource);
    }


    private final HibernateBundle<GettingStartedConfiguration> hibernate = new HibernateBundle<GettingStartedConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(GettingStartedConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

}
