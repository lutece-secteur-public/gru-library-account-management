package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

public class FederationLinkManagementService
{
    public IFederationLinkManagementTransportProvider _transportProvider;

    public FederationLinkManagementService()
    {
        super( );
    }

    public FederationLinkManagementService(IFederationLinkManagementTransportProvider _transportProvider)
    {
        super();
        this._transportProvider = _transportProvider;
    }

    public void set_transportProvider(IFederationLinkManagementTransportProvider _transportProvider)
    {
        this._transportProvider = _transportProvider;
    }

    public ResponseDto getFederationLinkList(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getFederationLinkList( strGuid, author, clientCode);
    }

    public ResponseDto createFederationLink(FederationLinkDto federationLink, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.createFederationLink( federationLink, author, clientCode );
    }

    public ResponseDto deleteFederationLink(String strGuid, String strIdentityProvider, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.deleteFederationLink( strGuid, strIdentityProvider, author, clientCode );
    }
}
