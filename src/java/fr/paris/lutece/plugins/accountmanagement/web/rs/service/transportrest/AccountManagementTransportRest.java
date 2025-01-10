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

import fr.paris.lutece.plugins.accountmanagement.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.accountmanagement.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.accountmanagement.web.service.transportprovider.IAccountManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.AccountDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.ChangeAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.GetAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.RequestClient;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.SearchListAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityAccountException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManagementTransportRest extends AbstractTransportRest implements IAccountManagementTransportProvider
{

    private String _strAccountManagementEndPoint;
    private String _strAccountManagementApi;

    /**
     * Simple Constructor
     */
    protected AccountManagementTransportRest( )
    {
        super( new HttpAccessTransport( ) );
    }

    public AccountManagementTransportRest( final IHttpTransportProvider httpTransport, String strAccountManagementApi )
    {
        super( httpTransport );

        _strAccountManagementEndPoint = httpTransport.getApiEndPointUrl( );
        _strAccountManagementApi = strAccountManagementApi;
    }

    @Override
    public SearchListAccountResponse getAccountList( String searchMail, String searchStatus, String desc, String sort, String range, RequestClient client )
            throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.PARAM_MAIL, searchMail, true );
        this.addQueryParam( mapParams, Constants.PARAM_INET_USER_STATUS, searchStatus );
        this.addQueryParam( mapParams, Constants.PARAMETER_DESC, desc );
        this.addQueryParam( mapParams, Constants.PARAMETER_SORT, sort );
        this.addQueryParam( mapParams, Constants.PARAMETER_RANGE, range );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH;
        return _httpTransport.doGet( url, mapParams, mapHeaders, SearchListAccountResponse.class, _mapper );
    }

    @Override
    public ChangeAccountResponse createAccount( String mail, String userPassword, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.PARAM_MAIL, mail );
        this.addQueryParam( mapParams, Constants.PARAM_USER_PASSWORD, userPassword );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH;
        return _httpTransport.doPost( url, mapParams, mapHeaders, ChangeAccountResponse.class, _mapper );
    }

    @Override
    public ChangeAccountResponse modifyAccount( AccountDto account, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.PARAM_MAIL, account.getLogin( ) );
        this.addQueryParam( mapParams, Constants.PARAM_INET_USER_STATUS, account.getAccountStatus( ) );
        this.addQueryParam( mapParams, Constants.PARAM_VALIDATED_ACCOUNT, account.getValidated( ) );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + "/" + account.getUid( );
        return _httpTransport.doPut( url, mapParams, mapHeaders, ChangeAccountResponse.class, _mapper );
    }

    @Override
    public ChangeAccountResponse modifyPassword( String guid, String currentUserPassword, String userPassword, RequestClient client )
            throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.HEADER_CURRENT_USER_PASSWORD, currentUserPassword );
        this.addQueryParam( mapParams, Constants.PARAM_USER_PASSWORD, userPassword );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + "/" + guid;
        return _httpTransport.doPut( url, mapParams, mapHeaders, ChangeAccountResponse.class, _mapper );
    }

    @Override
    public ChangeAccountResponse deleteAccount( String guid, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + "/" + guid;
        return _httpTransport.doDeleteJSON( url, mapParams, mapHeaders, null, ChangeAccountResponse.class, _mapper );
    }

    @Override
    public GetAccountResponse getAccount( String guid, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + "/" + guid;
        return _httpTransport.doGet( url, mapParams, mapHeaders, GetAccountResponse.class, _mapper );
    }

    @Override
    public GetAccountResponse getKeyclockAccount( String guid, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.PARAMETER_SEARCH_KEYCLOCK, "true" );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + "/" + guid;
        return _httpTransport.doGet( url, mapParams, mapHeaders, GetAccountResponse.class, _mapper );
    }

    @Override
    public SearchListAccountResponse getAccountListByTechnicalInformations( String lastAuthDate, boolean beforeLastAuthDate, String modifDate,
            boolean beforeModifDate, boolean validatedAccount, String technicalOperation, boolean notOperator, RequestClient client )
            throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        this.addQueryParam( mapParams, Constants.PARAMETER_LAST_AUTH_DATE, lastAuthDate );
        this.addQueryParam( mapParams, Constants.PARAMETER_MODIF_DATE, modifDate );
        this.addQueryParam( mapParams, Constants.PARAMETER_TECHNICAL_OPERATION, technicalOperation );
        this.addQueryParam( mapParams, Constants.PARAMETER_BEFORE_LAST_AUTH_DATE, String.valueOf( beforeLastAuthDate ) );
        this.addQueryParam( mapParams, Constants.PARAMETER_BEFORE_MODIF_DATE, String.valueOf( beforeModifDate ) );
        this.addQueryParam( mapParams, Constants.PARAM_VALIDATED_ACCOUNT, String.valueOf( validatedAccount ) );
        this.addQueryParam( mapParams, Constants.PARAMETER_NOT_OPERATOR, String.valueOf( notOperator ) );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + Constants.PATH_ACCOUNT_TECHNICAL_INFORMATIONS;
        return _httpTransport.doGet( url, mapParams, mapHeaders, SearchListAccountResponse.class, _mapper );
    }

    @Override
    public void updateTechnicalOperation( List<String> listGuid, String technicalOperationValue, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        final String guidList = StringUtils.join( listGuid, Constants.COMA_SEPARATOR );
        this.addQueryParam( mapParams, Constants.PARAMETER_GUID_LIST, guidList );
        this.addQueryParam( mapParams, Constants.PARAMETER_TECHNICAL_OPERATION, technicalOperationValue );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + Constants.PATH_ACCOUNT_TECHNICAL_INFORMATIONS;
        _httpTransport.doGet( url, mapParams, mapHeaders, null, _mapper );
    }

    @Override
    public SearchListAccountResponse getAccountListByGuid( List<String> listGuid, RequestClient client ) throws IdentityAccountException
    {
        final Map<String, String> mapHeaders = new HashMap<>( );
        mapHeaders.put( Constants.PARAM_CLIENT_ID, client.getClientId( ) );
        mapHeaders.put( Constants.PARAM_CLIENT_SECRET, client.getClientSecret( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        final String guidList = StringUtils.join( listGuid, Constants.COMA_SEPARATOR );
        this.addQueryParam( mapParams, Constants.PARAMETER_GUID_LIST, guidList );

        final String url = _strAccountManagementEndPoint + _strAccountManagementApi + Constants.ACCOUNTS_PATH + Constants.PATH_ACCOUNT_BY_GUID_LIST;
        return _httpTransport.doGet( url, mapParams, mapHeaders, SearchListAccountResponse.class, _mapper );
    }
}
