/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.ejb3;

import org.mule.providers.AbstractMessageAdapter;
import org.mule.umo.MessagingException;
import org.mule.umo.provider.MessageTypeNotSupportedException;

import org.mule.providers.rmiejb3.RmiMessageAdapter;

/**
 * <code>Ejb3MessageAdapter</code> TODO document
 */
public class Ejb3MessageAdapter extends RmiMessageAdapter
{

	
	
    /* For general guidelines on writing transports see
       http://mule.mulesource.org/display/MULE/Writing+Transports */

    /* IMPLEMENTATION NOTE: The MessageAdapter is used to wrap an underlying
       message. It should store a copy of the underlying message as an
       instance variable. */
    
    public Ejb3MessageAdapter(Object message) throws MessageTypeNotSupportedException
    {
        super(message);
    }

    public String getPayloadAsString(String encoding) throws Exception
    {
        // TODO return the string representation of the wrapped message
        throw new UnsupportedOperationException("getPayloadAsString");
    }

    public byte[] getPayloadAsBytes() throws Exception
    {
        // TODO return the byte[] representation of the wrapped message
        throw new UnsupportedOperationException("getPayloadAsBytes");
    }

    public Object getPayload()
    {
        // TODO return the actual wrapped message
        throw new UnsupportedOperationException("getPayload");
    }

}
