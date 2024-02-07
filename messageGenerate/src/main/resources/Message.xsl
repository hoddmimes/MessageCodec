<xsl:stylesheet version="3.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:map="http://www.w3.org/2005/xpath-functions/map"
                xmlns:functx="http://www.functx.com"
                exclude-result-prefixes="map">


    <xsl:output method="text"/>
    <xsl:param name="messageBase"/>
    <xsl:param name="outPath"/>
    <xsl:param name="package"/>
    <xsl:param name="inputXml"/>
    <xsl:param name="inputXsl"/>
    <xsl:param name="dbMessages"/> <!-- XDMNode -->
    <xsl:param name="comments"/>

    <xsl:include href="functx-1.0.1.xsl"/>

    <!--  ============================================== -->
    <!--  				Define JAVA data types 			 -->
    <!--  ============================================== -->


    <xsl:variable name="TypeTableDefinitions">
        <DataTypes>
            <Type name="boolean" type="boolean" objType="Boolean" arrayEncoder="addBooleanArray" arrayDecoder="readBooleanArray" decoder="readBoolean" />
            <Type name="byte" type="byte" objType="Byte" arrayEncoder="addByteArray" arrayDecoder="readByteArray" decoder="readByte" />
            <Type name="short" type="short"  objType="Short" arrayEncoder="addShortArray" arrayDecoder="readShortArray" decoder="readShort" />
            <Type name="int" type="int"  objType="Integer" arrayEncoder="addIntegerArray" arrayDecoder="readIntegerArray" decoder="readInteger" />
            <Type name="char" type="char"  objType="Character" arrayEncoder="addCharArray" arrayDecoder="readCharacterArray" decoder="readCharacter" />
            <Type name="long" type="long"  objType="Long" arrayEncoder="addLongArray" arrayDecoder="readLongArray" decoder="readLong" />
            <Type name="float" type="float" objType="Float" arrayEncoder="addFloatArray" arrayDecoder="readFloatArray" decoder="readFloat" />
            <Type name="double" type="double"  objType="Double" arrayEncoder="addDoubleArray" arrayDecoder="readDoubleArray" decoder="readDouble" />
            <Type name="String" type="String" objType="String" arrayEncoder="addStringArray" arrayDecoder="readStringArray" decoder="readString" />
            <Type name="Map" type="Map&lt;String,String&gt;" objType="Map&lt;String,String&gt;" arrayEncoder="addMapArray" arrayDecoder="readMapArray" decoder="readMap" />
            <Type name="LocalDate" type="LocalDate"  objType="LocalDate" arrayEncoder="addLocalDateArray" arrayDecoder="readLocalDateArray" decoder="readLocalDate" />
            <Type name="LocalDateTime" objType="LocalDateTime"  type="LocalDateTime" arrayEncoder="addLocalDateTimeArray" arrayDecoder="readLocalDateTimeArray" decoder="readLocalDateTime" />
            <Type name="byte[]" type="byte[]" objType="byte[]" arrayEncoder="addByteVectorArray" arrayDecoder="readByteVectorArray" decoder="readByteVector"/>
        </DataTypes>
    </xsl:variable>

    <xsl:variable name="typeTable" select="//$TypeTableDefinitions/DataTypes"/>


    <xsl:variable name="dbMsgs" as="map(xs:string, xs:string)">
        <xsl:map>
            <xsl:for-each select="//$dbMessages/MongoMessages/Message">
                <xsl:map-entry key="xs:string(@name)" select="xs:string(@name)"/>
            </xsl:for-each>
        </xsl:map>
    </xsl:variable>


    <!--     ===================================================== -->
    <!--    Generate Java message objects for all messages defined -->
    <!--     ===================================================== -->

    <xsl:template match="/Messages">  <!-- <xsl:message>outpath: <xsl:value-of select="$outPath"/> xsl: <xsl:value-of select="$inputXsl"/> package: <xsl:value-of select="$package"/> comment: <xsl:value-of select="$comments"/> xml: <xsl:value-of select="$inputXml"/> </xsl:message> -->
        <xsl:for-each select="Message">
            <xsl:variable name="msgPos" select="position()"/>
            <xsl:apply-templates mode="generateMessage" select=".">
                <xsl:with-param name="msgPos" select="$msgPos"/>
            </xsl:apply-templates>
        </xsl:for-each>
        <xsl:apply-templates mode="generateConstantGroups" select="."/>
    </xsl:template>



    <!--     ==================================================== -->
    <!--     			      Generate ConstantGroups						   -->
    <!--     ===================================================== -->

    <xsl:template mode="generateConstantGroups" match="Messages">
        <xsl:if test="ConstantGroups">
            <xsl:for-each select="ConstantGroups/Group">
                <xsl:variable name="file" select="concat('file://',$outPath,@name,'.java')"/>

                <xsl:result-document href="{$file}" method="text" omit-xml-declaration="yes" encoding="utf-8">
                    package <xsl:value-of select="$package"/>;
                    import com.hoddmimes.transform.MessageAux;

                    public enum <xsl:value-of select="@name"/>  {
                    <xsl:for-each select="Constant">
                        <xsl:if test="@value">
                            <xsl:value-of select="@id"/>(<xsl:value-of select="@value"/>)
                            <xsl:if test="not(position()=last())">,</xsl:if>
                        </xsl:if>
                        <xsl:if test="not(@value)">
                            <xsl:value-of select="@id"/>
                            <xsl:if test="not(position()=last())">,</xsl:if>
                        </xsl:if>
                    </xsl:for-each>;
                    <xsl:if test="Constant[@value]">
                        <xsl:if test="number(Constant[@value][1]/@value)">
                            private final int mValue;

                            <xsl:value-of select="@name"/>(final int pValue) {
                                mValue = pValue;
                            }

                            public int getValue() { return mValue; } </xsl:if>
                        <xsl:if test="not(number(Constant[@value][1]/@value))">
                            private final String mValue;

                            <xsl:value-of select="@name"/>(final String pValue) {
                                mValue = pValue;
                            }

                            public String getValue() { return mValue; }
                        </xsl:if>
                    </xsl:if>
                    private static final <xsl:value-of select="@name"/> cValues[] = values();
                    public static <xsl:value-of select="@name"/> get(int ordinal) { return cValues[ordinal]; }
                    }
                    <xsl:message>Created file <xsl:value-of select="$file"/></xsl:message>
                </xsl:result-document>
            </xsl:for-each>
        </xsl:if>
    </xsl:template>


    <!--     ============================================== -->
    <!--     			      Import templates							   -->
    <!--     ============================================== -->

    <xsl:template mode="addImports" match="Imports">
        // Add XML defined imports
        <xsl:for-each select="Import">
            import <xsl:value-of select="@path"/>;</xsl:for-each>
    </xsl:template>


    <!--     ============================================== -->
    <!--     			      Generate Message Class				   -->
    <!--     ============================================== -->
    <xsl:template mode="generateMessage" match="Message">
        <xsl:param name="msgPos"/>

        <xsl:variable name="file" select="concat('file://',$outPath,@name,'.java')"/>
        <xsl:result-document href="{$file}" method="text" omit-xml-declaration="yes" encoding="utf-8">
            <xsl:if test="$comments">
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
            </xsl:if>
