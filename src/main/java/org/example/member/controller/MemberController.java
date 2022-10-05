package org.example.member.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @GetMapping("/members")
    public ResponseEntity<List<Object>> get(){

        return ResponseEntity.noContent().build();
    }
}
