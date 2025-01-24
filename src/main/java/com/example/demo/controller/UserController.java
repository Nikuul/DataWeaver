package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final AtomicInteger userIndex = new AtomicInteger(0);

    // Wyświetlanie listy użytkowników
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    // Formularz dodawania użytkownika
    @GetMapping("/add-user")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    // Dodawanie nowego użytkownika
    @PostMapping("/users")
    public String addUser(@ModelAttribute User user) {
        List<User> users = userRepository.findAll();
        long newId = users.isEmpty() ? 1 : users.get(users.size() - 1).getId() + 1;
        user.setId(newId);
        userRepository.save(user);
        return "redirect:/users";
    }

    // Formularz edytowania użytkownika
    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    // Zapis edytowanego użytkownika
    @PostMapping("/users/{id}/edit")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername()); 
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return "redirect:/users";
    }

    // Usuwanie użytkownika
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/users";
    }

    // Pobieranie użytkownika z jsonplaceholder
    @GetMapping("/fetch-user")
    public String fetchUser() {
        int index;
        User user = null;
        do {
            index = userIndex.getAndIncrement();
            if (userRepository.existsById((long) (index + 1))) {
                continue;
            }
            String url = "https://jsonplaceholder.typicode.com/users/" + (index + 1);
            try {
                user = restTemplate.getForObject(url, User.class);
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode().value() == 404) {
                } else {
                    throw e;
                }
            }
        } while (user == null || userRepository.existsById(user.getId()));
        
        userRepository.save(user);
        return "redirect:/users";
    }
}