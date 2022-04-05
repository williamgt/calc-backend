package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class TokenController {

    public static String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";
    Logger logger = LoggerFactory.getLogger(TokenController.class); //Setting up logger for calculator controller

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(@RequestBody UserLogin userlogin) throws Exception {
        logger.info("GOT TO TOKEN CONTROLLER");
        // check username and password are valid to access token
        // note that subsequent request to the API need this token
        if (userlogin.getUsername().equals("admin") && userlogin.getPassword().equals("password")) {
            logger.info("Successfully sent token");
            return generateToken(userlogin.getUsername());
        }
        logger.info("Something went wrong generating token");
        return "Access denied, wrong credentials....";
    }

    public String generateToken(String userId) throws Exception {
        Key key = Keys.hmacShaKeyFor(keyStr.getBytes("UTF-8"));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userId", userId);
        claims.put("authorities", grantedAuthorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000000))
                .signWith(key)
                .compact();
    }

}

