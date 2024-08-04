package ru.javawebinar.topjava.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static ru.javawebinar.topjava.web.user.AdminUIController.URL;

@RestController
@RequestMapping(value = URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIController extends AbstractUserController {
    static final String URL = "/admin/users";

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void switchEnabled(@RequestBody boolean isEnabled,
                              @PathVariable int id) {
        super.switchEnabled(isEnabled, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String password) {
        super.create(new User(null, name, email, password, Role.USER));
    }
}
