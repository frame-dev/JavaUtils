package de.framedev.javautils;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName Converter
 * / Date: 08.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class Converter {

    public boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean isShort(String number) {
        try {
            Short.parseShort(number);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean isFloat(String number) {
        try {
            Float.parseFloat(number);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean isLong(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean isBoolean(String bool) {
        try {
            Boolean.parseBoolean(bool);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public int stringToInteger(String number) {
        if (isInteger(number))
            return Integer.parseInt(number);
        return 0;
    }

    public int byteToInteger(byte number) {
        return number;
    }

    public int shortToInteger(short number) {
        return number;
    }

    public int longToInteger(long number) {
        return (int) number;
    }

    public int doubleToInteger(double number) {
        return (int) number;
    }

    public int floatToInteger(float number) {
        return (int) number;
    }

    public double stringToDouble(String number) {
        if (isDouble(number))
            return Double.parseDouble(number);
        return 0;
    }

    public double byteToDouble(byte number) {
        return number;
    }

    public double shortToDouble(short number) {
        return number;
    }

    public double intToDouble(int number) {
        return number;
    }

    public double longToDouble(long number) {
        return number;
    }

    public double floatToDouble(float number) {
        return number;
    }

    public short stringToShort(String number) {
        if (isShort(number))
            return Short.parseShort(number);
        return 0;
    }

    public short byteToShort(byte number) {
        return number;
    }

    public short intToShort(int number) {
        return (short) number;
    }

    public short longToShort(long number) {
        return (short) number;
    }

    public short floatToShort(float number) {
        return (short) number;
    }

    public short doubleToShort(double number) {
        return (short) number;
    }

    public float stringToFloat(String number) {
        if (isFloat(number))
            return Float.parseFloat(number);
        return 0f;
    }

    public float byteToFloat(byte number) {
        return number;
    }

    public float shortToFloat(short number) {
        return number;
    }

    public float intToFloat(int number) {
        return number;
    }

    public float longToFloat(long number) {
        return number;
    }

    public float doubleToFloat(double number) {
        return (float) number;
    }

    public long stringToLong(String number) {
        if (isLong(number))
            return Long.parseLong(number);
        return 0L;
    }

    public long byteToLong(byte number) {
        return number;
    }

    public long shortToLong(short number) {
        return number;
    }

    public long intToLong(int number) {
        return number;
    }

    public long doubleToLong(double number) {
        return (long) number;
    }

    public long floatToLong(float number) {
        return (long) number;
    }

    public String byteToString(byte number) {
        return String.valueOf(number);
    }

    public String shortToString(short number) {
        return String.valueOf(number);
    }

    public String intToString(int number) {
        return String.valueOf(number);
    }

    public String longToString(long number) {
        return String.valueOf(number);
    }

    public String floatToString(float number) {
        return String.valueOf(number);
    }

    public String doubleToString(double number) {
        return String.valueOf(number);
    }

    public String objectToString(Object object) {
        return String.valueOf(object);
    }

    public short objectToShort(Object object) {
        return Short.parseShort(String.valueOf(object));
    }

    public int objectToInt(Object object) {
        return Integer.parseInt(String.valueOf(object));
    }

    public long objectToLong(Object object) {
        return Long.parseLong(String.valueOf(object));
    }

    public float objectToFloat(Object object) {
        return Float.parseFloat(String.valueOf(object));
    }

    public double objectToDouble(Object object) {
        return Double.parseDouble(String.valueOf(object));
    }

    public boolean objectToBoolean(Object object) {
        return Boolean.parseBoolean(String.valueOf(object));
    }

    public boolean stringToBoolean(String bool) {
        if (isBoolean(bool)) {
            return Boolean.parseBoolean(bool);
        }
        return false;
    }
}
