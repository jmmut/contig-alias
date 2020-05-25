package com.ebivariation.contigalias.api;

import com.ebivariation.contigalias.service.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("contig-alias-admin")
@RestController
public class AdminController {

    private static final String ADMIN_AUTH_KEY = "admin_auth_key";

    private final AssemblyService service;

    @Autowired
    public AdminController(AssemblyService service) {
        this.service = service;
    }

    @PostMapping(value = "assemblies")
    public void fetchAndAddAccessionsToDb(String[] accessions) {
    }

}
