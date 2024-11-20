package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.IdentityOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
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

    public ResponseDto getIdentityList(String strLastName, String strFirstName, String strDesc, String strSort, String strRange,
                                       RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getIdentityList( strLastName, strFirstName, strDesc, strSort, strRange, author, clientCode );
    }

    public ResponseDto createIdentity(IdentityOpenAMDto identity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.createIdentity( identity, author, clientCode );
    }

    public ResponseDto modifyIdentity(IdentityOpenAMDto modifyIdentity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.modifyIdentity(modifyIdentity, author, clientCode);
    }

    public ResponseDto deleteIdentity(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.deleteIdentity(strGuid, author, clientCode);
    }

}
