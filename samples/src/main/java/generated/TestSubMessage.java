
/*
 * Copyright (c)  Pär Bertilsson 2024.
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

import com.hoddmimes.transform.*;
import org.bson.Document;

import java.util.List;


@SuppressWarnings({"WeakerAccess", "unused", "unchecked"})
public class TestSubMessage implements MessageInterface, MessageMongoInterface {
    public static final String NAME = "TestSubMessage";
    public static final int ID = (3 << 16) + 2;


    private int mSubIntValue;
    private String mSubStrValue;
    private List<String> mSubStringArray;
    private List<Integer> mSubIntArray;

    public TestSubMessage() {

    }

    // Constructor used for decoding root message
    public TestSubMessage(byte[] pBuffer) {
        MessageDecoder tDecoder = new MessageDecoder(pBuffer);
        this.decode(tDecoder);
    }

    public static Builder getTestSubMessageBuilder() {
        return new TestSubMessage.Builder();
    }

    public int getSubIntValue() {
        return mSubIntValue;
    }

    public TestSubMessage setSubIntValue(int pSubIntValue) {
        mSubIntValue = pSubIntValue;
        return this;
    }

    public String getSubStrValue() {
        return mSubStrValue;
    }

    public TestSubMessage setSubStrValue(String pSubStrValue) {
        mSubStrValue = pSubStrValue;
        return this;
    }

    public TestSubMessage addSubStringArrayItem(String pSubStringArray) {
        if (mSubStringArray == null) {
            mSubStringArray = ListFactory.getList("array");
        }
        mSubStringArray.add(pSubStringArray);
        return this;
    }

    public List<String> getSubStringArray() {
        return mSubStringArray;
    }

    public TestSubMessage setSubStringArray(List<String> pSubStringArray) {
        if (pSubStringArray == null) {
            mSubStringArray = null;
        } else {
            mSubStringArray = ListFactory.getList("array");
            mSubStringArray.addAll(pSubStringArray);
        }
        return this;
    }

    public TestSubMessage addSubIntArrayItem(int pSubIntArray) {
        if (mSubIntArray == null) {
            mSubIntArray = ListFactory.getList("array");
        }
        mSubIntArray.add(pSubIntArray);
        return this;
    }

    public List<Integer> getSubIntArray() {
        return mSubIntArray;
    }

    public TestSubMessage setSubIntArray(List<Integer> pSubIntArray) {
        if (pSubIntArray == null) {
            mSubIntArray = null;
        } else {
            mSubIntArray = ListFactory.getList("array");
            mSubIntArray.addAll(pSubIntArray);
        }
        return this;
    }

    public String getMessageName() {
        return "TestSubMessage";
    }

    @Override
    public byte[] getBytes() {
        MessageEncoder tEncoder = new MessageEncoder();
        this.encode(tEncoder);
        return tEncoder.getBytes();
    }

    @Override
    public int getMessageId() {
        return TestSubMessage.ID;
    }

    public void encode(MessageEncoder pEncoder) {


        MessageEncoder tEncoder = pEncoder;
        //Encode Attribute: mSubIntValue Type: int List: false
        tEncoder.add(mSubIntValue);

        //Encode Attribute: mSubStrValue Type: String List: false
        tEncoder.add(mSubStrValue);

        //Encode Attribute: mSubStringArray Type: String List: true
        tEncoder.addStringArray(mSubStringArray);

        //Encode Attribute: mSubIntArray Type: int List: true
        tEncoder.addIntegerArray(mSubIntArray);

    }

    public void decode(MessageDecoder pDecoder) {


        MessageDecoder tDecoder = pDecoder;

        //Decode Attribute: mSubIntValue Type:int List: false
        mSubIntValue = tDecoder.readInteger();

        //Decode Attribute: mSubStrValue Type:String List: false
        mSubStrValue = tDecoder.readString();

        //Decode Attribute: mSubStringArray Type:String List: true
        mSubStringArray = tDecoder.readStringArray("array");

        //Decode Attribute: mSubIntArray Type:int List: true
        mSubIntArray = tDecoder.readIntegerArray("array");


    }

    public Document getMongoDocument() {
        MongoEncoder tEncoder = new MongoEncoder();

        mongoEncode(tEncoder);
        return tEncoder.getDoc();
    }

    protected void mongoEncode(MongoEncoder pEncoder) {

        pEncoder.add("subIntValue", mSubIntValue);
        pEncoder.add("subStrValue", mSubStrValue);
        pEncoder.addStringArray("subStringArray", mSubStringArray);
        pEncoder.addIntegerArray("subIntArray", mSubIntArray);
    }

    public void decodeMongoDocument(Document pDoc) {

        Document tDoc = null;
        List<Document> tDocLst = null;
        MongoDecoder tDecoder = new MongoDecoder(pDoc);


        mSubIntValue = tDecoder.readInteger("subIntValue");

        mSubStrValue = tDecoder.readString("subStrValue");

        mSubStringArray = tDecoder.readStringArray("subStringArray", "array");

        mSubIntArray = tDecoder.readIntegerArray("subIntArray", "array");

    } // End decodeMongoDocument

    String blanks(int pCount) {
        if (pCount == 0) {
            return null;
        }
        String tBlanks = "                                                                                       ";
        return tBlanks.substring(0, pCount);
    }

    public String toString() {
        return this.toString(0);
    }

    public String toString(int pCount) {
        return toString(pCount, false);
    }

    public String toString(int pCount, boolean pExtention) {
        StringBuilder tSB = new StringBuilder(512);
        if (pCount > 0) {
            tSB.append(blanks(pCount));
        }

        if (pExtention) {
            tSB.append("<Extending Message: " + "\"TestSubMessage\"  Id: " + Integer.toHexString(getMessageId()) + ">\n");
        } else {
            tSB.append("Message: " + "\"TestSubMessage\"  Id: " + Integer.toHexString(getMessageId()) + "\n");
        }


        // FormatNative name: mSubIntValue array-type:
        tSB.append(blanks(pCount + 2) + "mSubIntValue: ");
        tSB.append(mSubIntValue);
        tSB.append("\n");
        // FormatNative name: mSubStrValue array-type:
        tSB.append(blanks(pCount + 2) + "mSubStrValue: ");
        tSB.append(mSubStrValue);
        tSB.append("\n");
        // FormatNative name: mSubStringArray array-type: array
        tSB.append(blanks(pCount + 2) + "mSubStringArray: ");
        tSB.append(MessageAux.addStringArray(pCount + 2, mSubStringArray));
        tSB.append("\n");
        // FormatNative name: mSubIntArray array-type: array
        tSB.append(blanks(pCount + 2) + "mSubIntArray: ");
        tSB.append(MessageAux.addIntegerArray(pCount + 2, mSubIntArray));
        tSB.append("\n");
        return tSB.toString();
    }

    public static class Builder {
        private final TestSubMessage mInstance;

        private Builder() {
            mInstance = new TestSubMessage();
        }


        public Builder setSubIntValue(int pValue) {
            mInstance.setSubIntValue(pValue);
            return this;
        }

        public Builder setSubStrValue(String pValue) {
            mInstance.setSubStrValue(pValue);
            return this;
        }

        public Builder setSubStringArray(List<String> pValue) {
            mInstance.setSubStringArray(pValue);
            return this;
        }

        public Builder setSubIntArray(List<Integer> pValue) {
            mInstance.setSubIntArray(pValue);
            return this;
        }


        public TestSubMessage build() {
            return mInstance;
        }

    }

}

/**
 * Possible native attributes
 * o "boolean" mapped to JSON "Boolean"
 * o "byte" mapped to JSON "Integer"
 * o "char" mapped to JSON "Integer"
 * o "short" mapped to JSON "Integer"
 * o "int" mapped to JSON "Integer"
 * o "long" mapped to JSON "Integer"
 * o "double" mapped to JSON "Numeric"
 * o "String" mapped to JSON "String"
 * o "byte[]" mapped to JSON "String" (Base64 string)
 * <p>
 * <p>
 * An attribute could also be an "constant" i.e. having the property "constantGroup", should then refer to an defined /Constang/Group
 * conastants are mapped to JSON strings,
 * <p>
 * <p>
 * If the type is not any of the types below it will be refer to an other structure / object
 **/

    