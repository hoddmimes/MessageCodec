# MessageCodec


This is a convenience tool for generating Java POJO Message object with optional POJO Mongo support and Message factory for the POJO message objects creation.

The generated Java POJO objects have the ability to encode itself to binary objects being byte vectors. POJO message objects can furthermore be created/instasited 
from an encoded byte vector.  


From the XML definitions the POJOs message objects are generated. Furthermore there is support for the generated POJO messages to encode / decode them self to Mongo documents.
For more information about the Mongo support see section *Mongo Support* below.

The typical use for having POJO message objects is when having a need for exchaning _message objects_ over a network connection.
The encoding/decoding is highly efficient when it comes to latency and message size, typically required by application with 
large message volumes and high latency requirements. 



## Transformation / Generation
There is a Java program **MessageGeneration.java** that takes one argument "-xml *transformation-definitions*.xml".  The program will read the definitions file and based on the definitions generate JAVA POJO classes and a MessageFactory.
The later can take a encoded byte vector string and create a corresponding POJO message object.

if running transformation from command line it is accomplished by executing the following command line 
~~~
   java -jar msgcodec-generate-1.4.jar -xml <transform-filset-definitions.xml>
~~~

maybe more relevant is howto invoke the transformation from a Gradle build. A transformation can be run as a JavaExec task, e.g.
~~~

   task generateMessageObject( type: JavaExec) {
       mainClass = 'MessageGeneration'
       classpath = ./libs/msgcodec-generate-1.4.jar
       args "-xml","./xml/TestMessagesFileSet.xml"
   } 
~~~


## XML Defintions
Samples are found in the directory ./samples/xml/
The definition file *(sample TestMessagesFileSet.xml)* defines what XML definition files that are going to be processed and where to generate the output.
It is possible to define references to other structure definitions files to be included i.e. linked references. This allows definitions to be defined in 
many files and packages.

## To Test
To test you can run a transformation of the sample definitions by execution the command'

> java -cp msgcodec-generate.1._x_.jar JsonTransform -xml ./xml/TestMessagesFileSet.xml

The file runGenerate.sh do this for you i.e. generate the test files.

## Sample TestMessageFileSet.xml

 <small>

    <MessageFiles>
    <!-- Generate a single message factory for all messages defined in the "MessageFile" files found -->
	<!-- If you do not like to generate a MessageFactory just comment out next line -->
	<MessageFactory outPath="./src/main/java/generated/" package="generated"/>

	<!-- Generates Mongo auxiliary CRUD and find methods for the structures defined in the "MessageFile" files found -->
	<!-- If you do not like to Mongo auxiliary support to be generated, comment out next line -->
	<MongoAux outPath="./src/main/java/generated/" package="generated"/>

	<!-- Generate POJO objects with Binary codec support based upon the definition found in the file -->
	<!-- You may have multiple "MessageFile" entries -->
	<MessageFile  file="./xml/TestMessage.xml" outPath="./src/main/java/generated/" package="generated" messageBase="3"/>
    </MessageFiles>

</small>

## Sample TestMessage.xml
<small>
<?xml version="1.0" encoding="UTF-8"?>
<Messages mongoSupport="true">
	<ConstantGroups prefix="Core">
		<Group name="CG1">
			<Constant id="X1"/>
			<Constant id="X2"/>
			<Constant id="X3"/>
			<Constant id="X4"/>
			<Constant id="X5"/>
		</Group>
		<Group name="CG2">
			<Constant id="Y1" value="500"/>
			<Constant id="Y2" value="501"/>
			<Constant id="Y3" value="502"/>
		</Group>
	</ConstantGroups>

	<Message name="TestMessageBase">
		<Attribute name="baseString" type="String"/>
		<Attribute name="baseInt" type="int"/>
	</Message>


	<Message name="TestSubMessage">
		<Attribute name="subIntValue" type="int"/>
		<Attribute name="subStrValue" type="String"/>
		<Attribute name="subStringArray" type="String" list="array"/>
		<Attribute name="subIntArray" type="int" list="array"/>
	</Message>


	<Message name="TestMessage" extends="TestMessageBase" rootMessage="true" db="true" dbCollection="Test">
		<Attribute name="booleanValue" type="boolean"/>
		<Attribute name="byteValue" type="byte"/>
		<Attribute name="shortValue" type="short"/>
		<Attribute name="intValue" type="int" dbKey="not-unique"/>
		<Attribute name="longValue" type="long"/>
		<Attribute name="floatValue" type="float"/>
		<Attribute name="doubleValue" type="double"/>
		<Attribute name="stringValue" type="String" dbKey="not-unique"/>
		<Attribute name="constValue" constantGroup="CG1"/>
		<Attribute name="byteArray" type="byte[]"/>
		<Attribute name="mapValue" type="Map"/>
		<Attribute name="dateValue" type="LocalDate"/>
		<Attribute name="dateTimeValue" type="LocalDateTime"/>
		<Attribute name="msgValue" type="TestSubMessage"/>

		<Attribute name="booleanList" type="boolean" list="array"/>
		<Attribute name="shortList" type="short"  list="array"/>
		<Attribute name="intList" type="int" list="array"/>
		<Attribute name="longList" type="long" list="array"/>
		<Attribute name="floatList" type="float" list="array"/>
		<Attribute name="doubleList" type="double" list="array"/>
		<Attribute name="stringList" type="String" list="array"/>
		<Attribute name="constList" constantGroup="CG1" list="array"/>
		<Attribute name="byteList" type="byte[]" list="array"/>
		<Attribute name="mapList" type="Map" list="array"/>
		<Attribute name="dateList" type="LocalDate" list="array"/>
		<Attribute name="dateTimeList" type="LocalDateTime" list="array"/>
		<Attribute name="msgList" type="TestSubMessage" list="array"/>
	</Message>
