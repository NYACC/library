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
public class JwtUtil {
    private static RSAPublicKey publicKey = RsaKeyUtil.getInstance().getPublicKey();
    private static RSAPrivateKey privateKey = RsaKeyUtil.getInstance().getPrivateKey();
    private static Algorithm algorithmRS = Algorithm.RSA256(publicKey, privateKey);

    public static String create(String userName) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("Albumen")
                    .withSubject(userName)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXP_TIME))
                    .sign(algorithmRS);
        } catch (JWTCreationException exception) {
            return null;
        }
        return token;
    }

    public static String create(String userName, String[] permission) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("Albumen")
                    .withSubject(userName)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXP_TIME))
                    .withArrayClaim("Permission", permission)
                    .sign(algorithmRS);
        } catch (JWTCreationException exception) {
            return null;
        }
        return token;
    }

    public static boolean verify(String token, String userName) {
        try {
            JWTVerifier verifier = JWT.require(algorithmRS)
                    .withIssuer("Albumen")
                    .withSubject(userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public static String[] verifyWithPermission(String token, String userName) {
        try {
            JWTVerifier verifier = JWT.require(algorithmRS)
                    .withIssuer("Albumen")
                    .withSubject(userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("Permission").asArray(String.class);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithmRS)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
