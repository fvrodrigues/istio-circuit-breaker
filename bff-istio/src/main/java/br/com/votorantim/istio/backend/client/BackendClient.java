package br.com.votorantim.istio.backend.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Slf4j
@Component
public class BackendClient {

    private RestTemplate restClient;


    public String getMessage(String action) {
        BackendResource resource = null;
        try {
            resource = restClient.getForObject(String.format("/v1/sample/%s", action), BackendResource.class);
        }catch (Throwable t) {
            resource = getThrow(t);
        }

        return resource.getMessage();
    }

    private BackendResource defaultResponse() {
        var response = new BackendResource();
        response.setId(-1);
        response.setMessage("default");
        return response;
    }

    private BackendResource getThrow(Throwable t) {
        log.error(t.getMessage(), t);

        if(t instanceof HttpClientErrorException.NotFound){
            throw (HttpClientErrorException)t;
        }

        return defaultResponse();
    }

    @Getter
    @Setter
    static class BackendResource {
        private String message;
        private Integer id;
    }


}
