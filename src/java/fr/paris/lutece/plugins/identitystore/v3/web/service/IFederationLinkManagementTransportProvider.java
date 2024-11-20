package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.IdentityRequestValidator;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

public interface IFederationLinkManagementTransportProvider
{


    default void checkCommonHeaders( final String clientCode, final RequestAuthor author ) throws IdentityStoreException
    {
        IdentityRequestValidator.instance( ).checkAuthor( author );
        IdentityRequestValidator.instance( ).checkClientCode( clientCode );
    }

    ResponseDto getFederationLinkList(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto createFederationLink(FederationLinkDto federationLink, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;

    ResponseDto deleteFederationLink( String guid, String IdentityProvider, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException;
}
