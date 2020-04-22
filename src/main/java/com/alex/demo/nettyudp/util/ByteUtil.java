package com.alex.demo.nettyudp.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Alex
 * @Created Dec 26, 2018 1:34:10 PM
 * @Description
 *              <p>
 */
public class ByteUtil {

	public static final String DEFAULT_SPLIT = StringUtils.SPACE;

	private final static String[] BYTE_HEX_STR = new String[256];

	private final static String[] BYTE_BINARY_STR = new String[256];
	static {
		for (short i = -128; i <= 127; i++) {
			final int index = i + 128;
			final int value = i | 0xffffff00;
			final String hexString = Integer.toHexString(value).toUpperCase();
			BYTE_HEX_STR[index] = hexString.substring(hexString.length() - 2, hexString.length());
			final String binaryString = Integer.toBinaryString(value).toUpperCase();
			BYTE_BINARY_STR[index] = binaryString.substring(binaryString.length() - 8, binaryString.length());
		}
	}

	public static byte[] spliceBytes(final byte[]... sources) {
		int len = 0;
		for (final byte[] arr : sources) {
			len += arr.length;
		}
		final byte[] result = new byte[len];
		int lenCount = 0;
		for (final byte[] arr : sources) {
			System.arraycopy(arr, 0, result, lenCount, arr.length);
			lenCount = lenCount + arr.length;
		}
		return result;
	}

	public static String byte2BinaryString(final byte x) {
		final short index = (short) (x + 128);
		return BYTE_BINARY_STR[index];
	}

