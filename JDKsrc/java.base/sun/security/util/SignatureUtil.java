/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.security.util;

import java.io.IOException;
import java.security.*;
import java.security.spec.*;
import java.util.Locale;
import sun.security.rsa.RSAUtil;
import jdk.internal.access.SharedSecrets;

/**
 * Utility class for Signature related operations. Currently used by various
 * internal PKI classes such as sun.security.x509.X509CertImpl,
 * sun.security.pkcs.SignerInfo, for setting signature parameters.
 *
 * @since   11
 */
public class SignatureUtil {

    private static String checkName(String algName) throws ProviderException {
        if (algName.indexOf(".") == -1) {
            return algName;
        }
        // convert oid to String
        try {
            return Signature.getInstance(algName).getAlgorithm();
        } catch (Exception e) {
            throw new ProviderException("Error mapping algorithm name", e);
        }
    }

    // Utility method of creating an AlgorithmParameters object with
    // the specified algorithm name and encoding
    private static AlgorithmParameters createAlgorithmParameters(String algName,
            byte[] paramBytes) throws ProviderException {

        try {
            algName = checkName(algName);
            AlgorithmParameters result =
                AlgorithmParameters.getInstance(algName);
            result.init(paramBytes);
            return result;
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new ProviderException(e);
        }
    }

    // Utility method for converting the specified AlgorithmParameters object
    // into an AlgorithmParameterSpec object.
    public static AlgorithmParameterSpec getParamSpec(String sigName,
            AlgorithmParameters params)
            throws ProviderException {

        sigName = checkName(sigName).toUpperCase(Locale.ENGLISH);
        AlgorithmParameterSpec paramSpec = null;
        if (params != null) {
            // AlgorithmParameters.getAlgorithm() may returns oid if it's
            // created during DER decoding. Convert to use the standard name
            // before passing it to RSAUtil
            if (params.getAlgorithm().indexOf(".") != -1) {
                try {
                    params = createAlgorithmParameters(sigName,
                        params.getEncoded());
                } catch (IOException e) {
                    throw new ProviderException(e);
                }
            }

            if (sigName.indexOf("RSA") != -1) {
                paramSpec = RSAUtil.getParamSpec(params);
            } else if (sigName.indexOf("ECDSA") != -1) {
                try {
                    paramSpec = params.getParameterSpec(ECParameterSpec.class);
                } catch (Exception e) {
                    throw new ProviderException("Error handling EC parameters", e);
                }
            } else {
                throw new ProviderException
                    ("Unrecognized algorithm for signature parameters " +
                     sigName);
            }
        }
        return paramSpec;
    }

    // Utility method for converting the specified parameter bytes into an
    // AlgorithmParameterSpec object.
    public static AlgorithmParameterSpec getParamSpec(String sigName,
            byte[] paramBytes)
            throws ProviderException {
        sigName = checkName(sigName).toUpperCase(Locale.ENGLISH);
        AlgorithmParameterSpec paramSpec = null;

        if (paramBytes != null) {
            if (sigName.indexOf("RSA") != -1) {
                AlgorithmParameters params =
                    createAlgorithmParameters(sigName, paramBytes);
                paramSpec = RSAUtil.getParamSpec(params);
            } else if (sigName.indexOf("ECDSA") != -1) {
                try {
                    Provider p = Signature.getInstance(sigName).getProvider();
                    paramSpec = ECUtil.getECParameterSpec(p, paramBytes);
                } catch (Exception e) {
                    throw new ProviderException("Error handling EC parameters", e);
                }
                // ECUtil discards exception and returns null, so we need to check
                // the returned value
                if (paramSpec == null) {
                    throw new ProviderException("Error handling EC parameters");
                }
            } else {
                throw new ProviderException
                     ("Unrecognized algorithm for signature parameters " +
                      sigName);
            }
        }
        return paramSpec;
    }

    // Utility method for initializing the specified Signature object
    // for verification with the specified key and params (may be null)
    public static void initVerifyWithParam(Signature s, PublicKey key,
            AlgorithmParameterSpec params)
            throws ProviderException, InvalidAlgorithmParameterException,
            InvalidKeyException {
        SharedSecrets.getJavaSecuritySignatureAccess().initVerify(s, key, params);
    }

    // Utility method for initializing the specified Signature object
    // for verification with the specified Certificate and params (may be null)
    public static void initVerifyWithParam(Signature s,
            java.security.cert.Certificate cert,
            AlgorithmParameterSpec params)
            throws ProviderException, InvalidAlgorithmParameterException,
            InvalidKeyException {
        SharedSecrets.getJavaSecuritySignatureAccess().initVerify(s, cert, params);
    }

    // Utility method for initializing the specified Signature object
    // for signing with the specified key and params (may be null)
    public static void initSignWithParam(Signature s, PrivateKey key,
            AlgorithmParameterSpec params, SecureRandom sr)
            throws ProviderException, InvalidAlgorithmParameterException,
            InvalidKeyException {
        SharedSecrets.getJavaSecuritySignatureAccess().initSign(s, key, params, sr);
    }
}
