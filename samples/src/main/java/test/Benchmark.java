package test;

import com.hoddmimes.transform.MessageDecoder;
import com.hoddmimes.transform.MessageEncoder;
import generated.*;

public class Benchmark
{
    private static int WARMUP = 100000;
    private static int ITERATIONS = 5000000;
    public static void main(String[] args) {
        Benchmark b = new Benchmark();
        b.test();
    }

    private void test() {
        TestMessage tstmsg = CreateTestMessage.createMessage();

        // Test encoding
        MessageEncoder tEncoder = new MessageEncoder();
        for (int i = 0; i < WARMUP; i++) {
            tEncoder.reset();
            tstmsg.encode( tEncoder );
        }

        long tStartTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            tEncoder.reset();
            tstmsg.encode( tEncoder );
        }
        long execTime = System.nanoTime() - tStartTime;
        System.out.println("Encoding " + ITERATIONS + " iterations, average encoding time " + (execTime / ITERATIONS)  + " nano sec");


        // Test decode
        byte[] msgbytes = tstmsg.getBytes();
        MessageDecoder tDecoder = new MessageDecoder( msgbytes );
        for (int i = 0; i < WARMUP; i++) {
            tDecoder.flip();
            tstmsg.decode( tDecoder );
        }

        tStartTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            tDecoder.flip();
            tstmsg.decode( tDecoder );
        }
        execTime = System.nanoTime() - tStartTime;
        System.out.println("Decoding " + ITERATIONS + " iterations, average decode time " + (execTime / ITERATIONS)  + " nano sec");
    }
}
