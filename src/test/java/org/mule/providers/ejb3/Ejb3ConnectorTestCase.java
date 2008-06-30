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

import org.mule.tck.providers.AbstractConnectorTestCase;
import org.mule.umo.UMOComponent;
import org.mule.umo.provider.UMOConnector;

public class Ejb3ConnectorTestCase extends AbstractConnectorTestCase
{

    /* For general guidelines on writing transports see
       http://mule.mulesource.org/display/MULE/Writing+Transports */

    public UMOConnector getConnector() 
    {
        Ejb3Connector c = new Ejb3Connector();
        c.setName("EJB3Connector");
        return c;
    }

    public String getTestEndpointURI()
    {
    	// TODO Return a valid endpoint for you transport here
    	return "ejb3://localhost:1099";
    }

    public Object getValidMessage() throws Exception
    {
        // TODO Return an valid message for your transport
    	return "hello".getBytes();
    }


    public void testProperties() throws Exception
    {
        // TODO test setting and retrieving any custom properties on the
        // Connector as necessary
    	Ejb3Connector c = (Ejb3Connector) getConnector();
    	
    	String securityPolicy = "rmi.policy";
    	c.setSecurityPolicy(securityPolicy);
    	assertNotNull(c.getSecurityPolicy());
    	
    }

	@Override
	public UMOConnector createConnector() throws Exception 
	{
        Ejb3Connector c = new Ejb3Connector();
        c.setName("EJB3Connector");
        c.setSecurityManager(null);
        c.initialise();
        return c;
	}

}
