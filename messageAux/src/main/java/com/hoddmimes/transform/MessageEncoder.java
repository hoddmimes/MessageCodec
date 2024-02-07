package com.hoddmimes.transform;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MessageEncoder
{
    private static final int DEFAULT_INIT_SIZE = 256;
    private static final boolean FAST_STRING = true;

    private byte[]  mBuffer;
    private int     mPosition;


    public MessageEncoder() {
        this( DEFAULT_INIT_SIZE );
    }

    public MessageEncoder(int pInitSize ) {
        mBuffer = new byte[pInitSize];
        mPosition = 0;
    }

    public int position() {
        return mPosition;
    }

    public byte[] getBuffer() {
        return mBuffer;
    }

    public byte[] getBytes() {
        byte[] tBytes = new byte[ mPosition ];
        System.arraycopy( mBuffer, 0, tBytes, 0, mPosition);
        return tBytes;
    }

    public void reset() {
        mPosition = 0;
    }

    public int remaining() {
        return (mBuffer.length - mPosition);
    }

    public int capacity() {
        return mBuffer.length;
    }

    private void ensureCapacity(int pRequestedSize) {
        if (remaining() - pRequestedSize < 0) {
            int tAllocSize = 0;

            if (mBuffer.length >= 262144) {
                tAllocSize = Math.max(262144, pRequestedSize);
            } else {
                tAllocSize = Math.max((2 * mBuffer.length), pRequestedSize);
            }
            byte[] tBuffer = new byte[mBuffer.length + tAllocSize];
            System.arraycopy(mBuffer, 0, tBuffer, 0, mPosition);
            mBuffer = tBuffer;
        }
    }

    public MessageEncoder add(final boolean pValue ) {
        ensureCapacity( Byte.BYTES );
        mBuffer[mPosition++] = (pValue) ? (byte) 1 : (byte) 0;
        return this;
    }
    public MessageEncoder add(final byte pValue ) {
        ensureCapacity( Byte.BYTES );
        mBuffer[mPosition++] = pValue;
        return this;
    }

    public MessageEncoder add(final char pValue ) {
        ensureCapacity( Character.BYTES );
        mBuffer[mPosition++] = (byte) (pValue >>> 0);
        mBuffer[ mPosition++] = (byte) (pValue >>> 8);
        return this;
    }

    public MessageEncoder add(final short pValue ) {
        ensureCapacity( Short.BYTES );
        mBuffer[ mPosition++] = (byte) (pValue >>> 0);
        mBuffer[ mPosition++] = (byte) (pValue >>> 8);
        return this;
    }

    public MessageEncoder add(final int pValue )
    {
        ensureCapacity( Integer.BYTES );
        mBuffer[ mPosition++] = (byte) (pValue >>> 0);
        mBuffer[ mPosition++] = (byte) (pValue >>> 8);
        mBuffer[ mPosition++] = (byte) (pValue >>> 16);
        mBuffer[ mPosition++] = (byte) (pValue >>> 24);
        return this;
    }

    public MessageEncoder add(final long pValue )
    {
        ensureCapacity( Long.BYTES );
        mBuffer[ mPosition++] = (byte) (pValue >>> 0);
        mBuffer[ mPosition++] = (byte) (pValue >>> 8);
        mBuffer[ mPosition++] = (byte) (pValue >>> 16);
        mBuffer[ mPosition++] = (byte) (pValue >>> 24);
        mBuffer[ mPosition++] = (byte) (pValue >>> 32);
        mBuffer[ mPosition++] = (byte) (pValue >>> 40);
        mBuffer[ mPosition++] = (byte) (pValue >>> 48);
        mBuffer[ mPosition++] = (byte) (pValue >>> 56);
        return this;
    }

    public MessageEncoder add(float pValue )
    {
        ensureCapacity( Integer.BYTES );
        int tValue = Float.floatToIntBits(pValue);
        mBuffer[ mPosition++] = (byte) (tValue >>> 0);
        mBuffer[ mPosition++] = (byte) (tValue >>> 8);
        mBuffer[ mPosition++] = (byte) (tValue >>> 16);
        mBuffer[ mPosition++] = (byte) (tValue >>> 24);
        return this;
    }

    public MessageEncoder add(double pValue )
    {
        ensureCapacity( Double.BYTES );
        long tValue = Double.doubleToLongBits(pValue);
        mBuffer[ mPosition++] = (byte) (tValue >>> 0);
        mBuffer[ mPosition++] = (byte) (tValue >>> 8);
        mBuffer[ mPosition++] = (byte) (tValue >>> 16);
        mBuffer[ mPosition++] = (byte) (tValue >>> 24);
        mBuffer[ mPosition++] = (byte) (tValue >>> 32);
        mBuffer[ mPosition++] = (byte) (tValue >>> 40);
        mBuffer[ mPosition++] = (byte) (tValue >>> 48);
        mBuffer[ mPosition++] = (byte) (tValue >>> 56);
        return this;
    }

    public MessageEncoder add(String pValue ) {
        if (pValue == null) {
            add(false);
        } else {
            add(true);
            byte[] tStrBuf;

            if (FAST_STRING) {
                tStrBuf = new byte[ pValue.length() ];
                for( int i = 0; i < pValue.length(); i++) {
                    tStrBuf[i] = (byte) (pValue.charAt(i) & 0xff);
                }
            } else {
                tStrBuf = pValue.getBytes(StandardCharsets.ISO_8859_1);
            }
            add(tStrBuf.length);
            ensureCapacity(tStrBuf.length);
            System.arraycopy(tStrBuf, 0, mBuffer, mPosition, tStrBuf.length);
            mPosition += tStrBuf.length;;
        }
        return this;
    }


    public MessageEncoder add(MessageInterface pMessage) {
        if (pMessage == null) {
            add(false);
        } else {
            add(true);
            MessageEncoder tEncoder = new MessageEncoder();
            pMessage.encode(tEncoder);
            add( tEncoder.position());
            ensureCapacity(tEncoder.position());
            System.arraycopy( tEncoder.getBuffer(), 0, mBuffer, mPosition, tEncoder.position());
            mPosition += tEncoder.position();
        }
        return this;
    }

    public MessageEncoder add(Map<String, String> pMap ) {
        if (pMap == null) {
            add(false);
        } else {
            add(true);
            add(pMap.size());
            for( Map.Entry<String,String> me : pMap.entrySet()) {
                add(me.getKey());
                add(me.getValue());
            }
        }
        return this;
    }

    public MessageEncoder add(LocalDate pValue) {
        if (pValue == null) {
            add(false);
        } else {
            add(true);
            add(DateUtils.localDateToDate(pValue).getTime());
        }
        return  this;
    }

    public MessageEncoder add(LocalDateTime pValue) {
        if (pValue == null) {
            add(false);
        } else {
            add(true);
            add(DateUtils.localDateTimeToDate(pValue).getTime());
        }
        return this;
    }

    public MessageEncoder add(byte[] pBuffer) {
        if (pBuffer == null) {
            add(false);
        } else {
            add(true);
            add(pBuffer.length);
            ensureCapacity(pBuffer.length);
            System.arraycopy(pBuffer,0, mBuffer, mPosition, pBuffer.length);
            mPosition += pBuffer.length;
        }
        return this;
    }

    /**
     * =======================================================================
     * 		Add Array Methods
     * =======================================================================
     */


    public MessageEncoder addLocalDateArray(List<LocalDate> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addLocalDateTimeArray(List<LocalDateTime> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addBooleanArray(List<Boolean> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addShortArray(List<Short> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }




    public MessageEncoder addIntegerArray(List<Integer> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addLongArray(List<Long> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addDoubleArray(List<Double> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addFloatArray(List<Float> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addByteVectorArray(List<byte[]> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }

    public MessageEncoder addMapArray(List<Map<String,String>> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }


    public MessageEncoder addStringArray(List<String> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add( pArray.size() );
            pArray.forEach(this::add);
        }
        return this;
    }



    public MessageEncoder addMessageArray(List<? extends MessageInterface> pArray) {
        if (pArray == null) {
            add(false);
        } else {
            add(true);
            add(pArray.size());
            pArray.forEach(this::add);
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("capacity: " + mBuffer.length + " position: " + mPosition + " remaining: " + (mBuffer.length - mPosition));

    }

}
