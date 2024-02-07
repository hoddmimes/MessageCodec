/*
 *
 * Copyright (c) 2008 Hoddmimes Solutions AB, Stockholm,
 * Sweden. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Hoddmimes Solutions AB, Stockholm, Sweden. You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Hoddmimes.
 *
 * Hoddmimes makes no representations or warranties about the suitability
 * of the software, either expressed or implied, including, but not limited
 * to, the implied warranties of merchantibility, fitness for a particular
 * purpose, or non-infringement. Hoddmimes shall not be liable for any
 * damages suffered by licensee as a result of using, modifying, or
 * distributing this software or its derivatives.
 */
package com.hoddmimes.transform;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class MessageAux {
	private static final String BLANKS = "                                                       ";

	private static String blanks( int pLength ) {
		return BLANKS.substring(0,pLength);
	}

	public static String format(byte[] pArray) {
		if (pArray == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append( Hex.toHexString( pArray ));
		return tSB.toString();
	}

	public static String addByteVectorArray(int pOffset, List<byte[]> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("\n");
		pList.forEach(v -> tSB.append( blanks(pOffset) + Hex.toHexString(v) + "\n"));
		return tSB.toString();
	}

	public static String addStringArray(int pOffset, List<String> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(1024);
		tSB.append("\n");
		pList.forEach(s -> tSB.append( blanks(pOffset) + s + "\n"));
		return tSB.toString();
	}

	public static String addMapArray(int pOffset, List<Map<String,String>> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(1024);

		tSB.append("\n");
		pList.forEach(m -> tSB.append( blanks(pOffset+1) + m + "\n"));
		return tSB.toString().substring(0, tSB.toString().length() - 1);
	}

	public static String addLocalDateArray(int pOffset, List<LocalDate> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(d -> tSB.append(String.valueOf(d) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

	public static String addLocalDateTimeArray(int pOffset, List<LocalDateTime> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(d -> tSB.append(String.valueOf(d) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

	public static String addBooleanArray(int pOffset, List<Boolean> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}
	public static String addShortArray(int pOffset, List<Short> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}
	public static String addIntegerArray(int pOffset, List<Integer> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

	public static String addLongArray(int pOffset, List<Long> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

	public static String addCharacterArray(int pOffset, List<Character> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

	public static String addFloatArray(int pOffset, List<Float> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

	public static String addDoubleArray(int pOffset, List<Double> pList) {
		if (pList == null) {
			return "null";
		}
		StringBuilder tSB = new StringBuilder(256);
		tSB.append("[");
		pList.forEach(i -> tSB.append(String.valueOf(i) + ", "));
		return tSB.toString().substring(0, tSB.toString().length() - 2) + "]";
	}

}
