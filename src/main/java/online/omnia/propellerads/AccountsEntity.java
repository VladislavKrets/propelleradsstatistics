package online.omnia.propellerads;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lollipop on 12.10.2017.
 */
@Entity
@Table(name = "accounts")
public class AccountsEntity {
    @Id
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "type")
    private String type;
    @Column(name = "api_key")
    private String apiKey;
    @Column(name = "api_url")
    private String apiUrl;
    public int getAccountId() {
        return accountId;
    }

    public String getType() {
        return type;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String toString() {
        return "AccountsEntity{" +
                "accountId=" + accountId +
                ", type='" + type + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                '}';
    }
}
