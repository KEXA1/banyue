/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Utils;

import Decoder.BASE64Decoder;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;
import javax.imageio.ImageIO;
/**
 *
 * @author dell
 */
public class ImageDeal {
    /**
	 * 通过BASE64Decoder解码，并生成图片
         * @param imgFilePath img路径
	 * @param imgStr 解码后的string
     * @return 
	 */
	public static boolean string2Image(String imgStr, String imgFilePath) {
		// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) return false;
		try {
			// Base64解码
			byte[] b = new BASE64Decoder().decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					// 调整异常数据
					b[i] += 256;
				}
			}
                    // 生成Jpeg图片
                    try ( OutputStream out = new FileOutputStream(imgFilePath)) {
                        out.write(b);
                        out.flush();
                    }
			return true;
		} catch (IOException e) {
			return false;
		}
	}
    /**
     * 图像转String
     * @param img
     * @return
     */
    public static String imgToBase64String(final RenderedImage img) {
        final ByteArrayOutputStream bitmap = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "jpg", Base64.getEncoder().wrap(bitmap));
            return bitmap.toString(StandardCharsets.ISO_8859_1.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
}
