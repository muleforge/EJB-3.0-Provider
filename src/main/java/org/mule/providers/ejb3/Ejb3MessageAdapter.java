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

import org.mule.providers.rmi.RmiMessageAdapter;

/**
 * <code>Ejb3MessageAdapter</code> TODO document
 */
public class Ejb3MessageAdapter extends RmiMessageAdapter
{
	private static final long serialVersionUID = 3932390270676915501L;
	
    public Ejb3MessageAdapter(Object message) throws MessageTypeNotSupportedException
    {
        super(message);
    }
}