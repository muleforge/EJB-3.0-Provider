/*
 * \$Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.ejb3;

import org.mule.impl.endpoint.MuleEndpointURI;
import org.mule.tck.AbstractMuleTestCase;
import org.mule.umo.endpoint.UMOEndpointURI;
import org.mule.config.MuleProperties;

public class Ejb3EndpointTestCase extends AbstractMuleTestCase
{

    /* For general guidelines on writing transports see
       http://mule.mulesource.org/display/MULE/Writing+Transports */

    public void testValidEndpointURI() throws Exception
    {
        UMOEndpointURI url = new MuleEndpointURI("ejb3://localhost:1099");
        assertEquals("ejb3", url.getScheme());
        assertEquals("ejb3://localhost:1099", url.getAddress());
        assertNull(url.getEndpointName());
        assertEquals(1099, url.getPort());
        assertEquals("localhost", url.getHost());
        assertEquals(0, url.getParams().size());
    }
    
    public void testQueryParams1() throws Exception
    {
    	UMOEndpointURI url = new MuleEndpointURI("ejb3://localhost:1099/service?method=testMethod");
    	assertEquals("ejb3", url.getScheme());
    	//assertEquals("ejb3://localhost:1099", url.getAddress());
    	assertEquals("/service", url.getPath());
    	assertEquals(1099, url.getPort());
    	assertNull(url.getEndpointName());
    	assertEquals("ejb3://localhost:1099/service?method=testMethod", url.toString());
    	assertEquals("testMethod", url.getParams().getProperty(MuleProperties.MULE_METHOD_PROPERTY));
    	
    }

}
