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

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.AccountDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.ChangeAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.GetAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.RequestClient;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.SearchListAccountResponse;
import fr.paris.lutece.plugins.accountmanagement.web.service.transportprovider.IAccountManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityAccountException;

import java.util.List;

public class AccountManagementService
{
    public IAccountManagementTransportProvider _transportProvider;

    public AccountManagementService( )
    {
        super( );
    }

    public AccountManagementService( IAccountManagementTransportProvider transportProvider )
    {
        super( );
        _transportProvider = transportProvider;
    }

    public void set_transportProvider( IAccountManagementTransportProvider _transportProvider )
    {
        this._transportProvider = _transportProvider;
    }

    public SearchListAccountResponse getAccountList( String strSearchMail, String strSearchStatus, String strDesc, String strSort, String strRange,
            RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.getAccountList( strSearchMail, strSearchStatus, strDesc, strSort, strRange, client );
    }

    public ChangeAccountResponse createAccount( String strMail, String strUserPassword, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.createAccount( strMail, strUserPassword, client );
    }

    public ChangeAccountResponse modifyAccount( AccountDto account, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.modifyAccount( account, client );
    }

    public ChangeAccountResponse modifyPassword( String strGuid, String strCurrentUserPassword, String strUserPassword, RequestClient client )
            throws IdentityAccountException
    {
        return this._transportProvider.modifyPassword( strGuid, strCurrentUserPassword, strUserPassword, client );
    }

    public ChangeAccountResponse deleteAccount( String strGuid, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.deleteAccount( strGuid, client );
    }

    public GetAccountResponse getAccount( String strGuid, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.getAccount( strGuid, client );
    }

    public GetAccountResponse getKeyclockAccount( String strGuid, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.getKeyclockAccount( strGuid, client );
    }

    public SearchListAccountResponse getAccountListByTechnicalInformations( String strLastAuthDate, boolean bBeforeLastAuthDate, String strModifDate,
            boolean bBeforeModifDate, boolean bValidatedAccount, String strTechnicalOperation, boolean bNotOperator, RequestClient client )
            throws IdentityAccountException
    {
        return this._transportProvider.getAccountListByTechnicalInformations( strLastAuthDate, bValidatedAccount, strModifDate, bBeforeModifDate,
                bValidatedAccount, strTechnicalOperation, bNotOperator, client );
    }

    public void updateTechnicalOperation( List<String> listGuid, String strTechnicalOperationValue, RequestClient client ) throws IdentityAccountException
    {
        this._transportProvider.updateTechnicalOperation( listGuid, strTechnicalOperationValue, client );
    }

    public SearchListAccountResponse getAccountListByGuid( List<String> listGuid, RequestClient client ) throws IdentityAccountException
    {
        return this._transportProvider.getAccountListByGuid( listGuid, client );
    }
}
