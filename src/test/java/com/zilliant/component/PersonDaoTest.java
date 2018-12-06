package com.zilliant.component;

import com.zilliant.core.Person;;
import io.dropwizard.testing.junit.DAOTestRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDaoTest {

    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
            .addEntityClass(Person.class)
            .build();

    private PersonDAO personDAO;

    @Before
    public void setUp() throws Exception {
        personDAO = new PersonDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createPerson() {
        final Person jeff = daoTestRule.inTransaction(() -> personDAO.create(new Person("Jeff", "The plumber")));
        assertThat(jeff.getId()).isGreaterThan(0);
        assertThat(jeff.getFullName()).isEqualTo("Jeff");
        assertThat(jeff.getJobTitle()).isEqualTo("The plumber");
    }

    @Test
    public void findAll() {
        daoTestRule.inTransaction(() -> {
            personDAO.create(new Person("Jeff", "The plumber"));
            personDAO.create(new Person("Jim", "The cook"));
            personDAO.create(new Person("Randy", "The watchman"));
        });

        final List<Person> persons = personDAO.findAll();
        assertThat(persons).extracting("fullName").containsOnly("Jeff", "Jim", "Randy");
        assertThat(persons).extracting("jobTitle").containsOnly("The plumber", "The cook", "The watchman");
    }

    @Test
    public void sampleTestCase() {
        Assert.assertEquals(1,1);
    }
}
