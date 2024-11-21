package fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.IdentityRequestValidator;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.IdentityOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListIdentityResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

public interface IIdentityManagementTransportProvider
{


    default void checkCommonHeaders( final String clientCode, final RequestAuthor author ) throws IdentityStoreException
    {
        IdentityRequestValidator.instance( ).checkAuthor( author );
        IdentityRequestValidator.instance( ).checkClientCode( clientCode );
    }

    SearchListIdentityResult getIdentityList(String lastName, String FirstName, String desc, String sort, String range,
                                             RequestAuthor author, String clientCode ) throws OpenamIdentityException, IdentityStoreException;

    void createIdentity(IdentityOpenAMDto identity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    void modifyIdentity( IdentityOpenAMDto modifyIdentity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    void deleteIdentity(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    IdentityOpenAMDto getIdentity(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;
}
