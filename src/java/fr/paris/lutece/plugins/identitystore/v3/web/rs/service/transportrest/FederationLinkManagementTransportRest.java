package fr.paris.lutece.plugins.identitystore.v3.web.rs.service.transportrest;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListFederationLinkResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider.IFederationLinkManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.Map;

public class FederationLinkManagementTransportRest extends AbstractTransportRest implements IFederationLinkManagementTransportProvider
{

    private String _strFederationLinkManagementEndPoint;
    private String _strOpenAmApi;

    /**
     * Simple Constructor
     */
    protected FederationLinkManagementTransportRest()
    {
        super(new HttpAccessTransport());
    }

    public FederationLinkManagementTransportRest(final IHttpTransportProvider httpTransport, String api)
    {
        super(httpTransport);

        _strFederationLinkManagementEndPoint = httpTransport.getApiEndPointUrl();
        _strOpenAmApi= api;
    }

    @Override
    public SearchListFederationLinkResponse getFederationLinkList(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();

        final String url = _strFederationLinkManagementEndPoint + _strOpenAmApi  + Constants.FEDERATION_LINK_PATH + "/" + guid;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, SearchListFederationLinkResponse.class, _mapper);
    }

    @Override
    public void createFederationLink(FederationLinkDto federationLink, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.PARAMETER_IDENTITY_ID, federationLink.getIdentityId());
        mapParams.put(Constants.PARAMETER_IDENTITY_NAME, federationLink.getIdentityName());
        mapParams.put(Constants.PARAMETER_IDENTITY_PROVIDER, federationLink.getIdentityProvider());

        final String url = _strFederationLinkManagementEndPoint + _strOpenAmApi  + Constants.FEDERATION_LINK_PATH + "/" + federationLink.getGuid();
        _httpTransport.doPostJSON(url, mapParams, mapHeadersRequest, null, null, _mapper);
    }

    @Override
    public void deleteFederationLink(String guid, String identityProvider, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();

        final String url = _strFederationLinkManagementEndPoint + _strOpenAmApi  + Constants.FEDERATION_LINK_PATH + "/" + guid + "/" + identityProvider;
        _httpTransport.doDeleteJSON(url, mapParams, mapHeadersRequest, null, null, _mapper);
    }
}
