import com.fx.util.DecryptUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Michael on 9/6/2016.
 */
public class Test {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = getHttpClient();

        try {
            String sys_type = "kcm";
            String user_id = "1";

            String queryString = sys_type + "_" + user_id;
            String queryParams = DecryptUtils.encode(queryString, "YzQyNzIzYjEtMGQ4Ni00ZGExLTlhMTktNDAxYzhmZTY3NjNj");

            // GET请求
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/login/sessioncheck?" + queryParams);
            httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.setHeader("app_id", "c42723b1-0d86-4da1-9a19-401c8fe6763c");
            System.out.println("执行get请求:...."+httpGet.getURI());
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(httpGet);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("响应状态码:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }

    private static void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null){
            client.close();
        }
    }

}
