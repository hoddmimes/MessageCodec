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

import com.hoddmimes.transform.MessageDecoder;
import com.hoddmimes.transform.MessageEncoder;
import com.hoddmimes.transform.MessageInterface;
import com.hoddmimes.transform.MessageMongoInterface;
import com.hoddmimes.transform.MongoDecoder;
import com.hoddmimes.transform.MongoEncoder;
import java.util.List;
import org.bson.Document;

@SuppressWarnings({"WeakerAccess", "unused", "unchecked"})
public class TestMessageBase implements MessageInterface, MessageMongoInterface {
  public static final String NAME = "TestMessageBase";
  public static final int ID = (3 << 16) + 1;

  private String mBaseString;
  private int mBaseInt;

  public TestMessageBase() {}

  // Constructor used for decoding root message
  public TestMessageBase(byte[] pBuffer) {
    MessageDecoder tDecoder = new MessageDecoder(pBuffer);
    this.decode(tDecoder);
  }

  public TestMessageBase setBaseString(String pBaseString) {
    mBaseString = pBaseString;
    return this;
  }

  public String getBaseString() {
    return mBaseString;
  }

  public TestMessageBase setBaseInt(int pBaseInt) {
    mBaseInt = pBaseInt;
    return this;
  }

  public int getBaseInt() {
    return mBaseInt;
  }

  public String getMessageName() {
    return "TestMessageBase";
  }

  @Override
  public byte[] getBytes() {
    MessageEncoder tEncoder = new MessageEncoder();
    this.encode(tEncoder);
    return tEncoder.getBytes();
  }

  @Override
  public int getMessageId() {
    return TestMessageBase.ID;
  }

  public void encode(MessageEncoder pEncoder) {

    MessageEncoder tEncoder = pEncoder;
    // Encode Attribute: mBaseString Type: String List: false
    tEncoder.add(mBaseString);

    // Encode Attribute: mBaseInt Type: int List: false
    tEncoder.add(mBaseInt);
  }

  public void decode(MessageDecoder pDecoder) {

    MessageDecoder tDecoder = pDecoder;

    // Decode Attribute: mBaseString Type:String List: false
    mBaseString = tDecoder.readString();

    // Decode Attribute: mBaseInt Type:int List: false
    mBaseInt = tDecoder.readInteger();
  }

  public Document getMongoDocument() {
    MongoEncoder tEncoder = new MongoEncoder();

    mongoEncode(tEncoder);
    return tEncoder.getDoc();
  }

  protected void mongoEncode(MongoEncoder pEncoder) {

    pEncoder.add("baseString", mBaseString);
    pEncoder.add("baseInt", mBaseInt);
  }

  public void decodeMongoDocument(Document pDoc) {

    Document tDoc = null;
    List<Document> tDocLst = null;
    MongoDecoder tDecoder = new MongoDecoder(pDoc);

    mBaseString = tDecoder.readString("baseString");

    mBaseInt = tDecoder.readInteger("baseInt");
  } // End decodeMongoDocument

  String blanks(int pCount) {
    if (pCount == 0) {
      return null;
    }
    String tBlanks =
        "                                                                                       ";
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
      tSB.append(
          "<Extending Message: "
              + "\"TestMessageBase\"  Id: "
              + Integer.toHexString(getMessageId())
              + ">\n");
    } else {
      tSB.append(
          "Message: " + "\"TestMessageBase\"  Id: " + Integer.toHexString(getMessageId()) + "\n");
    }

    // FormatNative name: mBaseString array-type:
    tSB.append(blanks(pCount + 2) + "mBaseString: ");
    tSB.append(String.valueOf(mBaseString));
    tSB.append("\n");
    // FormatNative name: mBaseInt array-type:
    tSB.append(blanks(pCount + 2) + "mBaseInt: ");
    tSB.append(String.valueOf(mBaseInt));
    tSB.append("\n");
    return tSB.toString();
  }

  public static Builder getTestMessageBaseBuilder() {
    return new TestMessageBase.Builder();
  }

  public static class Builder {
    private TestMessageBase mInstance;

    private Builder() {
      mInstance = new TestMessageBase();
    }

    public Builder setBaseString(String pValue) {
      mInstance.setBaseString(pValue);
      return this;
    }

    public Builder setBaseInt(int pValue) {
      mInstance.setBaseInt(pValue);
      return this;
    }

    public TestMessageBase build() {
      return mInstance;
    }
  }
}

        /**
         * Possible native attributes o "boolean" mapped to JSON "Boolean" o "byte" mapped to JSON
         * "Integer" o "char" mapped to JSON "Integer" o "short" mapped to JSON "Integer" o "int"
         * mapped to JSON "Integer" o "long" mapped to JSON "Integer" o "double" mapped to JSON
         * "Numeric" o "String" mapped to JSON "String" o "byte[]" mapped to JSON "String" (Base64
         * string)
         *
         * <p>An attribute could also be an "constant" i.e. having the property "constantGroup",
         * should then refer to an defined /Constang/Group conastants are mapped to JSON strings,
         *
         * <p>If the type is not any of the types below it will be refer to an other structure /
         * object
         */
