package com.brook_plus.authy;

import com.authy.AuthyApiClient;
import com.authy.AuthyException;
import com.authy.api.Token;
import com.authy.api.Tokens;
import com.authy.api.User;
import com.authy.api.Users;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthyService {

    public static final String API_KEY = "zbYaqaPrZJro74hS4dNqMddi8DJp0XrQ";

    public Map<String, ?> authyImage(String token) throws AuthyException {
        AuthyApiClient client = new AuthyApiClient(API_KEY);
        Tokens tokens = client.getTokens();

        Users users = client.getUsers();
        User user = users.createUser(
                "christian@brook.ai",
                "554-879-4635",
                "52"
        );

        if (user.isOk()) {
            Token response = tokens.verify(user.getId(), token);
            if (response.isOk()) {
                return response.toMap();
            }
            return Map.of("error", response.getError());
        }
        return Map.of("error", user.getError());
    }

}
