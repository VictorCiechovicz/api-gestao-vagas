package com.gestaovagas.modules.company.controllers;


import com.gestaovagas.modules.company.auth.AuthCompany;
import com.gestaovagas.modules.company.dto.AuthCompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthCompanyController {


    private final AuthCompany authCompany;

    @PostMapping("/company")
    public String create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        return authCompany.execute(authCompanyDTO);
    }
}
