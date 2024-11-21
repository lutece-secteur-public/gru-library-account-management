package fr.paris.lutece.plugins.identitystore.v3.web.rs.service.transportrest;

import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.AccountOpenAMDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.CreateAccountResponse;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.SearchListAccountResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.account.openam.json.JsonSearchListAccountResult;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.RequestAuthor;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.dto.common.ResponseDto;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.service.HttpAccessTransport;
import fr.paris.lutece.plugins.identitystore.v3.web.rs.util.Constants;
import fr.paris.lutece.plugins.identitystore.v3.web.service.transportprovider.IAccountManagementTransportProvider;
import fr.paris.lutece.plugins.identitystore.v3.web.service.IHttpTransportProvider;
import fr.paris.lutece.plugins.identitystore.web.exception.IdentityStoreException;
import fr.paris.lutece.plugins.identitystore.web.exception.OpenamIdentityException;
import org.apache.commons.lang3.StringUtils;
import fr.paris.lutece.portal.service.html.EncodingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManagementTransportRest extends AbstractTransportRest implements IAccountManagementTransportProvider
{

    private String _strAccountManagementEndPoint;
    private String _strOpenAmApi;

    /**
     * Simple Constructor
     */
    protected AccountManagementTransportRest()
    {
        super(new HttpAccessTransport());
    }

    public AccountManagementTransportRest(final IHttpTransportProvider httpTransport, String api)
    {
        super(httpTransport);

        _strAccountManagementEndPoint = httpTransport.getApiEndPointUrl();
        _strOpenAmApi= api;
    }

    @Override
    public SearchListAccountResult getAccountList(String searchMail, String searchStatus, String desc, String sort, String range, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.PARAM_MAIL, EncodingService.encodeUrl(searchMail));
        mapParams.put(Constants.PARAM_INET_USER_STATUS, searchStatus);
        mapParams.put(Constants.PARAMETER_DESC, desc);
        mapParams.put(Constants.PARAMETER_SORT, sort);
        mapParams.put(Constants.PARAMETER_RANGE, range);

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, SearchListAccountResult.class, _mapper);
    }

    @Override
    public CreateAccountResponse createAccount(String mail, String userPassword, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.PARAM_MAIL, mail);
        mapParams.put(Constants.PARAM_USER_PASSWORD, userPassword);

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH;
        return _httpTransport.doPostJSON(url, mapParams, mapHeadersRequest, null, CreateAccountResponse.class, _mapper);
    }

    @Override
    public void modifyAccount(AccountOpenAMDto account, RequestAuthor author, String clientCode)
            throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.PARAM_MAIL, account.getLogin());
        mapParams.put(Constants.PARAM_INET_USER_STATUS, account.getAccountStatus());
        mapParams.put(Constants.PARAM_VALIDATED_ACCOUNT, account.getValidated());

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + account.getUid();
        _httpTransport.doPutJSON(url, mapParams, mapHeadersRequest, null, null, _mapper);
    }

    @Override
    public void modifyPassword(String guid, String currentUserPassword, String userPassword, RequestAuthor author,
                                      String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.HEADER_CURRENT_USER_PASSWORD, currentUserPassword);
        mapParams.put(Constants.PARAM_USER_PASSWORD, userPassword);

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + guid;
        _httpTransport.doPutJSON(url, mapParams, mapHeadersRequest, null, null, _mapper);
    }

    @Override
    public void deleteAccount(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + guid;
        _httpTransport.doDeleteJSON(url, mapParams, mapHeadersRequest, null, null, _mapper);
    }

    @Override
    public AccountOpenAMDto getAccount(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + guid;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, AccountOpenAMDto.class, _mapper);
    }

    @Override
    public AccountOpenAMDto getKeyclockAccount(String guid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.PARAMETER_SEARCH_KEYCLOCK, "true");

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + guid;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, AccountOpenAMDto.class, _mapper);
    }


    @Override
    public JsonSearchListAccountResult getAccountListByTechnicalInformations(String lastAuthDate, boolean beforeLastAuthDate, String modifDate,
                                                                             boolean beforeModifDate, boolean validatedAccount, String technicalOperation,
                                                                             boolean notOperator, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        mapParams.put(Constants.PARAMETER_LAST_AUTH_DATE, lastAuthDate);
        mapParams.put(Constants.PARAMETER_MODIF_DATE, modifDate);
        mapParams.put(Constants.PARAMETER_TECHNICAL_OPERATION, technicalOperation);
        mapParams.put(Constants.PARAMETER_BEFORE_LAST_AUTH_DATE, String.valueOf(beforeLastAuthDate));
        mapParams.put(Constants.PARAMETER_BEFORE_MODIF_DATE, String.valueOf(beforeModifDate));
        mapParams.put(Constants.PARAM_VALIDATED_ACCOUNT, String.valueOf(validatedAccount));
        mapParams.put(Constants.PARAMETER_NOT_OPERATOR, String.valueOf(notOperator));

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + Constants.PATH_ACCOUNT_TECHNICAL_INFORMATIONS;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, JsonSearchListAccountResult.class, _mapper);
    }

    @Override
    public void updateTechnicalOperation(List<String> listGuid, String technicalOperationValue,
                                                RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        String guid = StringUtils.join(listGuid, Constants.CSV_SEPARATOR);
        mapParams.put(Constants.PARAMETER_GUID, guid);
        mapParams.put(Constants.PARAMETER_TECHNICAL_OPERATION, technicalOperationValue);

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + Constants.PATH_ACCOUNT_TECHNICAL_INFORMATIONS;
        _httpTransport.doGet(url, mapParams, mapHeadersRequest, null, _mapper);
    }

    @Override
    public SearchListAccountResult getAccountListByGuid(List<String> listGuid, RequestAuthor author, String clientCode) throws OpenamIdentityException, IdentityStoreException
    {
        this.checkCommonHeaders(clientCode, author);

        final Map<String, String> mapHeadersRequest = new HashMap<>();
        mapHeadersRequest.put(Constants.PARAM_CLIENT_CODE, clientCode);
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_NAME, author.getName());
        mapHeadersRequest.put(Constants.PARAM_AUTHOR_TYPE, author.getType().name());

        final Map<String, String> mapParams = new HashMap<>();
        String guid = StringUtils.join(listGuid, Constants.CSV_SEPARATOR);
        mapParams.put(Constants.PARAMETER_GUID, guid);

        final String url = _strAccountManagementEndPoint + _strOpenAmApi  + Constants.ACCOUNTS_PATH + Constants.PATH_ACCOUNT_BY_GUID_LIST;
        return _httpTransport.doGet(url, mapParams, mapHeadersRequest, SearchListAccountResult.class, _mapper);
    }
}