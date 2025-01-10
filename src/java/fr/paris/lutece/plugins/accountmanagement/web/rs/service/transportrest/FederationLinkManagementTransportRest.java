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
package fr.paris.lutece.plugins.accountmanagement.web.rs.service.transportrest;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.ChangeFederationLinkResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.RequestClient;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.SearchListFederationLinkResponse;
import fr.paris.lutece.plugins.accountmanagement.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.accountmanagement.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.accountmanagement.web.service.transportprovider.IFederationLinkManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityAccountException;

import java.util.HashMap;
import java.util.Map;

public class FederationLinkManagementTransportRest extends AbstractTransportRest implements IFederationLinkManagementTransportProvider
{

    private String _strFederationLinkManagementEndPoint;
    private String _strFederationLinkManagementApi;

    /**
     * Simple Constructor
     */
    protected FederationLinkManagementTransportRest( )
    {
        super( new HttpAccessTransport( ) );
    }

    public FederationLinkManagementTransportRest( final IHttpTransportProvider httpTransport, String strFederationLinkManagementApi )
    {
        super( httpTransport );

        _strFederationLinkManagementEndPoint = httpTransport.getApiEndPointUrl( );
        _strFederationLinkManagementApi = strFederationLinkManagementApi;
    }

    @Override
    public SearchListFederationLinkResponse getFederationLinkList( String guid, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strFederationLinkManagementEndPoint + _strFederationLinkManagementApi + Constants.FEDERATION_LINK_PATH + "/" + guid;
        return _httpTransport.doGet( url, mapParams, mapHeaders, SearchListFederationLinkResponse.class, _mapper );
    }

    @Override
    public ChangeFederationLinkResponse createFederationLink( FederationLinkDto federationLink, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.PARAMETER_IDENTITY_ID, federationLink.getIdentityId( ) );
        this.addQueryParam( mapParams, Constants.PARAMETER_IDENTITY_NAME, federationLink.getIdentityName( ) );
        this.addQueryParam( mapParams, Constants.PARAMETER_IDENTITY_PROVIDER, federationLink.getIdentityProvider( ) );

        final String url = _strFederationLinkManagementEndPoint + _strFederationLinkManagementApi + Constants.FEDERATION_LINK_PATH + "/"
                + federationLink.getGuid( );
        return _httpTransport.doPost( url, mapParams, mapHeaders, ChangeFederationLinkResponse.class, _mapper );
    }

    @Override
    public ChangeFederationLinkResponse deleteFederationLink( String guid, String identityProvider, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strFederationLinkManagementEndPoint + _strFederationLinkManagementApi + Constants.FEDERATION_LINK_PATH + "/" + guid + "/"
                + identityProvider;
        return _httpTransport.doDeleteJSON( url, mapParams, mapHeaders, null, ChangeFederationLinkResponse.class, _mapper );
    }
}
