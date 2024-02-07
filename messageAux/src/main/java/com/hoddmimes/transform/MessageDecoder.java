package com.hoddmimes.transform;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class MessageDecoder
{
    private int mPosition;
    private int mArrayOffset;

    private byte[] mBuffer;

    private MessageDecoder(byte[] pBuffer, int pPosition, int pArrayOffset) {
        mBuffer = pBuffer;
        mPosition = pPosition;
        mArrayOffset = pArrayOffset;
    }

    public MessageDecoder(byte[] pBuffer ) {
        mPosition = 0;
        mArrayOffset = 0;
        mBuffer = pBuffer;
    }

    public MessageDecoder(MessageDecoder pDecoder ) {
        mBuffer = pDecoder.mBuffer;
        mPosition = 0;
        mArrayOffset = pDecoder.mArrayOffset;
    }

    public MessageDecoder slice() {
        return new MessageDecoder( this.mBuffer, 0, mPosition);
    }

    public void flip() {
        mArrayOffset = 0;
        mPosition = 0;
    }

    public boolean readBoolean() {
        if (mBuffer[mArrayOffset + mPosition++] == (byte) 0) {
            return false;
        }
        return true;
    }

    public byte readByte() {
        return (byte) mBuffer[mArrayOffset + mPosition++];
    }

    public char readCharacter() {
        char tValue = 0;
        tValue += (char) (mBuffer[ mArrayOffset + mPosition++] & 0xff);
        tValue += (char) ((mBuffer[ mArrayOffset + mPosition++] & 0xff) << 8);
        return tValue;
    }

    public short readShort() {
        short tValue = 0;
        tValue += (short) (mBuffer[mArrayOffset + mPosition++] & 0xff);
        tValue += (short) ((mBuffer[mArrayOffset + mPosition++] & 0xff) << 8);
        return tValue;
    }

    public int readInteger() {
        int tValue = 0;
        tValue += (int) (mBuffer[mArrayOffset + mPosition++] & 0xff);
        tValue += (int) ((mBuffer[mArrayOffset + mPosition++] & 0xff) << 8);
        tValue += (int) ((mBuffer[mArrayOffset + mPosition++] & 0xff) << 16);
        tValue += (int) ((mBuffer[mArrayOffset + mPosition++] & 0xff) << 24);
        return tValue;
    }

    public long readLong() {
        long tValue = (long) (mBuffer[mArrayOffset + mPosition++] & 0xff) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 8) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 16) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 24) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 32) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 40) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 48) +
                             ((long) (mBuffer[mArrayOffset + mPosition++] & 0xff) << 56);
        return tValue;
    }

    public float readFloat() {
        int tValue = readInteger();
        return Float.intBitsToFloat( tValue );
    }

    public double readDouble() {
        long tValue = readLong();
        return Double.longBitsToDouble( tValue );
    }

    public String readString() {
       if (!readBoolean()) {
           return null;
       }
       int tSize = readInteger();
       String tString = new String(mBuffer, mArrayOffset + mPosition, tSize, StandardCharsets.ISO_8859_1);
       mPosition += tSize;
       return tString;
    }

    public byte[] readByteVector() {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger();
        byte[] tBuffer = new byte[tSize];
        System.arraycopy( mBuffer, mArrayOffset + mPosition, tBuffer, 0, tSize );
        mPosition += tSize;
        return tBuffer;
    }
    public Map<String, String> readMap()
    {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger();
        Map<String,String> m = new HashMap<>(tSize);
        for (int i = 0; i < tSize; i++) {
            m.put( readString(), readString());
        }
        return m;
    }

    public LocalDateTime readLocalDateTime()
    {
        if (!readBoolean()) {
            return null;
        }
        return DateUtils.dateToLocalDateTime(new Date(readLong()));
    }

    public LocalDate readLocalDate()
    {
        if (!readBoolean()) {
            return null;
        }
        return DateUtils.dateToLocalDate(new Date(readLong()));
    }

    public MessageInterface readMessage(Class<?> pMessageClass) {
        if (!readBoolean()) { // Is there a message or not
            return null;
        }

        int tSize = readInteger();
        MessageDecoder tDecoder = this.slice();
        this.mPosition += tSize;

        try {
            MessageInterface tMessage = (MessageInterface) pMessageClass.newInstance();
            tMessage.decode(tDecoder);
            return tMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ===============================================================
     * Read Array Functions
     * ===============================================================
     */

    public List<Boolean> readBooleanArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<Boolean> tList = ListFactory.getList( pListType );
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readBoolean());
        }
        return tList;
    }

    public List<Short> readShortArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<Short> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readShort());
        }
        return tList;
    }

    public List<Integer> readIntegerArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<Integer> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readInteger());
        }
        return tList;
    }

    public List<Long> readLongArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<Long> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readLong());
        }
        return tList;
    }

    public List<Double> readDoubleArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<Double> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readDouble());
        }
        return tList;
    }

    public List<LocalDate> readLocalDateArray( String pListType ) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<LocalDate> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readLocalDate());
        }
        return tList;
    }

    public List<LocalDateTime> readLocalDateTimeArray( String pListType ) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<LocalDateTime> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readLocalDateTime());
        }
        return tList;
    }

    public List<Float> readFloatArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<Float> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readFloat());
        }
        return tList;
    }

    public List<String> readStringArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<String> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readString());
        }
        return tList;
    }


    public List<?> readMessageArray(Class<?> pClass) {
        if (!readBoolean()) {
            return null;
        }
        int tSize = readInteger(); // Read array size
        List<MessageInterface> tList = new ArrayList<MessageInterface>();
        for( int i = 0; i < tSize; i++ ) {
            tList.add( readMessage(pClass));
        }
        return tList;
    }

    public List<byte[]> readByteVectorArray(String pListType) {
        if (!readBoolean()) {
            return null;
        }

        int tSize = readInteger(); // Read array size
        List<byte[]> tList = ListFactory.getList(pListType);
        for( int i = 0; i < tSize; i++ ) {
            tList.add(readByteVector());
        }
        return tList;
    }

    public List<Map<String, String>> readMapArray(String pListType) {
        if (readBoolean()) {
            int tSize = readInteger();
            List<Map<String,String>> tList = ListFactory.getList(pListType);
            for (int i = 0; i < tSize; i++) {
                tList.add( readMap());
            }
            return tList;
        }
        return null;
    }
}
