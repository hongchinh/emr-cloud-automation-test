package api.model;

import lombok.Data;

@Data
public class DataModel {
    private String token;
    private int userId;
    private String loginId;
    private String name;
    private String kanaName;
    private int kaId;
    private boolean isDoctor;
    private int managerKbn;
    private String sName;
    private int hpId;
    private String refreshToken;
    private String refreshTokenExpiryTime;
}
