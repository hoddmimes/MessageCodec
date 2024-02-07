

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

import com.hoddmimes.transform.*;
import java.nio.ByteBuffer;


	
import generated.*;
	

@SuppressWarnings({"WeakerAccess","unused","unchecked"})
public class MessageFactory implements MessageFactoryInterface
{


	public int getMessageId( byte[] pBuffer )
	{
		ByteBuffer byteBuffer = ByteBuffer.wrap( pBuffer );
		return byteBuffer.getInt();
	}

	@Override
	public MessageInterface getMessageInstance(byte[] pBuffer) {
		int tMessageId = getMessageId( pBuffer );
	
		switch( tMessageId ) 
		{

            case TestMessage.ID:
            {
            	TestMessage tMessage = new TestMessage();
            	tMessage.decode( new MessageDecoder(pBuffer));
            	return tMessage;
            }
			
            default:
              return null;
		}	
	}
}
		