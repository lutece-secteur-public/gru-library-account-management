package fr.paris.lutece.plugins.identitystore.v3.web.rs.service;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.FederationLinkDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IFederationLinkManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;

import java.util.HashMap;
import java.util.Map;

public class FederationLinkManagementTransportRest extends AbstractTransportRest implements IFederationLinkManagementTransportProvider
{

    private String _strFederationLinkManagementEndPoint;

    /**
     * Simple Constructor
     */
    protected FederationLinkManagementTransportRest()
    {
        super(new HttpAccessTransport());
    }

    public FederationLinkManagementTransportRest(final IHttpTransportProvider httpTransport)
    {
        super(httpTransport);

        _strFederationLinkManagementEndPoint = httpTransport.getApiEndPointUrl();
    }

    @Override
    public ResponseDto getFederationLinkList(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();

        //TODO ajouter les constantes de l'API OpenAM FederationLink
        final String url = _strFederationLinkManagementEndPoint + "" + guid;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, ResponseDto.class, _mapper);
    }

    @Override
    public ResponseDto createFederationLink(FederationLinkDto federationLink, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
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

        //TODO ajouter les constantes de l'API OpenAM FederationLink
        final String url = _strFederationLinkManagementEndPoint + "" + federationLink.getGuid();
        return _httpTransport.doPostJSON(url, mapParams, mapHeadersRequest, null, ResponseDto.class, _mapper);
    }

    @Override
    public ResponseDto deleteFederationLink(String guid, String identityProvider, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();

        //TODO ajouter les constantes de l'API OpenAM FederationLink
        final String url = _strFederationLinkManagementEndPoint + "" + guid + "/" + identityProvider;
        return _httpTransport.doDeleteJSON(url, mapParams, mapHeadersRequest, null, ResponseDto.class, _mapper);
    }
}
