package gr.ntua.ece.softeng19b.api;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;

import java.util.Optional;
import java.security.secureRandom;
import java.util.Base64;

public final class AuthenticationService {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    private AuthenticationService() {}

    public static String createToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
