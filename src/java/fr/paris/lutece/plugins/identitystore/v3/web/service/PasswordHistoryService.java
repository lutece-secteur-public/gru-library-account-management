package fr.paris.lutece.plugins.identitystore.v3.web.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

public class PasswordHistoryService
{
    public IPasswordHistoryManagementTransportProvider _transportProvider;

    public PasswordHistoryService()
    {
        super( );
    }

    public PasswordHistoryService(IPasswordHistoryManagementTransportProvider _transportProvider)
    {
        super();
        this._transportProvider = _transportProvider;
    }

    public void set_transportProvider(IPasswordHistoryManagementTransportProvider _transportProvider)
    {
        this._transportProvider = _transportProvider;
    }

    public ResponseDto getListPasswordHistory(String strGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        return this._transportProvider.getListPasswordHistory(strGuid, author, clientCode);
    }
}
