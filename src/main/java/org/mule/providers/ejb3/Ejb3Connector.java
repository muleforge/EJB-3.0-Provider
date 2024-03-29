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

import org.mule.providers.AbstractConnector;
import org.mule.umo.lifecycle.InitialisationException;

/**
 * <code>Ejb3Connector</code> TODO document
 */
public class Ejb3Connector extends AbstractConnector
{

    /* For general guidelines on writing transports see
       http://mule.mulesource.org/display/MULE/Writing+Transports */

    /* IMPLEMENTATION NOTE: All configuaration for the transport should be set
       on the Connector object, this is the object that gets configured in
       MuleXml */

    public void doInitialise() throws InitialisationException
    {
        // Optional; does not need to be implemented. Delete if not required

        /* IMPLEMENTATION NOTE: Is called once all bean properties have been
           set on the connector and can be used to validate and initialise the
           connectors state. */
    }

    public void doConnect() throws Exception
    {
        // Optional; does not need to be implemented. Delete if not required

        /* IMPLEMENTATION NOTE: Makes a connection to the underlying
           resource. When connections are managed at the receiver/dispatcher
           level, this method may do nothing */
    }

    public void doDisconnect() throws Exception
    {
        // Optional; does not need to be implemented. Delete if not required

        /* IMPLEMENTATION NOTE: Disconnects any connections made in the
           connect method If the connect method did not do anything then this
           method shouldn't do anything either. */
    }

    public void doStart() throws org.mule.umo.UMOException
    {
    	// Optional; does not need to be implemented. Delete if not required

        /* IMPLEMENTATION NOTE: If there is a single server instance or
           connection associated with the connector i.e.  AxisServer or a Jms
           Connection or Jdbc Connection, this method should put the resource
           in a started state here. */
    }

    public void doStop() throws org.mule.umo.UMOException
    {
    	// Optional; does not need to be implemented. Delete if not required

        /* IMPLEMENTATION NOTE: Should put any associated resources into a
           stopped state. Mule will automatically call the stop() method. */
    }

    public void doDispose()
    {
    	// Optional; does not need to be implemented. Delete if not required

        /* IMPLEMENTATION NOTE: Should clean up any open resources associated
           with the connector. */
    }

    public String getProtocol()
    {
        return "ejb3";
    }

}
