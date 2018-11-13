package com.zilliant.resources;

import com.codahale.metrics.annotation.Timed;
import com.zilliant.core.Person;
import com.zilliant.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDAO personDAO;

    public PersonResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Person findPerson(@PathParam("id") LongParam id) {
        return personDAO.findById(id.get());
    }

    @POST
    @Timed
    @UnitOfWork
    public Person createPerson (Person person) {
        return personDAO.create(person);
    }
}
