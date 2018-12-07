package com.jhenaoz.resources;

import com.codahale.metrics.annotation.Timed;
import com.jhenaoz.core.Person;
import com.jhenaoz.component.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDAO personDAO;

    public PersonResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Response findPerson(@PathParam("id") LongParam id) {
        Person person = personDAO.findById(id.get());
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Person createPerson (Person person) {
        return personDAO.create(person);
    }
}
