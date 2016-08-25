import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Created by Michael on 8/16/2016.
 */
public class HttpClientTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
        /*String data = "[user_name:1]";
        String private_key = "32959E34807D5C60DEBA3A2C9D1F196DABB7FC60EC66613C28BF0456388B4013";
        Hmac_sha256Util hw = new Hmac_sha256Util();
        String sign = hw.signature(data, private_key);
        StringEntity entity = new StringEntity(data, "UTF-8");
        byte[] enk = DecryptUtils.hex("YzQyNzIzYjEtMGQ4Ni00ZGExLTlhMTktNDAxYzhmZTY3NjNj");
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        String sys_type = "kcm";
        String user_id = "1";
        byte[] encoded_type = DecryptUtils.encryptMode(enk,sys_type.getBytes());
        byte[] encoded_id = DecryptUtils.encryptMode(enk, user_id.getBytes());
        String systemType = Base64.encode(encoded_type);
        String userId = Base64.encode(encoded_id);
        String resultJson = HttpUtil.postInvoke("http://127.0.0.1:8080/login/ipwhitelist/"+ systemType + "/" + userId, sign, entity, "utf-8");
        System.out.println(resultJson);*/

        /*String app_id = UUID.randomUUID().toString();
        System.out.println(app_id);   // c42723b1-0d86-4da1-9a19-401c8fe6763c

        String app_sercet = Base64.encode(app_id.getBytes());
        System.out.println(app_sercet);  // YzQyNzIzYjEtMGQ4Ni00ZGExLTlhMTktNDAxYzhmZTY3NjNj*/

        /*CloseableHttpClient httpClient = getHttpClient();

        try {
            String sys_type = "kcm";
            String user_id = "1";

            String queryString = "sys_type=" + sys_type + "&user_id=" + user_id;
            String queryParams = DecryptUtils.encode(queryString, "YzQyNzIzYjEtMGQ4Ni00ZGExLTlhMTktNDAxYzhmZTY3NjNj");

            // GET请求
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/login/ipwhitelist?" + queryParams);
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
        }*/


        String uriAPI = "http://127.0.0.1:8080/login/test";//Post方式没有参数在这里
        String result = "";
        HttpPost httpRequst = new HttpPost(uriAPI);//创建HttpPost对象

        String data = "{limit: 10, offset: 0, search: '', agentuserQuery: '', order: 'asc', ibId: '-1', ibId_all: '-1'}";
        StringEntity entity = new StringEntity(data, "UTF-8");

        try {
            httpRequst.setEntity(entity);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }


    }

    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }

    private static void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

}
