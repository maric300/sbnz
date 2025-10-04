package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.RecursiveSearchQuery;
import com.ftn.sbnz.service.services.RecursiveSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search/recursive")
public class RecursiveSearchController {

    private final RecursiveSearchService searchService;

    @Autowired
    public RecursiveSearchController(RecursiveSearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public ResponseEntity<List<Mineral>> searchByGroup(@RequestBody RecursiveSearchQuery query) {
        List<Mineral> results = searchService.searchByGroup(query);
        return ResponseEntity.ok(results);
    }
}