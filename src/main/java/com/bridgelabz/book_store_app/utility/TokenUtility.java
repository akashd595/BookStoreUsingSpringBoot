package com.bridgelabz.book_store_app.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {

    String TOKEN_SECRET = "Moscowww1";

    public String createToken(int id){
        try{

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            return JWT.create().withClaim("contact_id",id).sign(algorithm);

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (JWTCreationException e){
            e.printStackTrace();
        }
        return null;
    }

    public int decodeToken(String token){
        int contactId;

        Verification verification = null;
        try{
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        JWTVerifier jwtVerifier = verification.build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Claim claim =decodedJWT.getClaim("contact_id");
        contactId = claim.asInt();

        return contactId;
    }
}
