package mydiaryweb.controller;

import mydiaryweb.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping(value = {"/home", "/", ""})
    public String home() {
        return "home";
    }

        @PersistenceContext(type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @RequestMapping(value="/retrieve")
    @ResponseBody
    public List retrieveAll() {
        return entityManager.createQuery("SELECT e FROM Person e").getResultList();
    }

    @RequestMapping(value="/add")
    @ResponseBody
    @Transactional
    public void addOne() {
        Person person = new Person();
        person.setEmail("added@email.com");
        person.setName("Newly added");
        entityManager.persist(person);
    }
}
