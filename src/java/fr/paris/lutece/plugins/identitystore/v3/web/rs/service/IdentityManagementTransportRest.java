package fr.paris.lutece.plugins.identitystore.v3.web.rs.service;


import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.IdentityOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IIdentityManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.Map;

public class IdentityManagementTransportRest extends AbstractTransportRest implements IIdentityManagementTransportProvider
{

    private String _strIdentityManagementEndPoint;
    /**
     * Simple Constructor
     */
    protected IdentityManagementTransportRest( )
    {
        super(new HttpAccessTransport( ));
    }

    public IdentityManagementTransportRest( final IHttpTransportProvider httpTransport )
    {
        super( httpTransport );

        _strIdentityManagementEndPoint = httpTransport.getApiEndPointUrl( );
    }

    @Override
    public ResponseDto getIdentityList(String lastName, String firstName, String desc, String sort, String range,
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

        //TODO ajouter les constantes de l'API OpenAM Identity
        final String url = _strIdentityManagementEndPoint + "" ;
        return _httpTransport.doGet( url, mapParams, mapHeadersRequest, ResponseDto.class, _mapper );
    }

    @Override
    public ResponseDto createIdentity(IdentityOpenAMDto identity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = getIdentityMapParameters(identity, identity.getUid() );

        //TODO ajouter les constantes de l'API OpenAM Identity
        final String url = _strIdentityManagementEndPoint + "";
        return _httpTransport.doPostJSON( url, mapParams, mapHeadersRequest, null, ResponseDto.class, _mapper );
    }

    @Override
    public ResponseDto modifyIdentity(IdentityOpenAMDto modifyIdentity, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = getIdentityMapParameters(modifyIdentity, null );

        //TODO ajouter les constantes de l'API OpenAM Identity
        final String url = _strIdentityManagementEndPoint + "" + modifyIdentity.getUid();
        return _httpTransport.doPutJSON( url, mapParams, mapHeadersRequest, null, ResponseDto.class, _mapper );
    }

    @Override
    public ResponseDto deleteIdentity(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders( clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( Constants.PARAM_CLIENT_CODE, clientCode );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_NAME, author.getName( ) );
        mapHeadersRequest.put( Constants.PARAM_AUTHOR_TYPE, author.getType( ).name( ) );

        final Map<String, String> mapParams = new HashMap<>( );

        //TODO ajouter les constantes de l'API OpenAM Identity
        final String url = _strIdentityManagementEndPoint + "" + guid ;
        return _httpTransport.doDeleteJSON( url, mapParams, mapHeadersRequest, null, ResponseDto.class, _mapper );
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
