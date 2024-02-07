import com.hoddmimes.transform.MessageDecoder;
import com.hoddmimes.transform.MessageEncoder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCodec
{
    enum TestConstant {TC1,TC2,TC3};
    public static void main(String[] args) {
        TestCodec t = new TestCodec();
        t.test();
    }

    private void test() {
        byte[] bytes = {1,2,3,4,5,6,7};
        Map<String,String> map = new HashMap<>();
        map.put("test-key","test-value");
        LocalDate localDate = LocalDate.of(2023, 05, 01);
        LocalDateTime localDateTime = LocalDateTime.of(2023, 05,01, 11,11,11);
        List<Boolean> booleanList = new ArrayList<>(Arrays.asList(true,false,true,false));
        List<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        List<byte[]> bytesList = new ArrayList<>();
        byte[] bv1 = {1,2,3,4,5,6};
        bytesList.add(bv1);
        byte[] bv2 = {2,3,4,5,6,7};
        bytesList.add(bv2);
        byte[] bv3 = {3,4,5,6,7,8};
        bytesList.add(bv3);

        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> m1 = new HashMap<>();
        m1.put("k00","v00");
        m1.put("k10","v10");
        m1.put("k11","v11");
        m1.put("k12","v12");
        mapList.add(m1);
        Map<String,String> m2 = new HashMap<>();
        m2.put("k20","v20");
        m2.put("k20","v20");
        m2.put("k21","v21");
        m2.put("k22","v22");
        mapList.add(m2);

        ByteBuffer bb = ByteBuffer.allocate(20);
        bb.order(ByteOrder.nativeOrder());

        MessageEncoder be = new MessageEncoder();

        be.add( true );
        be.add((byte) 1);
        be.add((short) 123 );
        be.add( 123456 );
        be.add( 123456789123L );
        be.add("test string");
        be.add( bytes );
        be.add( map );
        be.add( localDate );
        be.add( localDateTime );
        be.addBooleanArray( booleanList );
        be.addIntegerArray( intList );
        be.addByteVectorArray( bytesList );
        be.addMapArray( mapList );



        MessageDecoder bd = new MessageDecoder( be.getBytes());

        assertTrue( bd.readBoolean());
        assertEquals((byte) 1, bd.readByte());
        assertEquals( (short) 123, bd.readShort());
        assertEquals( 123456, bd.readInteger());
        assertEquals( 123456789123L, bd.readLong());
        assertEquals( "test string", bd.readString());
        byte[] xbytes = bd.readByteVector();
        for (int i = 0; i < xbytes.length; i++) {
            assertEquals( xbytes[i], bytes[i]);
        }

        Map<String,String> xmap = bd.readMap();
        assertEquals( xmap.get("test-key"), "test-value");

        LocalDate xLocalDate = bd.readLocalDate();
        assertEquals( xLocalDate.toString(), localDate.toString());

        LocalDateTime xLocalDateTime = bd.readLocalDateTime();
        assertEquals( xLocalDateTime.toString(), localDateTime.toString());

        List<Boolean> xbooleans = bd.readBooleanArray("array");
        for (int i = 0; i < xbooleans.size(); i++) {
            assertEquals( xbooleans.get(i), booleanList.get(i));
        }

        List<Integer> xintList = bd.readIntegerArray("array");
        for (int i = 0; i < xintList.size(); i++) {
            assertEquals( xintList.get(i), intList.get(i));
        }
    
        List<byte[]> xxbyteArrayList = bd.readByteVectorArray("array");
        for (int i = 0; i < xxbyteArrayList.size(); i++) {
            xbytes = xxbyteArrayList.get(i);
            byte[] ybytes = bytesList.get(i);
            for (int j = 0; j < xbytes.length; j++) {
                assertEquals(xbytes[j], ybytes[j]);
            }
        }

        List<Map<String,String>> xMapList = bd.readMapArray("array");
        for (int i = 0; i < xMapList.size(); i++) {
            xmap = xMapList.get(i);
            Map<String, String> ymap = mapList.get(i);
            assertEquals( xmap.get(String.format("k%d0", (i+1))), ymap.get(String.format("k%d0", (i+1))));
        }
    }
}
