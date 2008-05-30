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

import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.mule.providers.AbstractConnector;
import org.mule.umo.endpoint.UMOImmutableEndpoint;
import org.mule.umo.lifecycle.InitialisationException;

import org.mule.providers.rmiejb3.RmiConnector;

/**
 * <code>Ejb3Connector</code> TODO document
 */
public class Ejb3Connector extends RmiConnector
{
    public static final int EJB_SERVICECLASS_INVOCATION_FAILED = 2;

    public String getProtocol()
    {
        return "ejb3";
    }
    
    public Remote getRemoteObject(UMOImmutableEndpoint endpoint) throws RemoteException, UnknownHostException
    {
        //EJBObject remoteObj;
        Remote remote;
        try
        {
            /*
            Object ref = getRemoteRef(endpoint);
            Method method = ClassUtils.getMethod(ref.getClass(), "create", null);
            remoteObj = (EJBObject)method.invoke(ref, ClassUtils.NO_ARGS);
             */
            Object obj = getRemoteRef(endpoint);
            //nota: el objeto devuelto no implementa java.rmi.Remote ni ná de eso
            //implementa la interfaz remota de la EJB y punto.
            //uséase, aquí habría que devolver un Object, y no un Remote
            //=> hay que adaptar también el RMIConnector, o pasar de el e implementar
            //el EJB3Connector a pelo
            remote = (Remote) obj;
            
        }
        catch (Exception e)
        {
            throw new RemoteException("Remote EJBObject lookup failed for '" + endpoint.getEndpointURI(), e);
        }
        return remote;
    }
}
