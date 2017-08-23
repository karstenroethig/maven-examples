package karstenroethig.maven.example3;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

import junit.framework.TestCase;

public class JasyptTest extends TestCase
{
	@Test
	public void testPBE() throws Exception
	{
		// PBE (Password Based Encryptor)

		// String PBE Encryptor
		PBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword( "myMasterPassword" );

		String encryptedPassword = encryptor.encrypt( "myPassword" );

		System.out.println( encryptedPassword );

		// Text Encryptor
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword( "myMasterPassword" );

		String encryptedMessage = textEncryptor.encrypt( "myText" );
		String decryptedMessage = textEncryptor.decrypt( encryptedMessage );

		System.out.println( encryptedMessage );
		System.out.println( decryptedMessage );
	}
}
