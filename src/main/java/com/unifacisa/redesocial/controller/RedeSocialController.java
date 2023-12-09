package com.unifacisa.redesocial.controller;

import com.unifacisa.redesocial.model.Person;
import com.unifacisa.redesocial.model.Post;
import com.unifacisa.redesocial.repository.PersonRepository;
import com.unifacisa.redesocial.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class RedeSocialController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PersonRepository personRepository;

    Person user;

    @PostMapping("/new_post")
    public String newPost(@ModelAttribute Post post, Model model){

        post.setPerson(user);
        postRepository.save(post);

        return posts(model);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Person person, Model model){
        user = personRepository.save(person);
        return posts(model);
    }

    @GetMapping("/posts")
    public String posts(Model model){
        ArrayList<Post> posts = new ArrayList<>();
        posts = (ArrayList<Post>) postRepository.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new Person());
        return "login";
    }

    @GetMapping("/new_post")
    public String newPost(Model model){
        model.addAttribute("post", new Post());
        return "new_post";
    }



}
