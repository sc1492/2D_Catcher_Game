package com.mycompany.a3;

import java.util.Vector;

/**
 * This class is used for printing out objects' color values
 */
public class Util {

	public static Vector <Integer> intToRGB (int color) {
		Vector <Integer> rgb = new Vector<>();
        int red = (color & 0x00ff0000) >> 16;
        int green = (color & 0x0000ff00) >> 8;
        int blue = (color & 0x000000ff);
        rgb.add(red);
        rgb.add(green);
        rgb.add(blue);
		return rgb;
	}
}
