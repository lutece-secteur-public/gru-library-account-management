package fr.paris.lutece.plugins.identitystore.v3.web.rs.service.transportrest;


import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.IdentityOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListIdentityResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider.IIdentityManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.Map;

public class IdentityManagementTransportRest extends AbstractTransportRest implements IIdentityManagementTransportProvider
{

    private String _strIdentityManagementEndPoint;
    private String _strOpenAmApi;
    /**
     * Simple Constructor
     */
    protected IdentityManagementTransportRest( )
    {
        super(new HttpAccessTransport( ));
    }

    public IdentityManagementTransportRest( final IHttpTransportProvider httpTransport, String api )
    {
        super( httpTransport );

        _strIdentityManagementEndPoint = httpTransport.getApiEndPointUrl( );
        _strOpenAmApi= api;
    }

    @Override
    public SearchListIdentityResult getIdentityList(String lastName, String firstName, String desc, String sort, String range,
                                                    RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = new HashMap<>( );
        mapParams.put( Constants.PARAMETER_LAST_NAME, lastName );
        mapParams.put( Constants.PARAMETER_FIRST_NAME, firstName );
        mapParams.put( Constants.PARAMETER_DESC, desc );
        mapParams.put( Constants.PARAMETER_SORT, sort );
        mapParams.put( Constants.PARAMETER_RANGE, range );

        final String url = _strIdentityManagementEndPoint + _strOpenAmApi + Constants.IDENTITIES_PATH ;
        return _httpTransport.doGet( url, mapParams, mapHeadersRequest, SearchListIdentityResult.class, _mapper );
    }

    @Override
    public void createIdentity(IdentityOpenAMDto identity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = getIdentityMapParameters(identity, identity.getUid() );

        final String url = _strIdentityManagementEndPoint + _strOpenAmApi + Constants.IDENTITIES_PATH;
        _httpTransport.doPostJSON( url, mapParams, mapHeadersRequest, null, null, _mapper );
    }

    @Override
    public void modifyIdentity(IdentityOpenAMDto modifyIdentity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = getIdentityMapParameters(modifyIdentity, null );

        final String url = _strIdentityManagementEndPoint + _strOpenAmApi + Constants.IDENTITIES_PATH + "/" + modifyIdentity.getUid();
        _httpTransport.doPutJSON( url, mapParams, mapHeadersRequest, null, null, _mapper );
    }

    @Override
    public void deleteIdentity(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strIdentityManagementEndPoint + _strOpenAmApi + Constants.IDENTITIES_PATH + "/" + guid ;
        _httpTransport.doDeleteJSON( url, mapParams, mapHeadersRequest, null, null, _mapper );
    }

    @Override
    public IdentityOpenAMDto getIdentity(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        final String url = _strIdentityManagementEndPoint + _strOpenAmApi + Constants.IDENTITIES_PATH + "/" + guid ;
        return _httpTransport.doGet( url, mapParams, mapHeadersRequest, IdentityOpenAMDto.class, _mapper );
    }

    private Map<String, String> getIdentityMapParameters( IdentityOpenAMDto identity, String strGuid )
    {
        final Map<String, String> mapParams = new HashMap<>( );

        if ( strGuid != null )
        {
            mapParams.put( Constants.PARAMETER_UID, strGuid );
        }

        if ( identity.getLastname(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_LAST_NAME, identity.getLastname(  ) );
        }

        if ( identity.getFirstname(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_FIRST_NAME, identity.getFirstname(  ) );
        }

        if ( identity.getCity(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_CITY, identity.getCity(  ) );
        }

        if ( identity.getCityOfBirth(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_CITY_OF_BIRTH, identity.getCityOfBirth(  ) );
        }

        if ( identity.getCivility(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_CIVILITY, identity.getCivility(  ) );
        }

        if ( identity.getPostalCode(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_POSTAL_CODE, identity.getPostalCode(  ) );
        }

        if ( identity.getStreet(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_STREET, identity.getStreet(  ) );
        }

        if ( identity.getTelephoneNumber(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_TELEPHONE_NUMBER, identity.getTelephoneNumber(  ) );
        }

        if ( identity.getStayConnected(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_STAY_CONNECTED, identity.getStayConnected(  ) );
        }

        if ( identity.getBirthday(  ) != null )
        {
            mapParams.put( Constants.PARAMETER_BIRHDAY, identity.getBirthday(  ) );
        }

        return mapParams;
    }

}
