package com.imos.model.test;


import com.imos.model.Person;
import com.imos.utils.HibernateService;
import com.imos.utils.RepositoryException;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author alok.meher
 */
public class HibernateTesting {

    private static final HibernateService service = HibernateService.INSTANCE;
    private Session session;

    @BeforeAll
    public static void setUp() throws RepositoryException {
        service.config();
    }

    @AfterAll
    public static void shutdown() throws RepositoryException {
        service.shutDown();
    }

    @BeforeEach
    public void setUpForTest() throws RepositoryException {
        session = service.openConnection();
    }

    @AfterEach
    public void shutdownForTest() throws RepositoryException {
        service.closeConnection(session);
    }

    @Test
    public void modelTesting()  throws Exception{
        assertTrue(true);

        Person p = new Person();
        p.setName("Alok");
        p.setDateOfBirth(LocalDate.now());
        assertEquals("Alok", p.getName());

        session.save(p);

        List<Person> result = session.createQuery("from Person p", Person.class)
                .getResultList();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Alok", result.get(0).getName());
    }
}
