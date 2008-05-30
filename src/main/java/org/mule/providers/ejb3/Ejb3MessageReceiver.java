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

import org.mule.providers.ConnectException;
import org.mule.providers.AbstractMessageReceiver;
import org.mule.providers.ejb3.Ejb3Connector;
import org.mule.umo.UMOComponent;
import org.mule.umo.endpoint.UMOEndpoint;
import org.mule.umo.lifecycle.InitialisationException;
import org.mule.umo.provider.UMOConnector;

import org.mule.providers.rmiejb3.RmiMessageReceiver;

/**
 * <code>Ejb3MessageReceiver</code> TODO document
 */
public class Ejb3MessageReceiver extends RmiMessageReceiver 
{

	/* For general guidelines on writing transports see
	   http://mule.mulesource.org/display/MULE/Writing+Transports */

	public Ejb3MessageReceiver(UMOConnector connector, UMOComponent component,
			UMOEndpoint endpoint, long frequency)
			throws InitialisationException 
	{
		super(connector, component, endpoint, frequency);

		this.connector = (Ejb3Connector) connector;
	}
}

