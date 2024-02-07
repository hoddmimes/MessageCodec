
/*
 * Copyright (c)  Hoddmimes Solution AB 2021.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
            
package generated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.io.IOException;




    import org.bson.BsonType;
    import org.bson.Document;
    import org.bson.conversions.Bson;
    import com.mongodb.BasicDBObject;
    import org.bson.types.ObjectId;
    import com.hoddmimes.transform.MessageMongoInterface;
    import com.hoddmimes.transform.MongoDecoder;
    import com.hoddmimes.transform.MongoEncoder;


import com.hoddmimes.transform.MessageInterface;
import com.hoddmimes.transform.MessageEncoder;
import com.hoddmimes.transform.MessageDecoder;
import com.hoddmimes.transform.ListFactory;
import com.hoddmimes.transform.MessageAux;



            

            @SuppressWarnings({"WeakerAccess","unused","unchecked"})
            public class TestMessage extends TestMessageBase implements MessageInterface , MessageMongoInterface
            {
                public static final String NAME = "TestMessage";
                public static final int ID = (3 << 16) + 3;

            
                private String mMongoId = null;
                    private boolean mBooleanValue;
                    private byte mByteValue;
                    private short mShortValue;
                    private int mIntValue;
                    private long mLongValue;
                    private float mFloatValue;
                    private double mDoubleValue;
                    private String mStringValue;
                private CG1 mConstValue;
                    private byte[] mByteArray;
                    private Map<String,String> mMapValue;
                    private LocalDate mDateValue;
                    private LocalDateTime mDateTimeValue;
                    private TestSubMessage mMsgValue;
                    private List<Boolean> mBooleanList;
                    private List<Short> mShortList;
                    private List<Integer> mIntList;
                    private List<Long> mLongList;
                    private List<Float> mFloatList;
                    private List<Double> mDoubleList;
                    private List<String> mStringList;
                private List<CG1> mConstList;
                    private List<byte[]> mByteList;
                    private List<Map<String,String>> mMapList;
                    private List<LocalDate> mDateList;
                    private List<LocalDateTime> mDateTimeList;
                    private List<TestSubMessage> mMsgList;
               public TestMessage()
               {
                super();
               }

               // Constructor used for decoding root message
               public TestMessage(byte[] pBuffer ) {
                    MessageDecoder tDecoder = new MessageDecoder( pBuffer );
                    this.decode( tDecoder );
               }
    
            public String getMongoId() {
            return this.mMongoId;
            }

            public void setMongoId( String pMongoId ) {
            this.mMongoId = pMongoId;
            }
        
            public TestMessage setBooleanValue( boolean pBooleanValue ) {
            mBooleanValue = pBooleanValue;
            return this;
            }
            public boolean getBooleanValue() {
              return  mBooleanValue;
            }
        
            public TestMessage setByteValue( byte pByteValue ) {
            mByteValue = pByteValue;
            return this;
            }
            public byte getByteValue() {
              return  mByteValue;
            }
        
            public TestMessage setShortValue( short pShortValue ) {
            mShortValue = pShortValue;
            return this;
            }
            public short getShortValue() {
              return  mShortValue;
            }
        
            public TestMessage setIntValue( int pIntValue ) {
            mIntValue = pIntValue;
            return this;
            }
            public int getIntValue() {
              return  mIntValue;
            }
        
            public TestMessage setLongValue( long pLongValue ) {
            mLongValue = pLongValue;
            return this;
            }
            public long getLongValue() {
              return  mLongValue;
            }
        
            public TestMessage setFloatValue( float pFloatValue ) {
            mFloatValue = pFloatValue;
            return this;
            }
            public float getFloatValue() {
              return  mFloatValue;
            }
        
            public TestMessage setDoubleValue( double pDoubleValue ) {
            mDoubleValue = pDoubleValue;
            return this;
            }
            public double getDoubleValue() {
              return  mDoubleValue;
            }
        
            public TestMessage setStringValue( String pStringValue ) {
            mStringValue = pStringValue;
            return this;
            }
            public String getStringValue() {
              return  mStringValue;
            }
        
            public TestMessage setConstValue(CG1 pConstValue) {
            mConstValue = pConstValue;
            return this;
            }

            public CG1 getConstValue() {
            return  mConstValue;
            }
        
            public TestMessage setByteArray( byte[] pByteArray ) {
            mByteArray = pByteArray;
            return this;
            }
            public byte[] getByteArray() {
              return  mByteArray;
            }
        
            public TestMessage setMapValue( Map<String,String> pMapValue ) {
            mMapValue = pMapValue;
            return this;
            }
            public Map<String,String> getMapValue() {
              return  mMapValue;
            }
        
            public TestMessage setDateValue( LocalDate pDateValue ) {
            mDateValue = pDateValue;
            return this;
            }
            public LocalDate getDateValue() {
              return  mDateValue;
            }
        
            public TestMessage setDateTimeValue( LocalDateTime pDateTimeValue ) {
            mDateTimeValue = pDateTimeValue;
            return this;
            }
            public LocalDateTime getDateTimeValue() {
              return  mDateTimeValue;
            }
        

            public TestSubMessage getMsgValue() {
              return  mMsgValue;
            }

            public TestMessage setMsgValue(TestSubMessage pMsgValue) {
            mMsgValue = pMsgValue;
            return this;
            }

        
            public TestMessage setBooleanList(List<Boolean> pBooleanList ) {
            if ( pBooleanList == null) {
            mBooleanList = null;
            } else {
            mBooleanList = ListFactory.getList("array");
            mBooleanList.addAll( pBooleanList );
            }
            return this;
            }

            public TestMessage addBooleanListItem(boolean pBooleanList ) {
                if (mBooleanList == null) {
                    mBooleanList = ListFactory.getList("array");
                }
                mBooleanList.add( pBooleanList );
                return this;
            }

            public List<Boolean> getBooleanList()
            {
            return  mBooleanList;
            }
        
            public TestMessage setShortList(List<Short> pShortList ) {
            if ( pShortList == null) {
            mShortList = null;
            } else {
            mShortList = ListFactory.getList("array");
            mShortList.addAll( pShortList );
            }
            return this;
            }

            public TestMessage addShortListItem(short pShortList ) {
                if (mShortList == null) {
                    mShortList = ListFactory.getList("array");
                }
                mShortList.add( pShortList );
                return this;
            }

            public List<Short> getShortList()
            {
            return  mShortList;
            }
        
            public TestMessage setIntList(List<Integer> pIntList ) {
            if ( pIntList == null) {
            mIntList = null;
            } else {
            mIntList = ListFactory.getList("array");
            mIntList.addAll( pIntList );
            }
            return this;
            }

            public TestMessage addIntListItem(int pIntList ) {
                if (mIntList == null) {
                    mIntList = ListFactory.getList("array");
                }
                mIntList.add( pIntList );
                return this;
            }

            public List<Integer> getIntList()
            {
            return  mIntList;
            }
        
            public TestMessage setLongList(List<Long> pLongList ) {
            if ( pLongList == null) {
            mLongList = null;
            } else {
            mLongList = ListFactory.getList("array");
            mLongList.addAll( pLongList );
            }
            return this;
            }

            public TestMessage addLongListItem(long pLongList ) {
                if (mLongList == null) {
                    mLongList = ListFactory.getList("array");
                }
                mLongList.add( pLongList );
                return this;
            }

            public List<Long> getLongList()
            {
            return  mLongList;
            }
        
            public TestMessage setFloatList(List<Float> pFloatList ) {
            if ( pFloatList == null) {
            mFloatList = null;
            } else {
            mFloatList = ListFactory.getList("array");
            mFloatList.addAll( pFloatList );
            }
            return this;
            }

            public TestMessage addFloatListItem(float pFloatList ) {
                if (mFloatList == null) {
                    mFloatList = ListFactory.getList("array");
                }
                mFloatList.add( pFloatList );
                return this;
            }

            public List<Float> getFloatList()
            {
            return  mFloatList;
            }
        
            public TestMessage setDoubleList(List<Double> pDoubleList ) {
            if ( pDoubleList == null) {
            mDoubleList = null;
            } else {
            mDoubleList = ListFactory.getList("array");
            mDoubleList.addAll( pDoubleList );
            }
            return this;
            }

            public TestMessage addDoubleListItem(double pDoubleList ) {
                if (mDoubleList == null) {
                    mDoubleList = ListFactory.getList("array");
                }
                mDoubleList.add( pDoubleList );
                return this;
            }

            public List<Double> getDoubleList()
            {
            return  mDoubleList;
            }
        
            public TestMessage setStringList(List<String> pStringList ) {
            if ( pStringList == null) {
            mStringList = null;
            } else {
            mStringList = ListFactory.getList("array");
            mStringList.addAll( pStringList );
            }
            return this;
            }

            public TestMessage addStringListItem(String pStringList ) {
                if (mStringList == null) {
                    mStringList = ListFactory.getList("array");
                }
                mStringList.add( pStringList );
                return this;
            }

            public List<String> getStringList()
            {
            return  mStringList;
            }
        
            public List<CG1> getConstList() {
            return mConstList ;
            }
            public TestMessage setConstList(List<CG1>pConstList) {
            if (pConstList == null) {
            mConstList = null;
            } else {
            mConstList = ListFactory.getList("array");
            mConstList.addAll( pConstList );
            }
            return this;
            }



        
            public TestMessage setByteList(List<byte[]> pByteList ) {
            if ( pByteList == null) {
            mByteList = null;
            } else {
            mByteList = ListFactory.getList("array");
            mByteList.addAll( pByteList );
            }
            return this;
            }

            public TestMessage addByteListItem(byte[] pByteList ) {
                if (mByteList == null) {
                    mByteList = ListFactory.getList("array");
                }
                mByteList.add( pByteList );
                return this;
            }

            public List<byte[]> getByteList()
            {
            return  mByteList;
            }
        
            public TestMessage setMapList(List<Map<String,String>> pMapList ) {
            if ( pMapList == null) {
            mMapList = null;
            } else {
            mMapList = ListFactory.getList("array");
            mMapList.addAll( pMapList );
            }
            return this;
            }

            public TestMessage addMapListItem(Map<String,String> pMapList ) {
                if (mMapList == null) {
                    mMapList = ListFactory.getList("array");
                }
                mMapList.add( pMapList );
                return this;
            }

            public List<Map<String,String>> getMapList()
            {
            return  mMapList;
            }
        
            public TestMessage setDateList(List<LocalDate> pDateList ) {
            if ( pDateList == null) {
            mDateList = null;
            } else {
            mDateList = ListFactory.getList("array");
            mDateList.addAll( pDateList );
            }
            return this;
            }

            public TestMessage addDateListItem(LocalDate pDateList ) {
                if (mDateList == null) {
                    mDateList = ListFactory.getList("array");
                }
                mDateList.add( pDateList );
                return this;
            }

            public List<LocalDate> getDateList()
            {
            return  mDateList;
            }
        
            public TestMessage setDateTimeList(List<LocalDateTime> pDateTimeList ) {
            if ( pDateTimeList == null) {
            mDateTimeList = null;
            } else {
            mDateTimeList = ListFactory.getList("array");
            mDateTimeList.addAll( pDateTimeList );
            }
            return this;
            }

            public TestMessage addDateTimeListItem(LocalDateTime pDateTimeList ) {
                if (mDateTimeList == null) {
                    mDateTimeList = ListFactory.getList("array");
                }
                mDateTimeList.add( pDateTimeList );
                return this;
            }

            public List<LocalDateTime> getDateTimeList()
            {
            return  mDateTimeList;
            }
        
            public TestMessage setMsgList( List<TestSubMessage> pMsgList ) {
              if (pMsgList == null) {
                mMsgList = null;
                return this;
              }


            if ( mMsgList == null)
            mMsgList = ListFactory.getList("array");


            mMsgList .addAll( pMsgList );
            return this;
            }


            public TestMessage addMsgList( List<TestSubMessage> pMsgList ) {

            if ( mMsgList == null)
            mMsgList = ListFactory.getList("array");

            mMsgList .addAll( pMsgList );
            return this;
            }

            public TestMessage addMsgList( TestSubMessage pMsgList ) {

            if ( pMsgList == null) {
            return this; // Not supporting null in vectors, well design issue
            }

            if ( mMsgList == null) {
            mMsgList = ListFactory.getList("array");
            }

            mMsgList.add( pMsgList );
            return this;
            }


            public List<TestSubMessage> getMsgList() {
                return mMsgList;
            }

        
        public String getMessageName() {
            return "TestMessage";
        }
    

        @Override
        public byte[] getBytes() {
            MessageEncoder tEncoder = new MessageEncoder();
            this.encode( tEncoder );
            return tEncoder.getBytes();
        }

        @Override
        public int getMessageId() {
            return TestMessage.ID;
        }

        
        public void encode( MessageEncoder pEncoder) {

        
            MessageEncoder tEncoder = pEncoder;
            pEncoder.add( TestMessage.ID );
            super.encode( tEncoder );
        
            //Encode Attribute: mBooleanValue Type: boolean List: false
            tEncoder.add( mBooleanValue );
        
            //Encode Attribute: mByteValue Type: byte List: false
            tEncoder.add( mByteValue );
        
            //Encode Attribute: mShortValue Type: short List: false
            tEncoder.add( mShortValue );
        
            //Encode Attribute: mIntValue Type: int List: false
            tEncoder.add( mIntValue );
        
            //Encode Attribute: mLongValue Type: long List: false
            tEncoder.add( mLongValue );
        
            //Encode Attribute: mFloatValue Type: float List: false
            tEncoder.add( mFloatValue );
        
            //Encode Attribute: mDoubleValue Type: double List: false
            tEncoder.add( mDoubleValue );
        
            //Encode Attribute: mStringValue Type: String List: false
            tEncoder.add( mStringValue );
        
            //Encode Attribute: mConstValue Type:  List: false
            if (mConstValue == null) {
                tEncoder.add(false);
            } else {
                tEncoder.add(true);
                tEncoder.add( mConstValue.ordinal());
            }
        
            //Encode Attribute: mByteArray Type: byte[] List: false
            tEncoder.add( mByteArray );
        
            //Encode Attribute: mMapValue Type: Map List: false
            tEncoder.add( mMapValue );
        
            //Encode Attribute: mDateValue Type: LocalDate List: false
            tEncoder.add( mDateValue );
        
            //Encode Attribute: mDateTimeValue Type: LocalDateTime List: false
            tEncoder.add( mDateTimeValue );
        
            //Encode Attribute: mMsgValue Type: TestSubMessage List: false
            tEncoder.add( mMsgValue );
        
            //Encode Attribute: mBooleanList Type: boolean List: true
            tEncoder.addBooleanArray( mBooleanList );
        
            //Encode Attribute: mShortList Type: short List: true
            tEncoder.addShortArray( mShortList );
        
            //Encode Attribute: mIntList Type: int List: true
            tEncoder.addIntegerArray( mIntList );
        
            //Encode Attribute: mLongList Type: long List: true
            tEncoder.addLongArray( mLongList );
        
            //Encode Attribute: mFloatList Type: float List: true
            tEncoder.addFloatArray( mFloatList );
        
            //Encode Attribute: mDoubleList Type: double List: true
            tEncoder.addDoubleArray( mDoubleList );
        
            //Encode Attribute: mStringList Type: String List: true
            tEncoder.addStringArray( mStringList );
        
            //Encode Attribute: mConstList Type:  List: true
            if (mConstList == null) {
              tEncoder.add(false);
            } else {
              tEncoder.add(true);
              tEncoder.add( mConstList.size());
              for( int i = 0; i < mConstList.size(); i++ ) {
                tEncoder.add( mConstList.get(i).ordinal());
              }
            }
        
            //Encode Attribute: mByteList Type: byte[] List: true
            tEncoder.addByteVectorArray( mByteList );
        
            //Encode Attribute: mMapList Type: Map List: true
            tEncoder.addMapArray( mMapList );
        
            //Encode Attribute: mDateList Type: LocalDate List: true
            tEncoder.addLocalDateArray( mDateList );
        
            //Encode Attribute: mDateTimeList Type: LocalDateTime List: true
            tEncoder.addLocalDateTimeArray( mDateTimeList );
        
            //Encode Attribute: mMsgList Type: TestSubMessage List: true
            tEncoder.addMessageArray( mMsgList );
        
        }

        
        public void decode( MessageDecoder pDecoder) {

        
            MessageDecoder tDecoder = pDecoder;
            tDecoder.readInteger(); // Root messages has the message ID as the first 4 bytes
        super.decode( tDecoder );
            //Decode Attribute: mBooleanValue Type:boolean List: false
            mBooleanValue = tDecoder.readBoolean();
        
            //Decode Attribute: mByteValue Type:byte List: false
            mByteValue = tDecoder.readByte();
        
            //Decode Attribute: mShortValue Type:short List: false
            mShortValue = tDecoder.readShort();
        
            //Decode Attribute: mIntValue Type:int List: false
            mIntValue = tDecoder.readInteger();
        
            //Decode Attribute: mLongValue Type:long List: false
            mLongValue = tDecoder.readLong();
        
            //Decode Attribute: mFloatValue Type:float List: false
            mFloatValue = tDecoder.readFloat();
        
            //Decode Attribute: mDoubleValue Type:double List: false
            mDoubleValue = tDecoder.readDouble();
        
            //Decode Attribute: mStringValue Type:String List: false
            mStringValue = tDecoder.readString();
        
            //Decode Attribute: mConstValue Type: List: false
            if (!tDecoder.readBoolean()) {
            mConstValue = null;
            } else {
               mConstValue = (CG1) CG1.get(  tDecoder.readInteger());
            }
        
            //Decode Attribute: mByteArray Type:byte[] List: false
            mByteArray = tDecoder.readByteVector();
        
            //Decode Attribute: mMapValue Type:Map List: false
            mMapValue = tDecoder.readMap();
        
            //Decode Attribute: mDateValue Type:LocalDate List: false
            mDateValue = tDecoder.readLocalDate();
        
            //Decode Attribute: mDateTimeValue Type:LocalDateTime List: false
            mDateTimeValue = tDecoder.readLocalDateTime();
        
            //Decode Attribute: mMsgValue Type:TestSubMessage List: false
            mMsgValue = (TestSubMessage) tDecoder.readMessage( TestSubMessage.class );
        
            //Decode Attribute: mBooleanList Type:boolean List: true
            mBooleanList = tDecoder.readBooleanArray( "array");
        
            //Decode Attribute: mShortList Type:short List: true
            mShortList = tDecoder.readShortArray( "array");
        
            //Decode Attribute: mIntList Type:int List: true
            mIntList = tDecoder.readIntegerArray( "array");
        
            //Decode Attribute: mLongList Type:long List: true
            mLongList = tDecoder.readLongArray( "array");
        
            //Decode Attribute: mFloatList Type:float List: true
            mFloatList = tDecoder.readFloatArray( "array");
        
            //Decode Attribute: mDoubleList Type:double List: true
            mDoubleList = tDecoder.readDoubleArray( "array");
        
            //Decode Attribute: mStringList Type:String List: true
            mStringList = tDecoder.readStringArray( "array");
        
            //Decode Attribute: mConstList Type: List: true
            if (!tDecoder.readBoolean()) {
               mConstList = null;
            } else {
                    mConstList = ListFactory.getList("array");
                    int tSize = tDecoder.readInteger();
                    for( int i = 0; i < tSize; i++ ) {
                        mConstList.add( CG1.get(tDecoder.readInteger()));
                    }
            }
        
            //Decode Attribute: mByteList Type:byte[] List: true
            mByteList = tDecoder.readByteVectorArray( "array");
        
            //Decode Attribute: mMapList Type:Map List: true
            mMapList = tDecoder.readMapArray( "array");
        
            //Decode Attribute: mDateList Type:LocalDate List: true
            mDateList = tDecoder.readLocalDateArray( "array");
        
            //Decode Attribute: mDateTimeList Type:LocalDateTime List: true
            mDateTimeList = tDecoder.readLocalDateTimeArray( "array");
        
            //Decode Attribute: mMsgList Type:TestSubMessage List: true
            mMsgList = (List<TestSubMessage>) tDecoder.readMessageArray( TestSubMessage.class );
        

        }
    
        public Document getMongoDocument() {
            MongoEncoder tEncoder = new MongoEncoder();
            super.mongoEncode( tEncoder );
            mongoEncode( tEncoder );
            return tEncoder.getDoc();
        }

     protected void mongoEncode(  MongoEncoder pEncoder ) {
        
                pEncoder.add("booleanValue",  mBooleanValue );
                pEncoder.add("byteValue",  mByteValue );
                pEncoder.add("shortValue",  mShortValue );
                pEncoder.add("intValue",  mIntValue );
                pEncoder.add("longValue",  mLongValue );
                pEncoder.add("floatValue",  mFloatValue );
                pEncoder.add("doubleValue",  mDoubleValue );
                pEncoder.add("stringValue",  mStringValue );
                   pEncoder.add( "constValue", mConstValue ); 
                pEncoder.add("byteArray",  mByteArray );
                pEncoder.add("mapValue",  mMapValue );
                pEncoder.add("dateValue",  mDateValue );
                pEncoder.add("dateTimeValue",  mDateTimeValue );
            pEncoder.add("msgValue",   mMsgValue );
                pEncoder.addBooleanArray( "booleanList", mBooleanList );
                pEncoder.addShortArray( "shortList", mShortList );
                pEncoder.addIntegerArray( "intList", mIntList );
                pEncoder.addLongArray( "longList", mLongList );
                pEncoder.addFloatArray( "floatList", mFloatList );
                pEncoder.addDoubleArray( "doubleList", mDoubleList );
                pEncoder.addStringArray( "stringList", mStringList );
                   pEncoder.addConstArray("constList", mConstList );
                pEncoder.addByteVectorArray( "byteList", mByteList );
                pEncoder.addMapArray( "mapList", mMapList );
                pEncoder.addLocalDateArray( "dateList", mDateList );
                pEncoder.addLocalDateTimeArray( "dateTimeList", mDateTimeList );
            pEncoder.addMessageArray("msgList",  mMsgList );
    }
    
        public void decodeMongoDocument( Document pDoc ) {

            Document tDoc = null;
            List<Document> tDocLst = null;
            MongoDecoder tDecoder = new MongoDecoder( pDoc );

            super.decodeMongoDocument( pDoc );
            ObjectId _tId = pDoc.get("_id", ObjectId.class);
            this.mMongoId = _tId.toString();
            
           mBooleanValue = tDecoder.readBoolean("booleanValue");
        
           mByteValue = tDecoder.readByte("byteValue");
        
           mShortValue = tDecoder.readShort("shortValue");
        
           mIntValue = tDecoder.readInteger("intValue");
        
           mLongValue = tDecoder.readLong("longValue");
        
           mFloatValue = tDecoder.readFloat("floatValue");
        
           mDoubleValue = tDecoder.readDouble("doubleValue");
        
           mStringValue = tDecoder.readString("stringValue");
        
                    mConstValue = (CG1) tDecoder.readConstant("constValue", CG1.class); 
           mByteArray = tDecoder.readByteVector("byteArray");
        
           mMapValue = tDecoder.readMap("mapValue");
        
           mDateValue = tDecoder.readLocalDate("dateValue");
        
           mDateTimeValue = tDecoder.readLocalDateTime("dateTimeValue");
        
            tDoc = (Document) tDecoder.readMessage("msgValue");
            if (tDoc == null) {
              mMsgValue = null;
            } else {
               mMsgValue = new TestSubMessage();
               mMsgValue.decodeMongoDocument( tDoc );
            } 
           mBooleanList = tDecoder.readBooleanArray("booleanList", "array");
        
           mShortList = tDecoder.readShortArray("shortList", "array");
        
           mIntList = tDecoder.readIntegerArray("intList", "array");
        
           mLongList = tDecoder.readLongArray("longList", "array");
        
           mFloatList = tDecoder.readFloatArray("floatList", "array");
        
           mDoubleList = tDecoder.readDoubleArray("doubleList", "array");
        
           mStringList = tDecoder.readStringArray("stringList", "array");
        
                    mConstList = (List<CG1>)tDecoder.readConstArray("constList", "array", CG1.class); 
           mByteList = tDecoder.readByteVectorArray("byteList", "array");
        
           mMapList = tDecoder.readMapArray("mapList", "array");
        
           mDateList = tDecoder.readLocalDateArray("dateList", "array");
        
           mDateTimeList = tDecoder.readLocalDateTimeArray("dateTimeList", "array");
        
                tDocLst = (List<Document>) tDecoder.readMessageArray("msgList", "array" );
                if (tDocLst == null) {
                   mMsgList = null;
                } else {
                   mMsgList = ListFactory.getList("array");
                   for( Document doc :  tDocLst ) {
                    TestSubMessage  m = new TestSubMessage();
                    m.decodeMongoDocument( doc );
                    mMsgList.add(m);
                }
            } 
        } // End decodeMongoDocument
    

        String blanks(int pCount) {
        if (pCount == 0) {
        return null;
        }
        String tBlanks = "                                                                                       ";
        return tBlanks.substring( 0,pCount );
        }


        public String toString() {
        return this.toString(0);
        }

        public String toString( int pCount ) {
        return toString( pCount, false );
        }

        public String toString( int pCount, boolean pExtention ) {
        StringBuilder tSB = new StringBuilder (512);
        if (pCount > 0) {
        tSB.append( blanks( pCount ));
        }

        if (pExtention)  {
        tSB.append("<Extending Message: " + "\"TestMessage\"  Id: " + Integer.toHexString(getMessageId()) + ">\n");
        } else {
        tSB.append("Message: " + "\"TestMessage\"  Id: " +  Integer.toHexString(getMessageId())  + "\n");
        }

        tSB.append( super.toString( pCount + 3, true ));
        // FormatNative name: mBooleanValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mBooleanValue: ");
                tSB.append( String.valueOf( mBooleanValue ));
                tSB.append("\n"); 
        // FormatNative name: mByteValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mByteValue: ");
                tSB.append( String.valueOf( mByteValue ));
                tSB.append("\n"); 
        // FormatNative name: mShortValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mShortValue: ");
                tSB.append( String.valueOf( mShortValue ));
                tSB.append("\n"); 
        // FormatNative name: mIntValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mIntValue: ");
                tSB.append( String.valueOf( mIntValue ));
                tSB.append("\n"); 
        // FormatNative name: mLongValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mLongValue: ");
                tSB.append( String.valueOf( mLongValue ));
                tSB.append("\n"); 
        // FormatNative name: mFloatValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mFloatValue: ");
                tSB.append( String.valueOf( mFloatValue ));
                tSB.append("\n"); 
        // FormatNative name: mDoubleValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mDoubleValue: ");
                tSB.append( String.valueOf( mDoubleValue ));
                tSB.append("\n"); 
        // FormatNative name: mStringValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mStringValue: ");
                tSB.append( String.valueOf( mStringValue ));
                tSB.append("\n"); 
        // FormatConstant name: mConstValue array-type: 
            tSB.append( blanks( pCount + 2 ) + "mConstValue: ");
            if (mConstValue == null) {
                tSB.append("<null>");
            } else {
                tSB.append(  mConstValue.toString() );
            }

            tSB.append("\n");
        
        // FormatNative name: mByteArray array-type: 
                tSB.append( blanks( pCount + 2 ) + "mByteArray: ");
                tSB.append( MessageAux.format( mByteArray));
                tSB.append("\n");
        // FormatNative name: mMapValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mMapValue: ");
                tSB.append( String.valueOf( mMapValue ));
                tSB.append("\n"); 
        // FormatNative name: mDateValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mDateValue: ");
                tSB.append( String.valueOf( mDateValue ));
                tSB.append("\n"); 
        // FormatNative name: mDateTimeValue array-type: 
                tSB.append( blanks( pCount + 2 ) + "mDateTimeValue: ");
                tSB.append( String.valueOf( mDateTimeValue ));
                tSB.append("\n"); 
        // FormatMessage name: mMsgValue array-type: 
            tSB.append( blanks( pCount + 2 ) + "mMsgValue: ");
            if ( mMsgValue == null) {
            tSB.append("<null>");
            } else {
            tSB.append( mMsgValue.toString( pCount + 4 ) );
            tSB.append("\n");
            } 
        // FormatNative name: mBooleanList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mBooleanList: ");
            tSB.append( MessageAux.addBooleanArray( pCount + 2, mBooleanList));
            tSB.append("\n");
        // FormatNative name: mShortList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mShortList: ");
            tSB.append( MessageAux.addShortArray( pCount + 2, mShortList));
            tSB.append("\n");
        // FormatNative name: mIntList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mIntList: ");
            tSB.append( MessageAux.addIntegerArray( pCount + 2, mIntList));
            tSB.append("\n");
        // FormatNative name: mLongList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mLongList: ");
            tSB.append( MessageAux.addLongArray( pCount + 2, mLongList));
            tSB.append("\n");
        // FormatNative name: mFloatList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mFloatList: ");
            tSB.append( MessageAux.addFloatArray( pCount + 2, mFloatList));
            tSB.append("\n");
        // FormatNative name: mDoubleList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mDoubleList: ");
            tSB.append( MessageAux.addDoubleArray( pCount + 2, mDoubleList));
            tSB.append("\n");
        // FormatNative name: mStringList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mStringList: ");
            tSB.append( MessageAux.addStringArray( pCount + 2, mStringList));
            tSB.append("\n");
        // FormatConstant name: mConstList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mConstList[]: ");
            if ( mConstList == null) {
            tSB.append("<null>");
            } else {
            int tSize = mConstList.size();
            for( int i = 0; i < tSize; i++ ) {
            tSB.append( mConstList.get( i ).toString() + " ");
            }
            }
            tSB.append("\n");
        
        // FormatNative name: mByteList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mByteList: ");
            tSB.append( MessageAux.addByteVectorArray( pCount + 2, mByteList));
            tSB.append("\n");
        // FormatNative name: mMapList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mMapList: ");
            tSB.append( MessageAux.addMapArray( pCount + 2, mMapList));
            tSB.append("\n");
        // FormatNative name: mDateList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mDateList: ");
            tSB.append( MessageAux.addLocalDateArray( pCount + 2, mDateList));
            tSB.append("\n");
        // FormatNative name: mDateTimeList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mDateTimeList: ");
            tSB.append( MessageAux.addLocalDateTimeArray( pCount + 2, mDateTimeList));
            tSB.append("\n");
        // FormatMessage name: mMsgList array-type: array
            tSB.append( blanks( pCount + 2 ) + "mMsgList[]: ");
            if ( mMsgList == null) {
            tSB.append("<null>");
            } else {
            tSB.append("\n");
            int tSize = mMsgList.size();
            for( int i = 0; i < tSize; i++ ) {
            TestSubMessage tMsg = (TestSubMessage) mMsgList.get( i );
            tSB.append(  tMsg.toString( pCount + 4 ) );
            }
            } 
        return tSB.toString();
        }
    

        public static  Builder getTestMessageBuilder() {
            return new TestMessage.Builder();
        }


        public static class  Builder {
          private TestMessage mInstance;

          private Builder () {
            mInstance = new TestMessage();
          }

        
                        public Builder setBooleanValue( boolean pValue ) {
                        mInstance.setBooleanValue( pValue );
                        return this;
                    }
                
                        public Builder setByteValue( byte pValue ) {
                        mInstance.setByteValue( pValue );
                        return this;
                    }
                
                        public Builder setShortValue( short pValue ) {
                        mInstance.setShortValue( pValue );
                        return this;
                    }
                
                        public Builder setIntValue( int pValue ) {
                        mInstance.setIntValue( pValue );
                        return this;
                    }
                
                        public Builder setLongValue( long pValue ) {
                        mInstance.setLongValue( pValue );
                        return this;
                    }
                
                        public Builder setFloatValue( float pValue ) {
                        mInstance.setFloatValue( pValue );
                        return this;
                    }
                
                        public Builder setDoubleValue( double pValue ) {
                        mInstance.setDoubleValue( pValue );
                        return this;
                    }
                
                        public Builder setStringValue( String pValue ) {
                        mInstance.setStringValue( pValue );
                        return this;
                    }
                
                public Builder setConstValue( CG1 pValue ) {
                    mInstance.setConstValue( pValue );
                    return this;
                }
            
                        public Builder setByteArray( byte[] pValue ) {
                        mInstance.setByteArray( pValue );
                        return this;
                    }
                
                        public Builder setMapValue( Map<String,String> pValue ) {
                        mInstance.setMapValue( pValue );
                        return this;
                    }
                
                        public Builder setDateValue( LocalDate pValue ) {
                        mInstance.setDateValue( pValue );
                        return this;
                    }
                
                        public Builder setDateTimeValue( LocalDateTime pValue ) {
                        mInstance.setDateTimeValue( pValue );
                        return this;
                    }
                
                    public Builder setMsgValue( TestSubMessage pValue )  {
                        mInstance.setMsgValue( pValue );
                        return this;
                    }
                
                        public Builder setBooleanList( List<Boolean> pValue ) {
                        mInstance.setBooleanList( pValue );
                        return this;
                    }
                
                        public Builder setShortList( List<Short> pValue ) {
                        mInstance.setShortList( pValue );
                        return this;
                    }
                
                        public Builder setIntList( List<Integer> pValue ) {
                        mInstance.setIntList( pValue );
                        return this;
                    }
                
                        public Builder setLongList( List<Long> pValue ) {
                        mInstance.setLongList( pValue );
                        return this;
                    }
                
                        public Builder setFloatList( List<Float> pValue ) {
                        mInstance.setFloatList( pValue );
                        return this;
                    }
                
                        public Builder setDoubleList( List<Double> pValue ) {
                        mInstance.setDoubleList( pValue );
                        return this;
                    }
                
                        public Builder setStringList( List<String> pValue ) {
                        mInstance.setStringList( pValue );
                        return this;
                    }
                
                public Builder setConstList( List<CG1> pValue ) {
                    mInstance.setConstList( pValue );
                    return this;
                }
            
                        public Builder setByteList( List<byte[]> pValue ) {
                        mInstance.setByteList( pValue );
                        return this;
                    }
                
                        public Builder setMapList( List<Map<String,String>> pValue ) {
                        mInstance.setMapList( pValue );
                        return this;
                    }
                
                        public Builder setDateList( List<LocalDate> pValue ) {
                        mInstance.setDateList( pValue );
                        return this;
                    }
                
                        public Builder setDateTimeList( List<LocalDateTime> pValue ) {
                        mInstance.setDateTimeList( pValue );
                        return this;
                    }
                
                    public Builder setMsgList( List<TestSubMessage> pValue )  {
                        mInstance.setMsgList( pValue );
                        return this;
                    }
                
                        public Builder setBaseString( String pValue ) {
                        mInstance.setBaseString( pValue );
                        return this;
                    }
                
                        public Builder setBaseInt( int pValue ) {
                        mInstance.setBaseInt( pValue );
                        return this;
                    }
                

        public TestMessage build() {
            return mInstance;
        }

        }
    
            }
            
        /**
            Possible native attributes
            o "boolean" mapped to JSON "Boolean"
            o "byte" mapped to JSON "Integer"
            o "char" mapped to JSON "Integer"
            o "short" mapped to JSON "Integer"
            o "int" mapped to JSON "Integer"
            o "long" mapped to JSON "Integer"
            o "double" mapped to JSON "Numeric"
            o "String" mapped to JSON "String"
            o "byte[]" mapped to JSON "String" (Base64 string)


            An attribute could also be an "constant" i.e. having the property "constantGroup", should then refer to an defined /Constang/Group
             conastants are mapped to JSON strings,


            If the type is not any of the types below it will be refer to an other structure / object

        **/

    