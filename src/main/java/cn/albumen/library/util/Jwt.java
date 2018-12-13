package cn.albumen.library.util;

import cn.albumen.library.constant.SecurityConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * @author Albumen
 */
public class Jwt {
    private static RSAPublicKey publicKey = RsaKeyTool.getInstance().getPublicKey();
    private static RSAPrivateKey privateKey = RsaKeyTool.getInstance().getPrivateKey();
    private static Algorithm algorithmRS = Algorithm.RSA256(publicKey, privateKey);

    public static String create(String userName) {
        String token = null;
        try {
            token= JWT.create()
                    .withIssuer("Albumen")
                    .withSubject(userName)
                    .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConfig.EXP_TIME))
                    .sign(algorithmRS);
        } catch (JWTCreationException exception){
            return null;
        }
        return token;
    }

    public static boolean verify(String token,String userName) {
        try {
            JWTVerifier verifier = JWT.require(algorithmRS)
                    .withIssuer("Albumen")
                    .withSubject(userName)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
            //Invalid signature/claims
        }
    }

    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithmRS)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
            //Invalid signature/claims
        }
    }
}
