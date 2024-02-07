package test;

import generated.CG1;
import generated.TestMessage;
import generated.TestSubMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class TestMessageCoding
{
    public static void main(String[] args) {
        TestMessageCoding tmc = new TestMessageCoding();
        tmc.test();
    }

    private void test() {
        TestMessage tstmsg = createTestMessage();

        // Test to encode test message
        byte[] msgBytes = tstmsg.getBytes();

        // Test to decode test message bytes
        TestMessage tstmsgX = new TestMessage( msgBytes );
        // Test to print decoded message
        //System.out.println( tstmsgX );
        byte[] msgBytesX = tstmsgX.getBytes();

        // Test Byte compability
        assertEquals( msgBytesX.length, msgBytes.length);
        for (int i = 0; i < msgBytes.length; i++) {
            assertEquals( String.format("Not equal at %d", i), msgBytes[i], msgBytesX[i] );
        }

        // Test string compability
        assertEquals( msgBytes.toString(), msgBytes.toString());
        // Test to print test message
        System.out.println( tstmsg );

    }

    private TestMessage createTestMessage() {
        TestMessage tstmsg = new TestMessage();
        List<Boolean> booleanList = new ArrayList<>(Arrays.asList(true,false,true,false));
        tstmsg.setBooleanList( booleanList );
        tstmsg.setBooleanValue( true );
        tstmsg.setByteList( createByteLists());
        tstmsg.setByteValue((byte) 1);
        tstmsg.setConstValue( CG1.X2 );
        List<CG1> constList = new ArrayList<>(Arrays.asList(CG1.X1, CG1.X3, CG1.X5));
        tstmsg.setConstList(constList);
        tstmsg.setDateList( createLocalDateList());
        tstmsg.setDateTimeList( createLocalDateTimeList());
        List<Double> doubleList = new ArrayList<>(Arrays.asList(11.11d,22.22d,33.33d));
        tstmsg.setDoubleList(doubleList);
        tstmsg.setDateValue( LocalDate.of(2023,10,10));
        tstmsg.setDateTimeValue(LocalDateTime.of(2023,10,10,1,2,3 ));
        List<Float> floatList = new ArrayList<>(Arrays.asList(1.1f,2.2f,3.3f));
        tstmsg.setFloatList(floatList);
        List<Integer> intList = new ArrayList<>(Arrays.asList(1001,2002,3003));
        tstmsg.setIntList(intList);
        tstmsg.setDoubleValue(12345.12345d);
        tstmsg.setFloatValue(123.123f);
        tstmsg.setIntValue(1007);
        tstmsg.setLongValue(123456789123L);
        List<Long> longList = new ArrayList<>(Arrays.asList(1111111111L,2222222222L,3333333333L));
        tstmsg.setLongList( longList );
        tstmsg.setMapValue( createMap(1, 1));
        tstmsg.setMapList( createMapList());
        tstmsg.setShortValue((short) 123);
        List<Short> shortList = new ArrayList<>(Arrays.asList((short) 123, (short) 234, (short) 345));
        tstmsg.setShortList(shortList);
        tstmsg.setStringValue("test-string");
        List<String> stringList = new ArrayList<>(Arrays.asList("test-string-1","test-string-2","test-string-3"));
        tstmsg.setStringList( stringList);
        tstmsg.setBaseInt(-12345);
        tstmsg.setBaseString("base-string");
        tstmsg.setMsgValue( createSubMsg(1));
        tstmsg.setMsgList( createSubMsgList());
        return tstmsg;
    }

    private List<TestSubMessage> createSubMsgList() {
        List<TestSubMessage> ml = new ArrayList<>();
        for (int i = 2; i <= 6; i++) {
            ml.add( createSubMsg(i));
        }
        return ml;
    }
    private TestSubMessage createSubMsg(int pIndex) {
        TestSubMessage tsm = new TestSubMessage();
        tsm.setSubIntValue( pIndex);
        tsm.setSubStrValue( String.format("submsg-%d", pIndex));
        for (int i = pIndex; i <= pIndex + 5 ; i++) {
            tsm.addSubIntArrayItem( i );
            tsm.addSubStringArrayItem(String.format("submsg-%d", (100 + pIndex)));
        }
        return tsm;
    }

    List<Map<String,String>> createMapList() {
        List<Map<String,String>> ml = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            ml.add( createMap( i, 2+1));
        }
        return ml;
    }

    private Map<String,String> createMap(int pIndex, int tSize) {
        Map<String,String> m = new HashMap<>();
        for (int i = pIndex; i <= tSize + pIndex; i++) {
            m.put(String.format("key%02d", i), String.format("value%02d", i));
        }
        return m;
    }

    private List<LocalDate> createLocalDateList() {
        List<LocalDate> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tList.add( LocalDate.of(2023, 05,(i+1)));
        }
        return tList;
    }
    private List<LocalDateTime> createLocalDateTimeList() {
        List<LocalDateTime> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tList.add( LocalDateTime.of(2023, 05,(i+1), i+1, i+1,i+1));
        }
        return tList;
    }

    private List<byte[]> createByteLists() {
        List<byte[]> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            byte[] bv =  new byte[5];
            for (int j = 0; j < bv.length; j++) {
                bv[j] = (byte) (i+1+j);
            }
            tList.add( bv );
        }
        return tList;
    }
}
