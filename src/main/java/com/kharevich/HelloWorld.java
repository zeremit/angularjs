package com.kharevich;

import com.kharevich.domain.User;
import com.kharevich.repository.Repository;
import com.kharevich.repository.RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 * Created by zeremit on 29.4.14.
 */
@Path("hello")
public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    private Repository repo;

    @PostConstruct
    public void init() {
        logger.info("run init");
        EntityManagerFactory emf = createEntityManagerFactory(Constants.MYSQL);
        repo = new RepositoryImpl(emf);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        User user = new User();
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("zeremit@gmail.com");
        repo.save(user);
        return "Hello!";
    }
}
