package api.model;

import lombok.Data;

@Data
public class ExchangeTokenResponse {
    private DataModel data;
    private String message;
    private int status;
}
