package karstenroethig.maven.example3;

import junit.framework.TestCase;

import org.junit.Test;
import org.sonatype.plexus.components.cipher.DefaultPlexusCipher;
import org.sonatype.plexus.components.cipher.PlexusCipherException;
import org.sonatype.plexus.components.sec.dispatcher.DefaultSecDispatcher;

public class MavenPasswordEncryptionTest extends TestCase {

	private static final String MASTER_PASSWORD = "foo";

	private static final String MASTER_PASSWORD_ENCRYPTED = "{5xaLhee+TVMHAQhkooZWmRVV+/Tn4+umz4+X0Vl7KFQ=}";

	private static final String PASSWORD = "bar";

	private static final String PASSWORD_ENCRYPTED = "{kiHIorKUbG0HGeqO8u0PNR2xJwYjNpmFro1WsBX6FbY=}";

	@Test
	public void testEncryptMasterPassword() throws Exception {

		/*
		 * Master-Passwort verschlüsseln mvn --encrypt-master-password
		 * <password>
		 */
		String encryptedMasterPassword1 = encrypt(MASTER_PASSWORD,
				DefaultSecDispatcher.SYSTEM_PROPERTY_SEC_LOCATION);

		String encryptedMasterPassword2 = encrypt(MASTER_PASSWORD,
				DefaultSecDispatcher.SYSTEM_PROPERTY_SEC_LOCATION);

		assertFalse(encryptedMasterPassword1.equals(encryptedMasterPassword2));
	}

	@Test
	public void testEncryptPassword() throws Exception {

		/*
		 * Passwort mit Hilfe des Master-Passwortes verschlüsseln mvn
		 * --encrypt-password <password>
		 */
		String encryptedPassword1 = encrypt(PASSWORD, MASTER_PASSWORD);

		String encryptedPassword2 = encrypt(PASSWORD, MASTER_PASSWORD);

		assertFalse(encryptedPassword1.equals(encryptedPassword2));
	}

	@Test
	public void testDecryptPassword() throws Exception {

		String masterPassword = decrypt(MASTER_PASSWORD_ENCRYPTED,
				DefaultSecDispatcher.SYSTEM_PROPERTY_SEC_LOCATION);

		assertEquals(MASTER_PASSWORD, masterPassword);

		String password = decrypt(PASSWORD_ENCRYPTED, masterPassword);

		assertEquals(PASSWORD, password);
	}

	private String encrypt(String password, String masterPassword)
			throws PlexusCipherException {

		DefaultPlexusCipher dc = new DefaultPlexusCipher();

		return dc.encryptAndDecorate(password, masterPassword);
	}

	private String decrypt(String encryptedPassword, String masterPassword)
			throws PlexusCipherException {

		DefaultPlexusCipher dc = new DefaultPlexusCipher();

		return dc.decryptDecorated(encryptedPassword, masterPassword);
	}
}
