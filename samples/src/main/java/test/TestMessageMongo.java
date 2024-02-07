package test;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;
import generated.*;
import static junit.framework.TestCase.assertEquals;


public class TestMessageMongo
{

    private MongoAux     dbAux;
    private MongoCollection  tstMsgDbCollection;


    private MongoCollection mTestCollection;
    public static void main(String[] args) {
        TestMessageMongo mt = new TestMessageMongo();
        mt.test();
    }


    private void test() {
        connectToDB( "localhost","Test", 27017 );
        TestMessage tstmsg = CreateTestMessage.createMessage();

        tstMsgDbCollection.drop();
        Document tstmsgDoc = tstmsg.getMongoDocument();
        dbAux.insertTestMessage( tstmsg );


        List<TestMessage> dbMsgs = dbAux.findTestMessageByIntValue(1007);
        assertEquals( 1, dbMsgs.size());
        TestMessage tstMsgX = dbMsgs.get(0);

        byte[] msgBytesX = tstMsgX.getBytes();
        byte[] msgBytes = tstmsg.getBytes();

        // Test Byte compability
        assertEquals( msgBytesX.length, msgBytes.length);
        for (int i = 0; i < msgBytes.length; i++) {
            assertEquals( String.format("Not equal at %d", i), msgBytes[i], msgBytesX[i] );
        }

        // Test string compability
        assertEquals( tstmsg.toString(), tstMsgX.toString());
        System.out.println("test message, mongo id: " + tstMsgX.getMongoId());


    }

    private void connectToDB( String pDbHost, String pDbName, int pDbPort ) {
        try {
           dbAux = new MongoAux("test","localhost", 27017);
           dbAux.connectToDatabase();
           tstMsgDbCollection = dbAux.getTestCollection();

        }
        catch(Exception e ) {
            e.printStackTrace();
        }
    }
}
