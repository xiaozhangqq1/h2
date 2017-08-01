package htest.controller;

import java.util.*;

import htest.model.User;
import htest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @GetMapping(value="/users")
    public List<User> getUserList() {

        List<User> r = new ArrayList<User>(users.values());
        return userRepository.findAll();
    }

    @PostMapping(value="/")
    public User postUser(@ModelAttribute User user) {

        //users.put(user.getId(), user);

        return userRepository.save(user);
    }

    @GetMapping(value="/{id}")
    public User getUser(@PathVariable Long id) {

        return userRepository.findOne(id);
    }

    @PutMapping(value="/{id}")
    public User putUser(@PathVariable Long id, @ModelAttribute User user) {

        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return userRepository.save(u);
    }

    @DeleteMapping(value="/{id}")
    public void deleteUser(@PathVariable Long id) {

        userRepository.delete(id);
    }

}

