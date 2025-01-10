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

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.RequestClient;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.SearchListPasswordHistortyResponse;
import fr.paris.lutece.plugins.accountmanagement.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.accountmanagement.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.accountmanagement.web.service.transportprovider.IPasswordHistoryManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityAccountException;

import java.util.HashMap;
import java.util.Map;

public class PasswordHistoryManagementRest extends AbstractTransportRest implements IPasswordHistoryManagementTransportProvider
{

    private String _strPasswordIdentityManagementEndPoint;
    private String _strPasswordHistoryManagementApi;

    /**
     * Simple Constructor
     */
    protected PasswordHistoryManagementRest( )
    {
        super( new HttpAccessTransport( ) );
    }

    public PasswordHistoryManagementRest( final IHttpTransportProvider httpTransport, String strPasswordHistoryManagementApi )
    {
        super( httpTransport );

        _strPasswordIdentityManagementEndPoint = httpTransport.getApiEndPointUrl( );
        _strPasswordHistoryManagementApi = strPasswordHistoryManagementApi;
    }

    @Override
    public SearchListPasswordHistortyResponse getListPasswordHistory( String guid, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strPasswordIdentityManagementEndPoint + _strPasswordHistoryManagementApi + Constants.PASSWORD_HISTORY_PATH + "/" + guid;
        return _httpTransport.doGet( url, mapParams, mapHeaders, SearchListPasswordHistortyResponse.class, _mapper );
    }
}
