package fr.paris.lutece.plugins.identitystore.v3.web.rs.service.transportrest;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListPasswordHistortyResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider.IPasswordHistoryManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.Map;

public class PasswordHistoryManagementRest extends AbstractTransportRest implements IPasswordHistoryManagementTransportProvider
{

    private String _strPasswordIdentityManagementEndPoint;
    private String _strOpenAmApi;
    /**
     * Simple Constructor
     */
    protected PasswordHistoryManagementRest( )
    {
        super(new HttpAccessTransport( ));
    }

    public PasswordHistoryManagementRest( final IHttpTransportProvider httpTransport, String api )
    {
        super( httpTransport );

        _strPasswordIdentityManagementEndPoint = httpTransport.getApiEndPointUrl( );
        _strOpenAmApi= api;
    }

    @Override
    public SearchListPasswordHistortyResult getListPasswordHistory(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strPasswordIdentityManagementEndPoint + _strOpenAmApi + Constants.PASSWORD_HISTORY_PATH ;
        return _httpTransport.doGet( url, mapParams, mapHeadersRequest, SearchListPasswordHistortyResult.class, _mapper );
    }
}
