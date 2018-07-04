package online.omnia.propellerads;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.sql.Date;
import java.util.List;

/**
 * Created by lollipop on 14.10.2017.
 */
public class Main {
    public static void main(String[] args) {
        List<AccountsEntity> accountsEntities = MySQLDaoImpl.getInstance().getAccountsEntities("PropellerAds");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new StatisticsJsonListDeserializer());
        Gson gson = builder.create();
        String answer;
        List<JsonStatistic> jsonStatistics;
        for (AccountsEntity accountsEntity : accountsEntities) {
            answer = HttpMethodUtils.getMethod("http://report.propellerads.com/?action=getStats&key="
                    + accountsEntity.getApiKey() +
                    "&date_range=yesterday&params[stat_columns][show,click,convers,convrate,cpm,ctr,profit]=show,click,convers,convrate,cpm,ctr,profit");
            System.out.println(answer);
            try {
                jsonStatistics = gson.fromJson(answer, List.class);
                answer = HttpMethodUtils.getMethod("http://report.propellerads.com/?action=getAffiliates&key=" + accountsEntity.getApiKey());
            } catch (Exception e) {
                Utils.writeLog(e.toString());
                continue;
            }
            System.out.println(answer);
            SourceStatisticsEntity sourceStatisticsEntity;
            for (JsonStatistic jsonStatistic : jsonStatistics) {
                if (Main.isJsonStatisticsEmpty(jsonStatistic)) continue;
                sourceStatisticsEntity = new SourceStatisticsEntity();
                sourceStatisticsEntity.setAccount_id(accountsEntity.getAccountId());
                sourceStatisticsEntity.setClicks(Integer.parseInt(jsonStatistic.getShow()));
                sourceStatisticsEntity.setConversions(Integer.parseInt(jsonStatistic.getConvers()));
                sourceStatisticsEntity.setCpc(Double.parseDouble(jsonStatistic.getClick()));
                sourceStatisticsEntity.setCpm(Double.parseDouble(jsonStatistic.getCpm()));
                sourceStatisticsEntity.setCtr(Double.parseDouble(jsonStatistic.getCtr()));
                sourceStatisticsEntity.setSpent(((int)(Double.parseDouble(jsonStatistic.getProfit()) * 100)) / 100.0);
                sourceStatisticsEntity.setReceiver("API");
                sourceStatisticsEntity.setDate(new Date(System.currentTimeMillis() - 86400000L));
                System.out.println(sourceStatisticsEntity);
                MySQLDaoImpl.getInstance().addSourceStatistics(sourceStatisticsEntity);
            }
        }
        MySQLDaoImpl.getSessionFactory().close();
    }
    private static boolean isJsonStatisticsEmpty(JsonStatistic jsonStatistic) {
        return jsonStatistic.getClick() == null && jsonStatistic.getConvers() == null
                && jsonStatistic.getConvrate() == null && jsonStatistic.getCpm() == null
                && jsonStatistic.getCtr() == null && jsonStatistic.getProfit() == null
                && jsonStatistic.getShow() == null;
    }

}
