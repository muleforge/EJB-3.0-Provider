/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the MuleSource MPL
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.rmiejb3;

import org.mule.impl.MuleMessage;
import org.mule.providers.AbstractMessageReceiver;
import org.mule.providers.ConnectException;
import org.mule.providers.rmi.i18n.RmiMessages;
import org.mule.umo.MessagingException;
import org.mule.umo.UMOComponent;
import org.mule.umo.UMOException;
import org.mule.umo.endpoint.UMOEndpoint;
import org.mule.umo.endpoint.UMOEndpointURI;
import org.mule.umo.lifecycle.InitialisationException;
import org.mule.umo.provider.UMOConnector;
import org.mule.umo.provider.UMOMessageAdapter;
import org.mule.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;

import javax.naming.Context;

/**
 * TODO
 */

public class RmiCallbackMessageReceiver extends AbstractMessageReceiver
{
    /**
     * The property name for the service object implementing the callback interface
     * RmiAble This should be set on the inbound endpoint
     */
    public static final String PROPERTY_SERVICE_CLASS_NAME = "serviceClassName";

    protected RmiConnector connector;

    protected RmiAble remoteObject = null;

    private Context jndi = null;

    private String bindName = null;

    public RmiCallbackMessageReceiver(UMOConnector connector, UMOComponent component, UMOEndpoint endpoint)
        throws InitialisationException
    {
        super(connector, component, endpoint);
        this.connector = (RmiConnector)connector;
    }

    protected void doDispose()
    {
        // template method
    }

    /**
     * Actual initialization. Attempts to rebind service object to Jndi Tree for
     * discovery
     * 
     * @param endpoint
     * @throws org.mule.umo.lifecycle.InitialisationException
     */
    private void initialize(UMOEndpoint endpoint) throws InitialisationException
    {
        logger.debug("Initializing with endpoint " + endpoint);

        String rmiPolicyPath = connector.getSecurityPolicy();

        System.setProperty("java.security.policy", rmiPolicyPath);

        UMOEndpointURI endpointUri = endpoint.getEndpointURI();

        int port = endpointUri.getPort();

        if (port < 1)
        {
            port = RmiConnector.DEFAULT_RMI_REGISTRY_PORT;
        }

        try
        {
            InetAddress inetAddress = InetAddress.getByName(endpointUri.getHost());

            bindName = endpointUri.getPath();

            remoteObject = getRmiObject();

            Method theMethod = remoteObject.getClass().getMethod("setReceiver",
                new Class[]{RmiCallbackMessageReceiver.class});
            theMethod.invoke(remoteObject, new Object[]{this});

            jndi = connector.getJndiContext(inetAddress.getHostAddress() + ":" + port);

            jndi.rebind(bindName, remoteObject);
        }
        catch (Exception e)
        {
            throw new InitialisationException(e, this);
        }

        logger.debug("Initialized successfully");
    }

    /**
     * Initializes endpoint
     * 
     * @throws org.mule.providers.ConnectException
     */
    protected void doConnect() throws ConnectException
    {
        try
        {
            // Do not reinit if RMI is already bound to JNDI!!!
            // TODO Test how things work under heavy load!!!
            // Do we need threadlocals or so!?!?

            // TODO [aperepel] consider AtomicBooleans here
            // for 'initialised/initialising' status, etc.
            if (null == remoteObject)
            {
                initialize(getEndpoint());
            }
        }
        catch (Exception e)
        {
            throw new ConnectException(e, this);
        }
    }

    /**
     * Unbinds Rmi class from registry
     */
    protected void doDisconnect()
    {
        logger.debug("Disconnecting...");

        try
        {
            jndi.unbind(bindName);
        }
        catch (Exception e)
        {
            logger.error(e);
        }

        logger.debug("Disconnected successfully.");
    }

    protected void doStart() throws UMOException
    {
        // nothing to do
    }

    protected void doStop() throws UMOException
    {
        // nothing to do
    }

    /**
     * Gets RmiAble objetc for registry to add in.
     * 
     * @return java.rmi.Remote and RmiAble implementing class
     * @throws org.mule.umo.lifecycle.InitialisationException
     */
    private RmiAble getRmiObject() throws InitialisationException
    {
        String className = (String)endpoint.getProperty(PROPERTY_SERVICE_CLASS_NAME);

        if (null == className)
        {
            throw new InitialisationException(RmiMessages.messageReceiverNeedsRmiAble(), this);
        }

        RmiAble remote = null;

        try
        {
            remote = (RmiAble)ClassUtils.instanciateClass(className, new Object[] { }, this.getClass());
        }
        catch (Exception e)
        {
            throw new InitialisationException(RmiMessages.serviceClassInvocationFailed(), e);
        }

        return (remote);
    }

    /**
     * Routes message forward
     * 
     * @param message
     * @return
     * @throws org.mule.umo.MessagingException
     * @throws org.mule.umo.UMOException
     */
    public Object routeMessage(Object message) throws MessagingException, UMOException
    {
        UMOMessageAdapter adapter = connector.getMessageAdapter(message);

        return (routeMessage(new MuleMessage(adapter)));
    }
}
