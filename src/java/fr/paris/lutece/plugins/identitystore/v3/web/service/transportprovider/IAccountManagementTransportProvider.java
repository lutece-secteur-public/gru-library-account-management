package fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.IdentityRequestValidator;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.AccountOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.CreateAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListAccountResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.json.JsonSearchListAccountResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.List;

public interface IAccountManagementTransportProvider
{

    default void checkCommonHeaders( final String clientCode, final RequestAuthor author ) throws IdentityStoreException
    {
        IdentityRequestValidator.instance( ).checkAuthor( author );
        IdentityRequestValidator.instance( ).checkClientCode( clientCode );
    }

    SearchListAccountResult getAccountList(String searchMail, String searchStatus, String desc, String sort,
                                           String range, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    CreateAccountResponse createAccount(String mail, String userPassword, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    void modifyAccount( AccountOpenAMDto account, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    void modifyPassword( String guid, String currentUserPassword, String userPassword, RequestAuthor author,
                           String clientCode ) throws OpenamIdentityException, IdentityStoreException;

    void deleteAccount( String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    AccountOpenAMDto getAccount( String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    AccountOpenAMDto getKeyclockAccount( String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    JsonSearchListAccountResult getAccountListByTechnicalInformations(String lastAuthDate, boolean beforeLastAuthDate, String modifDate,
                                                                      boolean beforeModifDate, boolean validatedAccount, String technicalOperation,
                                                                      boolean notOperator, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException;

    void updateTechnicalOperation( List<String> listGuid, String technicalOperationValue, RequestAuthor author,
                                   String clientCode) throws OpenamIdentityException, IdentityStoreException;

    SearchListAccountResult getAccountListByGuid(List<String> listGuid, RequestAuthor author, String clientCode )
            throws OpenamIdentityException, IdentityStoreException;
}
