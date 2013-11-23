package karstenroethig.maven.example5;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import junit.framework.TestCase;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeInt2of5Test extends TestCase {

	public void testCreateImages() throws Exception {
		
		Set<String> datas = new HashSet<String>();
		
		datas.add( "70103594733" );
		datas.add( "70103594732" );
		datas.add( "70103594731" );
		datas.add( "70103594730" );
		datas.add( "70103594729" );
		datas.add( "70103594728" );
		datas.add( "70103594727" );
		
		for( String data : datas ) {
			
			// do not trust the check digit algorithm of barbecue
			int checkDigit = calcCheckDigitForInt2of5( data );
			
			Barcode barcode = BarcodeFactory.createInt2of5( data + checkDigit );
			
			System.out.println( barcode.getData() );
			
			barcode.setBarWidth( 50 );
			barcode.setBarWidth( 3 );
			
			if( barcode.getFont() == null ) {
				barcode.setFont( new Font( "Arial", 0, 22 ) );
			}
			
			BufferedImage bufferedImage = BarcodeImageHandler.getImage( barcode );
			
			ImageIO.write( bufferedImage, "png", new File( "target/" + barcode.getData() + ".png" ) );
		}
	}
	
	private int calcCheckDigitForInt2of5( String data ) {
		
		int dataArray[] = new int[data.length()];
		
		for( int i = 0; i < data.length(); i++ ) {
			dataArray[i] = (int)data.charAt( i ) - (int)'0';
		}
		
		int[] scalarProduct = calcScalarProduct( dataArray, new int[]{ 3, 1 } );
		
		int sumOfDigits = calcSumOfDigits( scalarProduct );
		
		int sumOfDigitsMod10 = sumOfDigits % 10;
		
		int checkDigit = 10 - ( sumOfDigitsMod10 == 0 ? 10 : sumOfDigitsMod10 );
		
		return checkDigit;
	}
	
	private int[] calcScalarProduct( int[] data, int[] weighting ) {
		
		int wlen = weighting.length;
		int[] scalarProduct = new int[data.length];
		
		for( int i = 0; i < data.length; i++ ) {
			scalarProduct[i] = data[i] * weighting[i % wlen];
		}
		
		return scalarProduct;
	}
	
	private int calcSumOfDigits( int[] data ) {
		
		int sum = 0;
		
		for( int i = 0; i < data.length; i++ ) {
			sum += data[i];
		}
		
		return sum;
	}
	
}
