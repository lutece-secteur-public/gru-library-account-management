package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.IdentityRequestValidator;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.AccountOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.CreateAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.IdentityOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.List;

public interface IAccountManagementTransportProvider
{

    default void checkCommonHeaders( final String clientCode, final RequestAuthor author ) throws IdentityStoreException
    {
        IdentityRequestValidator.instance( ).checkAuthor( author );
        IdentityRequestValidator.instance( ).checkClientCode( clientCode );
    }

    ResponseDto getAccountList(String searchMail, String searchStatus, String desc, String sort,
                               String range, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    CreateAccountResponse createAccount(String mail, String userPassword, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto modifyAccount( AccountOpenAMDto account, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto modifyPassword( String guid, String currentUserPassword, String userPassword, RequestAuthor author,
                           String clientCode ) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto deleteAccount( String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto getAccount( String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto getKeyclockAccount( String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto getAccountListByTechnicalInformations( String lastAuthDate, boolean beforeLastAuthDate, String modifDate,
                                                  boolean beforeModifDate, boolean validatedAccount, String technicalOperation,
                                                  boolean notOperator, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException;

    ResponseDto updateTechnicalOperation( List<String> listGuid, String technicalOperationValue, RequestAuthor author,
                                   String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto getAccountListByGuid( List<String> listGuid, RequestAuthor author, String clientCode )
            throws OpenamIdentityException, IdentityStoreException;
}
