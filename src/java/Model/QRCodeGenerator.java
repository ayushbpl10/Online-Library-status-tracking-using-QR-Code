/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author HOME
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.EnumMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.apache.tomcat.util.codec.binary.Base64;

public class QRCodeGenerator {

	/**
     * @return 
	 * @throws WriterException
	 * @throws IOException
	 */

	public static String createQRImage(String QrStr) throws WriterException, IOException {
                String myCodeText = QrStr;
		String filePath = "/QR.png";
		int size = 250;
		String fileType = "png";
		File myFile = new File(filePath);
		
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int Width = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(Width, Width,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
 
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, Width, Width);
			graphics.setColor(Color.BLACK);
 
			for (int i = 0; i < Width; i++) {
				for (int j = 0; j < Width; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(image, fileType, baos);

                        String data = DatatypeConverter.printBase64Binary(baos.toByteArray());
                        return data;
		
	}
}