</Messages>
</small>

**the generated POJO/JSON object based upon the definition above can be viewed here: [TestMessage.java](https://github.com/hoddmimes/json-transform/blob/master/samples/src/main/java/generated/TestMessage.java)**

### Message Extensions
The natural way of extending message is to subclass a generated POJO message object However there is also a _dirty way_ of adding implementation to a generated POJO message by adding the code 
directly in the XML definition file using the element tag  **&lt;code&gt; ... &lt;/code&gt;** e.g.

<pre>
&lt;code&gt;
public int incrementFoo( int pIncrementValue )
{ 
   this.mFoo += pIncrementValue;
   return this.mFoo; 
}
&lt;/code&gt;
</pre>



### Supported Attribute Types


 >- "boolean"
 >- "byte"
 >- "char" 
 >- "short" 
 >- "int" 
 >- "long" 
 >- "double" 
 >- "String"
 >- "Map" ([{'name':value}...] _where key and value are strings_)
 >- "LocalDate" 
 >- "LocalDateTime" 
 >- "byte[]"

### Supported Attribute Lists Types


>    < Attribute name="intArray" type="int" list="array"/>
</small>

 **_"list"_** may be of the type
 * "array" - translates to java _ArrayList_
 * "linked" - translates to java _LinkedList_
 * "stack" - translates to java _Stack_





## Mongo Support
Optionally message objects can also be generated to support Mongo encode/decoding by implementing the interface
[MessageMongoInterface.java]([https://github.com/hoddmimes/json-transform/blob/master/sample/java/main/com/hoddmimes/json-transform/MessageMongoInterface.java](https://github.com/hoddmimes/json-transform/blob/master/sample/java/main/com/hoddmimes/jsontransform/MessageMongoInterface.java))

Three optional directives rules which and how message objects  implements Mongo support.
 * On a top level <Messages *mongoSupport="true"*> the attribute *mongoSupport* rules whatever the message definitions in the XML files
 should be subject for generate Mongo support or not.
 * On a individual message level <Message name="Froboz" rootMessage="true" *db="true" dbCollection="Test"*> the attribute *db* defines whatever the message is
 subject for implementing Mongo support or not. The attribute *dbCollection* is optional and is used to define an alternative name to be used for the Mongo collection into which the message
 is stored. If not present the collection name used will be equal with the message name.
 * On a message attribute level the xml attribute <Attribute name="intValue" type="int" mandatory="true" *dbKey="unique"*/> *dbKey* defines whatever the attribute
 should be a Mongo key in the document or not. If the *dbKey* value is *"unique"* the Mongo key would need to be unique. Furhermore an XML attribute could also be tagged with *dbTransient" <Attribute name="bytesValue" type="byte[]" *dbTransient="true"*/>.
 This will result in that the attribute will not be written/retrieved to/from Mongo.

In addition, a class MongoAux.java is generated. This class implements convince methods for creating a database, dropping a database and CRUD methods for handling the message POJO interaction with Mongo.
The files XML topfile, in the example  [TestMessageFileSet.xml]([https://github.com/hoddmimes/json-transform/blob/master/samples/xml/TestMessagesFileSet.xml](https://github.com/hoddmimes/json-transform/blob/master/sample/xml/TestMessagesFileSet.xml)) defines whatever the class MongoAux.java should be generated or not.


## Sample TestMessageCoding.java
the file [TestMessageCoding.java]([https://github.com/hoddmimes/json-transform/blob/master/samples/java/test/test/TestEncodeDecode.java](https://github.com/hoddmimes/json-transform/blob/master/sample/java/test/test/TestEncodeDecode.java))
exemplfies how encode/encode a Java POJO class.

## Sample TestMessageMongo.java
the file [TestMessageMongo.java]([https://github.com/hoddmimes/json-transform/blob/master/samples/java/test/test/TestMongoSupport.java](https://github.com/hoddmimes/json-transform/blob/master/sample/java/test/test/TestMongoSupport.java))
exemplfies how encode/encode a Java POJO class to Mongo DB documents. It also exemplifies how to use the generated Mongo auxiliary methods in the class [MongoAux]([[https://github.com/hoddmimes/json-transform/blob/master/sample/java/main/generated/MongoAux.java](https://github.com/hoddmimes/json-transform/blob/master/sample/java/main/generated/MongoAux.java)) to create,delete,find and update POJO objects in a Mongo DB.

The test assumes that there is a local Mongo DB instance without any authentication enabled.


## JAR Files Built
Three JAR files are built and placed on the project top level

- **msgcodec-generate-1.4.jar**  this JAR contains everyhing needed for generating POJO/JSON objects from a XML definition, A generation of objects are typically done by invoking the following command "java -cp pojojson-generate-1.4.jar JsonTransform -xml *transformation-definions*.xml"
- **msgcodec-1.4.jar** contains the support classes for encode/decode generated POJO/JSON objects. This JAR is need when there is a need for encode/decode generated objects.
- **msgcodec-tests.jar** contains all classes for running the test on the generated TestMessage object in this project.