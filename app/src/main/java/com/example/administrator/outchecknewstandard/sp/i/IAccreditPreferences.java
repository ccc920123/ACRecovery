package com.example.administrator.outchecknewstandard.sp.i;

public interface IAccreditPreferences {
    String getAccreditNumber(String str);

    Boolean getIsAccredit(String str);

    String getLock(String str);

    String getYxrq(String str);

    void saveAccreditNumber(String str, String str2);

    void saveIsAccredit(String str, Boolean bool);

    void saveLock(String str, String str2);

    void saveYxrq(String str, String str2);
}
