package cn.albumenj.util;


import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cn.albumenj.constant.SecurityConfig.*;

/**
 * @author Albumen
 */
public class RsaKeyTool {
    private final static Logger logger = LoggerFactory.getLogger(RsaKeyTool.class);
    private static RsaKeyTool instance = new RsaKeyTool();

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;


    public RsaKeyTool(){
        try{
            loadPublicKey();
        }catch (Exception e){
            logger.error(e.toString());
        }
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public static RsaKeyTool getInstance() {
        return instance;
    }


    private void loadPublicKey() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(PEM_KEY_PATH));
        Security.addProvider(new BouncyCastleProvider());
        KeyPair kp = (KeyPair) new PEMReader(br).readObject();

        this.privateKey = (RSAPrivateKey) kp.getPrivate();
        this.publicKey = (RSAPublicKey) kp.getPublic();
    }
}
