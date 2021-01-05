package com.notes.notelist.controller;

import com.notes.notelist.model.GetTokenBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class TokenController {

    @PostMapping
    public void getTokenBody(@RequestBody GetTokenBody getTokenBody) {
    }
}
