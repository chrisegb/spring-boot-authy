package com.brook_plus.authy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.Map;

@RestController
@RequestMapping("authy")
public class AuthyController {

    @Autowired
    private AuthyService authyService;

    @GetMapping(value = "generate/qrcode/{token}")
    public Map genQRCode(@PathVariable String token) throws Exception {
        return authyService.authyImage(token);
    }

}
