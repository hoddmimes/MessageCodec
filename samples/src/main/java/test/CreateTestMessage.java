package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import generated.*;

public class CreateTestMessage
{
    public static TestMessage createMessage() {
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

    private static List<TestSubMessage> createSubMsgList() {
        List<TestSubMessage> ml = new ArrayList<>();
        for (int i = 2; i <= 6; i++) {
            ml.add( createSubMsg(i));
        }
        return ml;
    }
    private static TestSubMessage createSubMsg(int pIndex) {
        TestSubMessage tsm = new TestSubMessage();
        tsm.setSubIntValue( pIndex);
        tsm.setSubStrValue( String.format("submsg-%d", pIndex));
        for (int i = pIndex; i <= pIndex + 5 ; i++) {
            tsm.addSubIntArrayItem( i );
            tsm.addSubStringArrayItem(String.format("submsg-%d", (100 + pIndex)));
        }
        return tsm;
    }

    private static List<Map<String,String>> createMapList() {
        List<Map<String,String>> ml = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            ml.add( createMap( i, 2+1));
        }
        return ml;
    }

    private static  Map<String,String> createMap(int pIndex, int tSize) {
        Map<String,String> m = new HashMap<>();
        for (int i = pIndex; i <= tSize + pIndex; i++) {
            m.put(String.format("key%02d", i), String.format("value%02d", i));
        }
        return m;
    }

    private static List<LocalDate> createLocalDateList() {
        List<LocalDate> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tList.add( LocalDate.of(2023, 05,(i+1)));
        }
        return tList;
    }
    private static List<LocalDateTime> createLocalDateTimeList() {
        List<LocalDateTime> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tList.add( LocalDateTime.of(2023, 05,(i+1), i+1, i+1,i+1));
        }
        return tList;
    }

    private static List<byte[]> createByteLists() {
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
