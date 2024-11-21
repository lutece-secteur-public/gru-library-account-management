package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.AccountOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.CreateAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListAccountResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.json.JsonSearchListAccountResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider.IAccountManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.List;

public class AccountManagementService
{
    public IAccountManagementTransportProvider _transportProvider;

    public AccountManagementService()
    {
        super( );
    }

    public AccountManagementService(IAccountManagementTransportProvider transportProvider )
    {
        super();
        _transportProvider = transportProvider;
    }

    public void set_transportProvider(IAccountManagementTransportProvider _transportProvider)
    {
        this._transportProvider = _transportProvider;
    }

    public SearchListAccountResult getAccountList(String strSearchMail, String strSearchStatus, String strDesc, String strSort, String strRange,
                                                  RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getAccountList(strSearchMail, strSearchStatus, strDesc, strSort, strRange, author, clientCode);
    }

    public CreateAccountResponse createAccount(String strMail, String strUserPassword, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.createAccount( strMail, strUserPassword, author, clientCode );
    }
    
    public void modifyAccount(AccountOpenAMDto account, RequestAuthor author,
                                     String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.modifyAccount(account, author, clientCode);
    }

    public void modifyPassword(String strGuid, String strCurrentUserPassword, String strUserPassword, RequestAuthor author,
                                 String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.modifyPassword(strGuid, strCurrentUserPassword, strUserPassword, author, clientCode);
    }
    
    public void deleteAccount(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.deleteAccount(strGuid, author, clientCode);
    }
    
    public AccountOpenAMDto getAccount(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getAccount( strGuid, author, clientCode );
    }
    
    public AccountOpenAMDto getKeyclockAccount(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getKeyclockAccount( strGuid, author, clientCode );
    }

    public JsonSearchListAccountResult getAccountListByTechnicalInformations(String strLastAuthDate, boolean bBeforeLastAuthDate, String strModifDate,
                                                                             boolean bBeforeModifDate, boolean bValidatedAccount, String strTechnicalOperation,
                                                                             boolean bNotOperator, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getAccountListByTechnicalInformations(strLastAuthDate, bValidatedAccount,
                strModifDate, bBeforeModifDate, bValidatedAccount, strTechnicalOperation, bNotOperator,
                author, clientCode);
    }

    public void updateTechnicalOperation(List<String> listGuid, String strTechnicalOperationValue, RequestAuthor author,
                                                String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.updateTechnicalOperation(listGuid, strTechnicalOperationValue, author, clientCode);
    }

    public SearchListAccountResult getAccountListByGuid(List<String> listGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getAccountListByGuid(listGuid, author, clientCode);
    }
}