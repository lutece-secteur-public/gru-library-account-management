/*
 * Copyright (c) 2002-2025, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.accountmanagement.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.ChangeFederationLinkResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.RequestClient;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.SearchListFederationLinkResponse;
import fr.paris.lutece.plugins.accountmanagement.web.service.transportprovider.IFederationLinkManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityAccountException;

public class FederationLinkManagementService
{
    public IFederationLinkManagementTransportProvider _transportProvider;

    public FederationLinkManagementService( )
    {
        super( );
    }

    public FederationLinkManagementService( IFederationLinkManagementTransportProvider _transportProvider )
    {
        super( );
        this._transportProvider = _transportProvider;
    }

    public void set_transportProvider( IFederationLinkManagementTransportProvider _transportProvider )
    {
        this._transportProvider = _transportProvider;
    }

    public SearchListFederationLinkResponse getFederationLinkList( String strGuid, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.getFederationLinkList( strGuid, client );
    }

    public ChangeFederationLinkResponse createFederationLink( FederationLinkDto federationLink, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.createFederationLink( federationLink, client );
    }

    public ChangeFederationLinkResponse deleteFederationLink( String strGuid, String strIdentityProvider, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.deleteFederationLink( strGuid, strIdentityProvider, client );
    }
}