	public static String bytes2BinaryString(final byte[] bytes, final String split) {
		final StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(byte2BinaryString(bytes[i]));
			if (i != bytes.length - 1) {
				buffer.append(split);
			}
		}
		return buffer.toString();
	}

	public static String bytes2BinaryString(final byte[] bytes) {
		return bytes2BinaryString(bytes, DEFAULT_SPLIT);
	}

	public static String char2BinaryString(final char x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2BinaryString(char1(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(char0(x)));
		return buffer.toString();
	}

	public static String char2BinaryString(final char x) {
		return char2BinaryString(x, DEFAULT_SPLIT);
	}

	public static String short2BinaryString(final short x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2BinaryString(short1(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(short0(x)));
		return buffer.toString();
	}

	public static String short2BinaryString(final short x) {
		return short2BinaryString(x, DEFAULT_SPLIT);
	}

	public static String int2BinaryString(final int x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2BinaryString(int3(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(int2(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(int1(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(int0(x)));
		return buffer.toString();
	}

	public static String int2BinaryString(final int x) {
		return int2BinaryString(x, DEFAULT_SPLIT);
	}

	public static String long2BinaryString(final long x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2BinaryString(long7(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long6(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long5(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long4(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long3(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long2(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long1(x)));
		buffer.append(split);
		buffer.append(byte2BinaryString(long0(x)));
		return buffer.toString();
	}

	public static String long2BinaryString(final long x) {
		return long2BinaryString(x, DEFAULT_SPLIT);
	}

	public static String byte2HexString(final byte x) {
		final short index = (short) (x + 128);
		return BYTE_HEX_STR[index];
	}

	public static String bytes2HexString(final byte[] bytes, final String split) {
		final StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(byte2HexString(bytes[i]));
			if (i != bytes.length - 1) {
				buffer.append(split);
			}
		}
		return buffer.toString();
	}

	public static String bytes2HexString(final byte[] bytes) {
		return bytes2HexString(bytes, DEFAULT_SPLIT);
	}

	/**
	 * 转换大小端问题
	 * 
	 * @param byteStr
	 *            如 01 0A 02 03
	 * @return 如 03 02 0A 01
	 */
	public static String convertBigLittleEnd(final String byteStr) {
		if (!byteStr.trim().contains(DEFAULT_SPLIT)) {
			return byteStr;
		}
		final String[] splits = byteStr.trim().split(DEFAULT_SPLIT);
		if (splits.length < 2) {
			return byteStr;
		}
		final StringBuilder buffer = new StringBuilder();
		for (int i = splits.length - 1; i >= 0; i--) {
			buffer.append(splits[i]);
			if (i != 0) {
				buffer.append(DEFAULT_SPLIT);
			}
		}
		return buffer.toString();
	}

	public static String char2HexString(final char x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2HexString(char1(x)));
		buffer.append(split);
		buffer.append(byte2HexString(char0(x)));
		return buffer.toString();
	}

	public static String char2HexString(final char x) {
		return char2HexString(x, DEFAULT_SPLIT);
	}

	/**
	 * @param x
	 * @param split
	 * @return 高位在前 : 10--->00 0A
	 */
	public static String short2HexString(final short x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2HexString(short1(x)));
		buffer.append(split);
		buffer.append(byte2HexString(short0(x)));
		return buffer.toString();
	}

	/**
	 * @param x
	 * @return 高位在前 : 10--->00 0A
	 */
	public static String short2HexString(final short x) {
		return short2HexString(x, DEFAULT_SPLIT);
	}

	public static String int2HexString(final int x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2HexString(int3(x)));
		buffer.append(split);
		buffer.append(byte2HexString(int2(x)));
		buffer.append(split);
		buffer.append(byte2HexString(int1(x)));
		buffer.append(split);
		buffer.append(byte2HexString(int0(x)));
		return buffer.toString();
	}

	public static String int2HexString(final int x) {
		return int2HexString(x, DEFAULT_SPLIT);
	}

	public static String long2HexString(final long x, final String split) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(byte2HexString(long7(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long6(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long5(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long4(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long3(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long2(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long1(x)));
		buffer.append(split);
		buffer.append(byte2HexString(long0(x)));
		return buffer.toString();
	}

	public static String long2HexString(final long x) {
		return long2HexString(x, DEFAULT_SPLIT);
	}

	public static byte char1(final char x) {
		return (byte) (x >> 8);
	}

	public static byte char0(final char x) {
		return (byte) x;
	}

	public static byte short1(final short x) {
		return (byte) (x >> 8);
	}

	public static byte short0(final short x) {
		return (byte) x;
	}

	public static byte int3(final int x) {
		return (byte) (x >> 24);
	}

	public static byte int2(final int x) {
		return (byte) (x >> 16);
	}

	public static byte int1(final int x) {
		return (byte) (x >> 8);
	}

	public static byte int0(final int x) {
		return (byte) x;
	}

	public static byte long7(final long x) {
		return (byte) (x >> 56);
	}

	public static byte long6(final long x) {
		return (byte) (x >> 48);
	}

	public static byte long5(final long x) {
		return (byte) (x >> 40);
	}

	public static byte long4(final long x) {
		return (byte) (x >> 32);
	}

	public static byte long3(final long x) {
		return (byte) (x >> 24);
	}

	public static byte long2(final long x) {
		return (byte) (x >> 16);
	}

	public static byte long1(final long x) {
		return (byte) (x >> 8);
	}

	public static byte long0(final long x) {
		return (byte) x;
	}

	public static byte[] splitChart(final char x) {
		return new byte[] { char0(x), char1(x) };
	}

	public static char makeChar(final byte b1, final byte b0) {
		return (char) (b1 << 8 | b0 & 0xff);
	}

	public static byte[] splitShort(final short x) {
		return new byte[] { short0(x), short1(x) };
	}

	public static short makeShort(final byte b1, final byte b0) {
		return (short) (b1 << 8 | b0 & 0xff);
	}

	public static byte[] splitInt(final int x) {
		return new byte[] { int0(x), int1(x), int2(x), int3(x) };
	}

	public static int makeInt(final byte b3, final byte b2, final byte b1, final byte b0) {
		return b3 << 24 | (b2 & 0xff) << 16 | (b1 & 0xff) << 8 | b0 & 0xff;
	}

	public static byte[] splitLong(final long x) {
		return new byte[] { long0(x), long1(x), long2(x), long3(x), long4(x), long5(x), long6(x), long7(x) };
	}

	public static long makeLong(final byte b7, final byte b6, final byte b5, final byte b4, final byte b3, final byte b2, final byte b1,
			final byte b0) {
		return (long) b7 << 56 | ((long) b6 & 0xff) << 48 | ((long) b5 & 0xff) << 40 | ((long) b4 & 0xff) << 32 | ((long) b3 & 0xff) << 24
				| ((long) b2 & 0xff) << 16 | ((long) b1 & 0xff) << 8 | (long) b0 & 0xff;
	}

	public static byte[] splitFloat(final float x) {
		return splitInt(Float.floatToRawIntBits(x));
	}

	public static float makeFloat(final byte b3, final byte b2, final byte b1, final byte b0) {
		return Float.intBitsToFloat(makeInt(b3, b2, b1, b0));
	}

	public static byte[] splitDouble(final double x) {
		return splitLong(Double.doubleToRawLongBits(x));
	}

	public static double makeDouble(final byte b7, final byte b6, final byte b5, final byte b4, final byte b3, final byte b2, final byte b1,
			final byte b0) {
		return Double.longBitsToDouble(makeLong(b7, b6, b5, b4, b3, b2, b1, b0));
	}
}
