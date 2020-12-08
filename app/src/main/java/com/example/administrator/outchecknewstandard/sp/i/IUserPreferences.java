package com.example.administrator.outchecknewstandard.sp.i;

public interface IUserPreferences {
    String getAPI(String str);

    String getIP(String str);

    String getJczmc(String str);

    String getJygwh(String str);

    String getPdaSbm(String str);

    String getPort(String str);

    String getStationNumber(String str);

    String getUserAccount(String str);

    String getUserName(String str);

    String getUserPwd(String str);

    String getVersionNumber(String str);

    void saveAPI(String str, String str2);

    void saveIP(String str, String str2);

    void saveJczmc(String str, String str2);

    void saveJjygwh(String str, String str2);

    void savePdaSbm(String str, String str2);

    void savePort(String str, String str2);

    void saveStationNumber(String str, String str2);

    void saveUserAccount(String str, String str2);

    void saveUserName(String str, String str2);

    void saveUserPwd(String str, String str2);

    void saveVersionNumber(String str, String str2);
}
