package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.IdentityOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListIdentityResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider.IIdentityManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

public class IdentityManagementService
{

    public IIdentityManagementTransportProvider _transportProvider;


    public IdentityManagementService()
    {
        super( );
    }

    public IdentityManagementService(IIdentityManagementTransportProvider _transportProvider)
    {
        super();
        this._transportProvider = _transportProvider;
    }

    public void set_transportProvider(IIdentityManagementTransportProvider _transportProvider)
    {
        this._transportProvider = _transportProvider;
    }

    public SearchListIdentityResult getIdentityList(String lastName, String strFirstName, String desc, String sort, String range,
                                                    RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getIdentityList( lastName, strFirstName, desc, sort, range, author, clientCode );
    }

    public void createIdentity(IdentityOpenAMDto identity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.createIdentity( identity, author, clientCode );
    }

    public void modifyIdentity(IdentityOpenAMDto modifyIdentity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.modifyIdentity(modifyIdentity, author, clientCode);
    }

    public void deleteIdentity(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this._transportProvider.deleteIdentity(strGuid, author, clientCode);
    }

    public IdentityOpenAMDto getIdentity(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getIdentity(guid, author, clientCode);
    }

}
