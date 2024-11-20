package fr.paris.lutece.plugins.identitystore.v3.web.rs.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IPasswordHistoryManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.Map;

public class PasswordHistoryManagementRest extends AbstractTransportRest implements IPasswordHistoryManagementTransportProvider
{

    private String _strPasswordIdentityManagementEndPoint;
    /**
     * Simple Constructor
     */
    protected PasswordHistoryManagementRest( )
    {
        super(new HttpAccessTransport( ));
    }

    public PasswordHistoryManagementRest( final IHttpTransportProvider httpTransport )
    {
        super( httpTransport );

        _strPasswordIdentityManagementEndPoint = httpTransport.getApiEndPointUrl( );
    }

    @Override
    public ResponseDto getListPasswordHistory(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        //TODO ajouter les constantes de l'API OpenAM PasswordIdentity
        final String url = _strPasswordIdentityManagementEndPoint + "" ;
        return _httpTransport.doGet( url, mapParams, mapHeadersRequest, ResponseDto.class, _mapper );
    }
}