<xsl:if test="$package!=''">
package <xsl:value-of select="$package"/>;
</xsl:if>
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.io.IOException;



<xsl:if test="../@mongoSupport='true'">
    import org.bson.BsonType;
    import org.bson.Document;
    import org.bson.conversions.Bson;
    import com.mongodb.BasicDBObject;
    import org.bson.types.ObjectId;
    import com.hoddmimes.transform.MessageMongoInterface;
    import com.hoddmimes.transform.MongoDecoder;
    import com.hoddmimes.transform.MongoEncoder;
</xsl:if>

import com.hoddmimes.transform.MessageInterface;
import com.hoddmimes.transform.MessageEncoder;
import com.hoddmimes.transform.MessageDecoder;
import com.hoddmimes.transform.ListFactory;
import com.hoddmimes.transform.MessageAux;



            <xsl:apply-templates mode="addImports" select="../Imports"/>
            <xsl:apply-templates mode="addImports" select="./Imports"/>

            @SuppressWarnings({"WeakerAccess","unused","unchecked"})
            public class <xsl:value-of select="@name"/> <xsl:if test="@extends"> extends <xsl:value-of select="@extends"/></xsl:if> implements MessageInterface <xsl:call-template name="declareInterfaces"/>
            {
                public static final String NAME = "<xsl:value-of select="@name"/>";
                public static final int ID = (<xsl:value-of select="$messageBase"/> &lt;&lt; 16) + <xsl:value-of select="$msgPos"/>;

            <xsl:apply-templates mode="declareAttributes" select="."/>
            <xsl:apply-templates mode="declareConstructors" select="."/>
            <xsl:apply-templates mode="declareGettersSetters" select="."/>
            <xsl:apply-templates mode="declareMessageIfMethods" select="."/>
            <xsl:apply-templates mode="generateMongoSupport" select="."/>
            <xsl:apply-templates mode="declareFormater" select="."/>
            <xsl:apply-templates mode="applyCode" select="."/>
            <xsl:apply-templates mode="generateBuilder" select="."/>
            }
            <xsl:if test="$comments">
            <xsl:apply-templates mode="addSomeDoc" select="."/>
            </xsl:if>
            <xsl:message>Created file <xsl:value-of select="$file"/></xsl:message>
        </xsl:result-document>
    </xsl:template>


    <xsl:template name="declareInterfaces">
        <xsl:variable name="mongoMsg" select="map:get($dbMsgs,string(@name))"/>
        <xsl:if test="$mongoMsg = @name">, MessageMongoInterface</xsl:if>
        <xsl:if test="@implements">, <xsl:value-of select="@implements"/></xsl:if>
        <xsl:for-each select="Interfaces/Interface">, <xsl:value-of select="@name"/></xsl:for-each>
    </xsl:template>

    <xsl:template mode="generateMongoDecoder" match="Message">
        public void decodeMongoDocument( Document pDoc ) {

            Document tDoc = null;
            List&lt;Document&gt; tDocLst = null;
            MongoDecoder tDecoder = new MongoDecoder( pDoc );

            <xsl:if test="@extends">super.decodeMongoDocument( pDoc );</xsl:if>

            <xsl:if test="@db='true'">
            ObjectId _tId = pDoc.get("_id", ObjectId.class);
            this.mMongoId = _tId.toString();
            </xsl:if>
        <xsl:for-each select="Attribute[not(@dbTransient='true')]">

            <xsl:if test="@constantGroup">
                <xsl:if test="not(@list)">
                    m<xsl:value-of select="functx:capitalize-first(@name)"/> = (<xsl:value-of select="@constantGroup"/>) tDecoder.readConstant("<xsl:value-of select='@name'/>", <xsl:value-of select="@constantGroup"/>.class); </xsl:if>
                <xsl:if test="@list">
                    m<xsl:value-of select="functx:capitalize-first(@name)"/> = (List&lt;<xsl:value-of select="@constantGroup"/>&gt;)tDecoder.readConstArray("<xsl:value-of select='@name'/>", "<xsl:value-of select='@list'/>", <xsl:value-of select="@constantGroup"/>.class); </xsl:if>
            </xsl:if>

            <xsl:if test="not(@constantGroup)">
                <xsl:variable name="dataType" select="@type"/>
                <xsl:if test="$typeTable/Type[@name=$dataType]">
                    <xsl:apply-templates mode="standardTypeFromMongoDoc" select="."/> </xsl:if>
                <xsl:if test="not($typeTable/Type[@name=$dataType])">
                    <xsl:apply-templates mode="messageFromMongoDoc" select="."/> </xsl:if>
            </xsl:if>
        </xsl:for-each>
        } // End decodeMongoDocument
    </xsl:template>

    <xsl:template mode="generateBuilder" match="Message">

        public static  Builder get<xsl:value-of select="@name"/>Builder() {
            return new <xsl:value-of select="@name"/>.Builder();
        }


        public static class  Builder {
          private <xsl:value-of select="@name"/> mInstance;

          private Builder () {
            mInstance = new <xsl:value-of select="@name"/>();
          }

        <xsl:apply-templates mode="generateBuildSetMethods" select="."/>
        <xsl:if test="@extends">
            <xsl:variable name="subclass" select="@extends"/>
            <xsl:apply-templates mode="generateBuildSetMethods" select="../Message[@name=$subclass]"/>
        </xsl:if>

        public <xsl:value-of select="@name"/> build() {
            return mInstance;
        }

        }
    </xsl:template>




    <xsl:template mode="generateBuildSetMethods" match="Message">
        <xsl:for-each select="Attribute">
            <xsl:if test="@constantGroup">
                public Builder set<xsl:value-of select="functx:capitalize-first (@name)"/>( <xsl:if test="@list">List&lt;<xsl:value-of select="@constantGroup"/>&gt;</xsl:if><xsl:if test="not(@list)"> <xsl:value-of select="@constantGroup"/></xsl:if> pValue ) {
                    mInstance.set<xsl:value-of select="functx:capitalize-first (@name)"/>( pValue );
                    return this;
                }
            </xsl:if>
            <xsl:if test="not(@constantGroup)">
                <xsl:variable name="dataType" select="@type"/>
                <xsl:if test="$typeTable/Type[@name=$dataType]">
                    <xsl:variable name="dType" select="$typeTable/Type[@name=$dataType]/@type"/>
                    <xsl:variable name="dObjType" select="$typeTable/Type[@name=$dataType]/@objType"/>
                    <xsl:if test="@list">
                        public Builder set<xsl:value-of select="functx:capitalize-first(@name)"/>( List&lt;<xsl:value-of select="$dObjType"/>&gt; pValue ) {</xsl:if>
                    <xsl:if test="not(@list)">
                        public Builder set<xsl:value-of select="functx:capitalize-first (@name)"/>( <xsl:value-of select="$dType"/> pValue ) {</xsl:if>
                        mInstance.set<xsl:value-of select="functx:capitalize-first (@name)"/>( pValue );
                        return this;
                    }
                </xsl:if>
                <xsl:if test="not($typeTable/Type[@name=$dataType])">
                    public Builder set<xsl:value-of select="functx:capitalize-first (@name)"/>( <xsl:if test="@list">List&lt;<xsl:value-of select="@type"/>&gt;</xsl:if> <xsl:if test="not(@list)"> <xsl:value-of select="@type"/></xsl:if> pValue )  {
                        mInstance.set<xsl:value-of select="functx:capitalize-first(@name)"/>( pValue );
                        return this;
                    }
                </xsl:if>
            </xsl:if>
          </xsl:for-each>
    </xsl:template>






    <xsl:template mode="standardTypeFromMongoDoc" match="Attribute">
        <xsl:variable name="dataType" select="@type"/>
        <xsl:if test="not(@list)">
           <xsl:variable name="decoder" select="$typeTable/Type[@name=$dataType]/@decoder"/>
           m<xsl:value-of select="functx:capitalize-first (@name)"/> = tDecoder.<xsl:value-of select="$decoder"/>("<xsl:value-of select='@name'/>");
        </xsl:if>
        <xsl:if test="@list">
           <xsl:variable name="decoder" select="$typeTable/Type[@name=$dataType]/@arrayDecoder"/>
           m<xsl:value-of select="functx:capitalize-first (@name)"/> = tDecoder.<xsl:value-of select="$decoder"/>("<xsl:value-of select='@name'/>", "<xsl:value-of select='@list'/>");
        </xsl:if>
    </xsl:template>

    <xsl:template mode="messageFromMongoDoc" match="Attribute">
        <xsl:if test="not(@list)">
            tDoc = (Document) tDecoder.readMessage("<xsl:value-of select="@name"/>");
            if (tDoc == null) {
              m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
            } else {
               m<xsl:value-of select="functx:capitalize-first (@name)"/> = new <xsl:value-of select="@type"/>();
               m<xsl:value-of select="functx:capitalize-first (@name)"/>.decodeMongoDocument( tDoc );
            } </xsl:if>
        <xsl:if test="@list">
                tDocLst = (List&lt;Document&gt;) tDecoder.readMessageArray("<xsl:value-of select='@name'/>", "<xsl:value-of select='@list'/>" );
                if (tDocLst == null) {
                   m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
                } else {
                   m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");
                   for( Document doc :  tDocLst ) {
                    <xsl:value-of select="@type"/>  m = new <xsl:value-of select="functx:capitalize-first (@type)"/>();
                    m.decodeMongoDocument( doc );
                    m<xsl:value-of select="functx:capitalize-first (@name)"/>.add(m);
                }
            } </xsl:if>
     </xsl:template>





    <xsl:template mode="generateMongoSupport" match="Message">
        <xsl:variable name="mongoMsg" select="map:get($dbMsgs, string(@name))"/>

       <!--
        <xsl:if test="$mongoMsg = @name">
           <xsl:message>Message: <xsl:value-of select="@name"/> mongoMsg: true HashName: <xsl:value-of select="$mongoMsg"/></xsl:message></xsl:if>
        <xsl:if test="not($mongoMsg = @name)">
            <xsl:message>Message: <xsl:value-of select="@name"/> mongoMsg: false HashName: <xsl:value-of select="$mongoMsg"/></xsl:message></xsl:if>
        -->

        <xsl:if test="$mongoMsg = @name">
            <xsl:apply-templates mode="generateMongoEncoder" select="."/>
            <xsl:apply-templates mode="generateMongoDecoder" select="."/>
        </xsl:if>
    </xsl:template>



    <xsl:template mode="generateMongoEncoder" match="Message">
        public Document getMongoDocument() {
            MongoEncoder tEncoder = new MongoEncoder();
            <xsl:if test="@extends">super.mongoEncode( tEncoder );</xsl:if>
            mongoEncode( tEncoder );
            return tEncoder.getDoc();
        }

     protected void mongoEncode(  MongoEncoder pEncoder ) {
        <xsl:for-each select="Attribute[not(@dbTransient='true')]">
           <xsl:if test="@constantGroup">
               <xsl:if test="not(@list)">
                   pEncoder.add( "<xsl:value-of select="@name"/>", m<xsl:value-of select="functx:capitalize-first (@name)"/> ); </xsl:if>
               <xsl:if test="@list">
                   pEncoder.addConstArray("<xsl:value-of select="@name"/>", m<xsl:value-of select="functx:capitalize-first (@name)"/> );</xsl:if>
           </xsl:if>
           <xsl:if test="not(@constantGroup)">
               <xsl:variable name="dataType" select="@type"/>
               <xsl:if test="$typeTable/Type[@name=$dataType]">
                   <xsl:apply-templates mode="standardTypeToMongoDoc" select="."/> </xsl:if>
               <xsl:if test="not($typeTable/Type[@name=$dataType])">
                   <xsl:apply-templates mode="messageToMongoDoc" select="."/> </xsl:if>
           </xsl:if>

       </xsl:for-each>
    }
    </xsl:template>

    <xsl:template mode="messageToMongoDoc" match="Attribute">
        <xsl:if test="not(@list)">
            pEncoder.add("<xsl:value-of select="@name"/>",   m<xsl:value-of select="functx:capitalize-first (@name)"/> );</xsl:if>
        <xsl:if test="@list">
            pEncoder.addMessageArray("<xsl:value-of select="@name"/>",  m<xsl:value-of select="functx:capitalize-first (@name)"/> );</xsl:if>
    </xsl:template>

    <xsl:template mode="standardTypeToMongoDoc" match="Attribute">
            <xsl:variable name="dataType" select="@type"/>
            <xsl:if test="@list">
                <xsl:variable name="encoder" select="$typeTable/Type[@name=$dataType]/@arrayEncoder"/>
                pEncoder.<xsl:value-of select="$encoder"/>( "<xsl:value-of select="@name"/>", m<xsl:value-of select="functx:capitalize-first (@name)"/> );</xsl:if>
            <xsl:if test="not(@list)">
                pEncoder.add("<xsl:value-of select="@name"/>",  m<xsl:value-of select="functx:capitalize-first (@name)"/> );</xsl:if>
    </xsl:template>




    <xsl:template mode="addSomeDoc" match="Message">
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

    </xsl:template>


    <xsl:template mode="declareConstructors" match="Message">
               public <xsl:value-of select="functx:capitalize-first (@name)"/>()
               {
                <xsl:if test="@extends">super();</xsl:if>
               }

               // Constructor used for decoding root message
               public <xsl:value-of select="functx:capitalize-first (@name)"/>(byte[] pBuffer ) {
                    MessageDecoder tDecoder = new MessageDecoder( pBuffer );
                    this.decode( tDecoder );
               }
    </xsl:template>


    <!--     ============================================== -->
    <!--     			Declare Message Attributes			-->
    <!--     ============================================== -->

    <xsl:template mode="declareAttributes" match="Message">
        <xsl:if test="./@db='true'">
                private String mMongoId = null;</xsl:if>
        <xsl:for-each select="Attribute">
            <xsl:if test="@constantGroup">
                <xsl:variable name="cType" select="@constantGroup"/>
                private <xsl:if test="@list">List&lt;<xsl:value-of select="$cType"/>&gt;</xsl:if><xsl:if test="not(@list)"><xsl:value-of select="$cType"/></xsl:if> m<xsl:value-of select="functx:capitalize-first (@name)"/>;</xsl:if>
            <xsl:if test="not(@constantGroup)">
                <xsl:variable name="dataType" select="@type"/>
                <xsl:if test="$typeTable/Type[@name=$dataType]">
                    <xsl:variable name="dType" select="$typeTable/Type[@name=$dataType]/@type"/>
                    <xsl:variable name="dObjType" select="$typeTable/Type[@name=$dataType]/@objType"/>
                    private <xsl:if test="@list">List&lt;<xsl:value-of select="$dObjType"/>&gt;</xsl:if><xsl:if test="not(@list)"><xsl:value-of select="$dType"/></xsl:if> m<xsl:value-of select="functx:capitalize-first (@name)"/>;</xsl:if>
                <xsl:if test="not($typeTable/Type[@name=$dataType])">
                    private <xsl:if test="@list">List&lt;<xsl:value-of select="$dataType"/>&gt;</xsl:if><xsl:if test="not(@list)"><xsl:value-of select="@type"/></xsl:if> m<xsl:value-of select="functx:capitalize-first (@name)"/>;</xsl:if>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>


    <!--     ============================================== -->
    <!--     			Apply code written in XSL very ugly -->
    <!--     ============================================== -->

    <xsl:template mode="applyCode" match="Message">
        <xsl:value-of select="code"/>
    </xsl:template>


    <!--     ============================================== -->
    <!--     			Declare initializer                  	   -->
    <!--     ============================================== -->
    <xsl:template mode="declareInitializer" match="Message">
        <xsl:for-each select="Attribute">
            <xsl:if test="@constantGroup">
                <xsl:variable name="cType" select="@constantGroup"/>
                private <xsl:if test="@list">List&lt;<xsl:value-of select="$cType"/>&gt;</xsl:if><xsl:if test="not(@list)"><xsl:value-of select="$cType"/></xsl:if> m<xsl:value-of select="functx:capitalize-first (@name)"/>;</xsl:if>
            <xsl:if test="not(@constantGroup)">
                <xsl:variable name="dataType" select="@type"/>
                <xsl:if test="$typeTable/Type[@name=$dataType]">
                    <xsl:variable name="dType" select="$typeTable/Type[@name=$dataType]/@type"/>
                    private <xsl:if test="@list">List&lt;<xsl:value-of select="$dType"/>&gt;</xsl:if><xsl:if test="not(@list)"><xsl:value-of select="$dType"/></xsl:if> m<xsl:value-of select="functx:capitalize-first (@name)"/>;</xsl:if>
                <xsl:if test="not($typeTable/Type[@name=$dataType])">
                    private <xsl:if test="@list">List&lt;<xsl:value-of select="$dataType"/>&gt;</xsl:if><xsl:if test="not(@list)"><xsl:value-of select="@type"/></xsl:if> m<xsl:value-of select="functx:capitalize-first (@name)"/>;</xsl:if>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <!--     ============================================== -->
    <!--     			Declare DeclareGettersSetters                  	   -->
    <!--     ============================================== -->



    <xsl:template mode="declareGettersSetters" match="Message">
        <xsl:if test="./@db='true'">
            public String getMongoId() {
            return this.mMongoId;
            }

            public void setMongoId( String pMongoId ) {
            this.mMongoId = pMongoId;
            }
        </xsl:if>
        <xsl:for-each select="Attribute">
            <xsl:if test="@constantGroup">
                <xsl:apply-templates mode="declareConstantGetterSetter" select="."/>
            </xsl:if>
            <xsl:variable name="dataType" select="@type"/>
            <xsl:if test="$typeTable/Type[@name=$dataType]">
                <xsl:apply-templates mode="declareNativeGetterSetter" select="."/>
            </xsl:if>
            <xsl:if test="not($typeTable/Type[@name=$dataType]) and not(@constantGroup)">
                <xsl:apply-templates mode="declareMessageGetterSetter" select="."/>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template mode="declareConstantGetterSetter" match="Attribute">
        <xsl:if test="not(@list)">
            public <xsl:value-of select="../@name"/> set<xsl:value-of select="functx:capitalize-first (@name)"/>(<xsl:value-of select="@constantGroup"/> p<xsl:value-of select="functx:capitalize-first (@name)"/>) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = p<xsl:value-of
                select="functx:capitalize-first (@name)"/>;
            return this;
            }

            public <xsl:value-of select="@constantGroup"/> get<xsl:value-of select="functx:capitalize-first (@name)"/>() {
            return  m<xsl:value-of select="functx:capitalize-first (@name)"/>;
            }
        </xsl:if>
        <xsl:if test="@list">
            public List&lt;<xsl:value-of select="@constantGroup"/>&gt; get<xsl:value-of select="functx:capitalize-first(@name)"/>() {
            return m<xsl:value-of select="functx:capitalize-first (@name)"/> ;
            }
            public <xsl:value-of select="../@name"/> set<xsl:value-of select="functx:capitalize-first (@name)"/>(List&lt;<xsl:value-of select="@constantGroup"/>&gt;p<xsl:value-of select="functx:capitalize-first (@name)"/>) {
            if (p<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
            } else {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");
            m<xsl:value-of select="functx:capitalize-first (@name)"/>.addAll( p<xsl:value-of select="functx:capitalize-first (@name)"/> );
            }
            return this;
            }



        </xsl:if>
    </xsl:template>


    <xsl:template mode="declareNativeGetterSetter" match="Attribute">

        <xsl:variable name="dataType" select="@type"/>
        <xsl:variable name="type" select="$typeTable/Type[@name=$dataType]/@type"/>
        <xsl:variable name="objType" select="$typeTable/Type[@name=$dataType]/@objType"/>

        <xsl:if test="@list">
            public <xsl:value-of select="../@name"/> set<xsl:value-of select="functx:capitalize-first (@name)"/>(List&lt;<xsl:value-of select="$objType"/>&gt; p<xsl:value-of select="functx:capitalize-first (@name)"/> ) {
            if ( p<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
            } else {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");
            m<xsl:value-of select="functx:capitalize-first (@name)"/>.addAll( p<xsl:value-of select="functx:capitalize-first (@name)"/> );
            }
            return this;
            }

            public <xsl:value-of select="../@name"/> add<xsl:value-of select="functx:capitalize-first (@name)"/>Item(<xsl:value-of select="$type"/> p<xsl:value-of select="functx:capitalize-first (@name)"/> ) {
                if (m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
                    m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");
                }
                m<xsl:value-of select="functx:capitalize-first (@name)"/>.add( p<xsl:value-of select="functx:capitalize-first (@name)"/> );
                return this;
            }

            public List&lt;<xsl:value-of select="$objType"/>&gt; get<xsl:value-of select="functx:capitalize-first (@name)"/>()
            {
            return  m<xsl:value-of select="functx:capitalize-first (@name)"/>;
            }
        </xsl:if>

        <xsl:if test="not(@list)">
            public <xsl:value-of select="../@name"/> set<xsl:value-of select="functx:capitalize-first (@name)"/>( <xsl:value-of select="$type"/> p<xsl:value-of select="functx:capitalize-first (@name)"/> ) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = p<xsl:value-of
                select="functx:capitalize-first (@name)"/>;
            return this;
            }
            public <xsl:value-of select="$type"/> get<xsl:value-of select="functx:capitalize-first (@name)"/>() {
              return  m<xsl:value-of select="functx:capitalize-first (@name)"/>;
            }
        </xsl:if>

    </xsl:template>


    <xsl:template mode="declareMessageGetterSetter" match="Attribute">
        <xsl:variable name="dataType" select="@type"/>


        <xsl:if test="@list">
            public <xsl:value-of select="../@name"/> set<xsl:value-of select="functx:capitalize-first (@name)"/>( List&lt;<xsl:value-of select="$dataType"/>&gt; p<xsl:value-of select="functx:capitalize-first (@name)"/> ) {
              if (p<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
                m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
                return this;
              }


            if ( m<xsl:value-of select="functx:capitalize-first (@name)"/> == null)
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");


            m<xsl:value-of select="functx:capitalize-first (@name)"/> .addAll( p<xsl:value-of
                select="functx:capitalize-first (@name)"/> );
            return this;
            }


            public <xsl:value-of select="../@name"/> add<xsl:value-of select="functx:capitalize-first (@name)"/>( List&lt;<xsl:value-of select="$dataType"/>&gt; p<xsl:value-of select="functx:capitalize-first (@name)"/> ) {

            if ( m<xsl:value-of select="functx:capitalize-first (@name)"/> == null)
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");

            m<xsl:value-of select="functx:capitalize-first (@name)"/> .addAll( p<xsl:value-of
                select="functx:capitalize-first (@name)"/> );
            return this;
            }

            public <xsl:value-of select="../@name"/> add<xsl:value-of select="functx:capitalize-first (@name)"/>( <xsl:value-of select="$dataType"/> p<xsl:value-of select="functx:capitalize-first (@name)"/> ) {

            if ( p<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            return this; // Not supporting null in vectors, well design issue
            }

            if ( m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select='@list'/>");
            }

            m<xsl:value-of select="functx:capitalize-first (@name)"/>.add( p<xsl:value-of
                select="functx:capitalize-first (@name)"/> );
            return this;
            }


            public List&lt;<xsl:value-of select="$dataType"/>&gt; get<xsl:value-of select="functx:capitalize-first (@name)"/>() {
                return m<xsl:value-of select="functx:capitalize-first (@name)"/>;
            }

        </xsl:if>

        <xsl:if test="not(@list)">

            public <xsl:value-of select="$dataType"/> get<xsl:value-of select="functx:capitalize-first (@name)"/>() {
              return  m<xsl:value-of select="functx:capitalize-first (@name)"/>;
            }

            public <xsl:value-of select="../@name"/> set<xsl:value-of select="functx:capitalize-first (@name)"/>(<xsl:value-of select="$dataType"/> p<xsl:value-of select="functx:capitalize-first (@name)"/>) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = p<xsl:value-of
                select="functx:capitalize-first (@name)"/>;
            return this;
            }

        </xsl:if>


    </xsl:template>


    <!--     ============================================== -->
    <!--     			Declare MessageIf methods           -->
    <!--     ============================================== -->

    <xsl:template mode="declareMessageIfMethods" match="Message">
        <xsl:param name="msgPos"/>
        <xsl:apply-templates mode="declareMsgIdMethods" select=".">
            <xsl:with-param name="msgPos" select="$msgPos"/>
        </xsl:apply-templates>
        <xsl:apply-templates mode="declareMsgCodecMethods" select="."/>





    </xsl:template>


    <!--     ==================================================== -->
    <!--     			Declare MessageIf ID methods                      	  -->
    <!--     ==================================================== -->
    <xsl:template mode="declareMsgIdMethods" match="Message">
        public String getMessageName() {
            return "<xsl:value-of select="@name"/>";
        }
    </xsl:template>


    <!--     ==================================================== -->
    <!--     			Declare MessageIf encode/decode methods               	  -->
    <!--     ==================================================== -->



    <xsl:template mode="declareMsgCodecMethods" match="Message">

        @Override
        public byte[] getBytes() {
            MessageEncoder tEncoder = new MessageEncoder();
            this.encode( tEncoder );
            return tEncoder.getBytes();
        }

        @Override
        public int getMessageId() {
            return <xsl:value-of select="@name"/>.ID;
        }

        <!-- ================= ENCODER ======================== -->
        public void encode( MessageEncoder pEncoder) {

        <xsl:if test="not(@rootMessage='true')">
            MessageEncoder tEncoder = pEncoder;</xsl:if>
        <xsl:if test="@rootMessage='true'">
            MessageEncoder tEncoder = pEncoder;
            pEncoder.add( <xsl:value-of select="@name"/>.ID );</xsl:if>

        <xsl:if test="@extends">
            super.encode( tEncoder );
        </xsl:if>

        <xsl:for-each select="Attribute">
            //Encode Attribute: m<xsl:value-of select="functx:capitalize-first (@name)"/> Type: <xsl:value-of select="@type"/> List: <xsl:if test="@list">true</xsl:if><xsl:if test="not(@list)">false</xsl:if>
            <xsl:if test="@list">
                <xsl:apply-templates mode="encoderArray" select="."/>
            </xsl:if>
            <xsl:if test="not(@list)">
                <xsl:apply-templates mode="encoderSingle" select="."/>
            </xsl:if>
        </xsl:for-each>
        }

        <!-- ================= DECODER ======================== -->
        public void decode( MessageDecoder pDecoder) {

        <xsl:if test="not(@rootMessage='true')">
            MessageDecoder tDecoder = pDecoder;
        </xsl:if>
        <xsl:if test="@rootMessage='true'">
            MessageDecoder tDecoder = pDecoder;
            tDecoder.readInteger(); // Root messages has the message ID as the first 4 bytes
        </xsl:if>
        <xsl:if test="@extends">super.decode( tDecoder );</xsl:if>

        <xsl:for-each select="Attribute">
            //Decode Attribute: m<xsl:value-of select="functx:capitalize-first (@name)"/> Type:<xsl:value-of select="@type"/> List: <xsl:if test="@list">true</xsl:if><xsl:if test="not(@list)">false</xsl:if>
            <xsl:if test="@list">
                <xsl:apply-templates mode="decoderArray" select="."/>
            </xsl:if>
            <xsl:if test="not(@list)">
                <xsl:apply-templates mode="decoderSingle" select="."/>
            </xsl:if>
        </xsl:for-each>

        }
    </xsl:template>


    <!-- ++++++++++++++++++++++++++++++++++++++++++
                               Decode methods
     ++++++++++++++++++++++++++++++++++++++++++++ -->


    <xsl:template mode="decoderSingle" match="Attribute">
        <xsl:variable name="dataType" select="@type"/>

        <xsl:if test="@constantGroup">
            if (!tDecoder.readBoolean()) {
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
            } else {
               m<xsl:value-of select="functx:capitalize-first (@name)"/> = (<xsl:value-of select="@constantGroup"/>) <xsl:value-of select="@constantGroup"/>.get(  tDecoder.readInteger());
            }
        </xsl:if>
        <xsl:if test="$typeTable/Type[@name=$dataType]">
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = tDecoder.<xsl:value-of select="$typeTable/Type[@name=$dataType]/@decoder"/>();
        </xsl:if>
        <xsl:if test="not($typeTable/Type[@name=$dataType]) and not(@constantGroup)">
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = (<xsl:value-of select="$dataType"/>) tDecoder.readMessage( <xsl:value-of select="$dataType"/>.class );
        </xsl:if>
    </xsl:template>


    <xsl:template mode="decoderArray" match="Attribute">
        <xsl:variable name="dataType" select="@type"/>

        <xsl:if test="@constantGroup">
            if (!tDecoder.readBoolean()) {
               m<xsl:value-of select="functx:capitalize-first (@name)"/> = null;
            } else {
                    m<xsl:value-of select="functx:capitalize-first (@name)"/> = ListFactory.getList("<xsl:value-of select="@list"/>");
                    int tSize = tDecoder.readInteger();
                    for( int i = 0; i &lt; tSize; i++ ) {
                        m<xsl:value-of select="functx:capitalize-first (@name)"/>.add( <xsl:value-of select="@constantGroup"/>.get(tDecoder.readInteger()));
                    }
            }
        </xsl:if>
        <xsl:if test="$typeTable/Type[@name=$dataType]">
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = tDecoder.<xsl:value-of select="$typeTable/Type[@name=$dataType]/@arrayDecoder"/>( "<xsl:value-of select="@list"/>");
        </xsl:if>
        <xsl:if test="not($typeTable/Type[@name=$dataType]) and not(@constantGroup)">
            m<xsl:value-of select="functx:capitalize-first (@name)"/> = (List&lt;<xsl:value-of select="$dataType"/>&gt;) tDecoder.readMessageArray( <xsl:value-of select="$dataType"/>.class );
        </xsl:if>
    </xsl:template>


    <!-- +++++++++++++++++++++++++++++++++++++++++++++
                               Encoder methods
    ++++++++++++++++++++++++++++++++++++++++++++++++ -->


    <xsl:template mode="encoderArray" match="Attribute">
        <xsl:variable name="dataType" select="@type"/>
        <xsl:if test="@constantGroup">
            if (m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
              tEncoder.add(false);
            } else {
              tEncoder.add(true);
              tEncoder.add( m<xsl:value-of select="functx:capitalize-first (@name)"/>.size());
              for( int i = 0; i &lt; m<xsl:value-of select="functx:capitalize-first (@name)"/>.size(); i++ ) {
                tEncoder.add( m<xsl:value-of select="functx:capitalize-first (@name)"/>.get(i).ordinal());
              }
            }
        </xsl:if>
        <xsl:if test="$typeTable/Type[@name=$dataType]">
            tEncoder.<xsl:value-of select="$typeTable/Type[@name=$dataType]/@arrayEncoder"/>( m<xsl:value-of select="functx:capitalize-first (@name)"/> );
        </xsl:if>
        <xsl:if test="not($typeTable/Type[@name=$dataType]) and not(@constantGroup)">
            tEncoder.addMessageArray( m<xsl:value-of select="functx:capitalize-first (@name)"/> );
        </xsl:if>
    </xsl:template>


    <xsl:template mode="encoderSingle" match="Attribute">
        <xsl:variable name="dataType" select="@type"/>
        <xsl:if test="@constantGroup">
            if (m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
                tEncoder.add(false);
            } else {
                tEncoder.add(true);
                tEncoder.add( m<xsl:value-of select="functx:capitalize-first (@name)"/>.ordinal());
            }
        </xsl:if>
        <xsl:if test="$typeTable/Type[@name=$dataType]">
            tEncoder.add( m<xsl:value-of select="functx:capitalize-first (@name)"/> );
        </xsl:if>
        <xsl:if test="not($typeTable/Type[@name=$dataType]) and not(@constantGroup)">
            tEncoder.add( m<xsl:value-of select="functx:capitalize-first (@name)"/> );
        </xsl:if>
    </xsl:template>


    <xsl:template mode="formatNative" match="Attribute">
        // FormatNative name: m<xsl:value-of select="functx:capitalize-first (@name)"/> array-type: <xsl:value-of select="@list"/>
        <xsl:if test="@list">
            <xsl:variable name="dataType" select="@type"/>
            <xsl:variable name="fmtarr" select="$typeTable/Type[@name=$dataType]/@arrayEncoder"/>
            tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select="functx:capitalize-first (@name)"/>: ");
            tSB.append( MessageAux.<xsl:value-of select="$fmtarr"/>( pCount + 2, m<xsl:value-of select="functx:capitalize-first (@name)"/>));
            tSB.append("\n");</xsl:if>
        <xsl:if test="not(@list)">
            <xsl:if test="@type='byte[]'">
                tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select="functx:capitalize-first (@name)"/>: ");
                tSB.append( MessageAux.format( m<xsl:value-of select="functx:capitalize-first (@name)"/>));
                tSB.append("\n");</xsl:if>
            <xsl:if test="not(@type='byte[]')">
                tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select="functx:capitalize-first (@name)"/>: ");
                tSB.append( String.valueOf( m<xsl:value-of select="functx:capitalize-first (@name)"/> ));
                tSB.append("\n"); </xsl:if></xsl:if>
    </xsl:template>




    <xsl:template mode="formatMessage" match="Attribute">
        // FormatMessage name: m<xsl:value-of select="functx:capitalize-first (@name)"/> array-type: <xsl:value-of select="@list"/>
        <xsl:if test="@list">
            tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select='functx:capitalize-first (@name)'/>[]: ");
            if ( m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            tSB.append("&lt;null&gt;");
            } else {
            tSB.append("\n");
            int tSize = m<xsl:value-of select="functx:capitalize-first (@name)"/>.size();
            for( int i = 0; i &lt; tSize; i++ ) {
            <xsl:value-of select="@type"/> tMsg = (<xsl:value-of select="@type"/>) m<xsl:value-of select="functx:capitalize-first (@name)"/>.get( i );
            tSB.append(  tMsg.toString( pCount + 4 ) );
            }
            } </xsl:if>
        <xsl:if test="not(@list)">
            tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select="functx:capitalize-first (@name)"/>: ");
            if ( m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            tSB.append("&lt;null&gt;");
            } else {
            tSB.append( m<xsl:value-of select="functx:capitalize-first (@name)"/>.toString( pCount + 4 ) );
            tSB.append("\n");
            } </xsl:if>
    </xsl:template>

    <xsl:template mode="formatConstant" match="Attribute">
        // FormatConstant name: m<xsl:value-of select="functx:capitalize-first (@name)"/> array-type: <xsl:value-of select="@list"/>
        <xsl:if test="not(@list)">
            tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select='functx:capitalize-first (@name)'/>: ");
            if (m<xsl:value-of select='functx:capitalize-first (@name)'/> == null) {
                tSB.append("&lt;null&gt;");
            } else {
                tSB.append(  m<xsl:value-of select="functx:capitalize-first (@name)"/>.toString() );
            }

            tSB.append("\n");
        </xsl:if>
        <xsl:if test="@list">
            tSB.append( blanks( pCount + 2 ) + "m<xsl:value-of select='functx:capitalize-first (@name)'/>[]: ");
            if ( m<xsl:value-of select="functx:capitalize-first (@name)"/> == null) {
            tSB.append("&lt;null&gt;");
            } else {
            int tSize = m<xsl:value-of select="functx:capitalize-first (@name)"/>.size();
            for( int i = 0; i &lt; tSize; i++ ) {
            tSB.append( m<xsl:value-of select="functx:capitalize-first (@name)"/>.get( i ).toString() + " ");
            }
            }
            tSB.append("\n");
        </xsl:if>
    </xsl:template>


    <xsl:template mode="declareFormater" match="Message">

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
        tSB.append("&lt;Extending Message: " + "\"<xsl:value-of select="@name"/>\"  Id: " + Integer.toHexString(getMessageId()) + "&gt;\n");
        } else {
        tSB.append("Message: " + "\"<xsl:value-of select="@name"/>\"  Id: " +  Integer.toHexString(getMessageId())  + "\n");
        }

        <xsl:if test="@extends">tSB.append( super.toString( pCount + 3, true ));</xsl:if>

        <xsl:for-each select="Attribute">
            <xsl:if test="@constantGroup">
                <xsl:apply-templates mode="formatConstant" select="."/>
            </xsl:if>
            <xsl:if test="not(@constantGroup)">
                <xsl:variable name="dataType" select="@type"/>
                <xsl:if test="$typeTable/Type[@name=$dataType]">
                    <xsl:apply-templates mode="formatNative" select="."/>
                </xsl:if>
                <xsl:if test="not($typeTable/Type[@name=$dataType])">
                    <xsl:apply-templates mode="formatMessage" select="."/>
                </xsl:if>
            </xsl:if>
        </xsl:for-each>
        return tSB.toString();
        }
    </xsl:template>


</xsl:stylesheet>
