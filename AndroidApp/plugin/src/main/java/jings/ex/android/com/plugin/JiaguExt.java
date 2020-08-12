package jings.ex.android.com.plugin;

/**
 * Created by jings on 2020/7/29.
 */

public class JiaguExt {

    private String userName;
    private String password;
    private String keystorePath;
    private String keystorePass;
    private String keystoreKeyAlias;
    private String keystoreKeyAliasPwd;
    private String jiagutoolPath;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    public void setKeystorePass(String keystorePass) {
        this.keystorePass = keystorePass;
    }

    public String getKeystoreKeyAlias() {
        return keystoreKeyAlias;
    }

    public void setKeystoreKeyAlias(String keystoreKeyAlias) {
        this.keystoreKeyAlias = keystoreKeyAlias;
    }

    public String getKeystoreKeyAliasPwd() {
        return keystoreKeyAliasPwd;
    }

    public void setKeystoreKeyAliasPwd(String keystoreKeyAliasPwd) {
        this.keystoreKeyAliasPwd = keystoreKeyAliasPwd;
    }

    public String getJiagutoolPath() {
        return jiagutoolPath;
    }

    public void setJiagutoolPath(String jiagutoolPath) {
        this.jiagutoolPath = jiagutoolPath;
    }
}
