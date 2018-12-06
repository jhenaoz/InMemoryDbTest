
package com.zilliant.resources;

import com.codahale.metrics.annotation.Timed;
import com.zilliant.core.Person;
import com.zilliant.component.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/hello-world")
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorldResource {

    @GET
    @Timed
    @UnitOfWork
    public String findPerson() {
        return "Hello World";
    }
}
