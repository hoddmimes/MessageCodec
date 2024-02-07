import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Test
{
    public static void main(String[] args) {
        Test t = new Test();
        t.test();
    }

    private void test() {
        ByteBuffer bb = ByteBuffer.allocate(20);
        bb.order(ByteOrder.nativeOrder());

        bb.putShort( (short) 0x7f01);
        System.out.println( bb.array());

    }
}
