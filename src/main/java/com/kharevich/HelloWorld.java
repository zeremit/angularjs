package com.kharevich;

import com.google.gson.Gson;
import com.kharevich.domain.User;
import com.kharevich.repository.Repository;
import com.kharevich.repository.RepositoryImpl;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

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

    public static JSONObject getJsonFromMyFormObject(List<User> form) throws JSONException {
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < form.size(); i++)
        {
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id", form.get(i).getId());
            formDetailsJson.put("name", form.get(i).getName());
            formDetailsJson.put("surname", form.get(i).getSurname());

            jsonArray.put(formDetailsJson);
        }
        responseDetailsJson.put("forms", jsonArray);
        return responseDetailsJson;
    }

    @GET
    @Produces()
    public String getIt() throws JSONException {
        User user = new User();
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("zeremit@gmail.com");
        repo.save(user);
        int count = repo.countPersons();
        List<User> users = repo.findPersons(User.class, 0, count, null, null);
        Gson gson = new Gson();
        String result = gson.toJson(users);
        return result;
    }
}
