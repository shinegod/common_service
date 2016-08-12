package ExcelTest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import com.fx.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiTest {

    /*excel column formate:column_#_width, excel中每一列的名称*/
    public static final String[] USER_RECORES_COLUMNS = new String[]{
            "User Name_#_3000",
            "Address_#_7000"
    };
    /*the column will display on xls files. must the same as the entity fields.对应上面的字段.*/
    public static final String[] USER_RECORES_FIELDS = new String[]{
            "name","address"
    };

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        List<UserTest> users = new ArrayList<UserTest>();
        for(int i=0; i<10;i++){
            UserTest u = new UserTest("name: " + i, i, "address: " + i);
            users.add(u);
        }

        //实际项目中，这个list 是从数据库中得到的
        HSSFWorkbook workbook = new HSSFWorkbook();
        ExcelUtil<UserTest> UserTestSheet = new ExcelUtil<UserTest>();
        HSSFSheet sheet = UserTestSheet.creatAuditSheet(workbook, "UserTest sheet xls",
                users, USER_RECORES_COLUMNS, USER_RECORES_FIELDS);

       /* FileOutputStream fileOut = new FileOutputStream("d:/user_test.xls");
        workbook.write(fileOut);
        fileOut.close();*/

        ByteArrayInputStream inputStream = (ByteArrayInputStream) ExcelUtil.writeExcelToStream(workbook, sheet);

        FileOutputStream fileOut = new FileOutputStream("d:/user_test1.xls");
        int data=inputStream.read();
        while(data!=-1){
            fileOut.write(data);
            data=inputStream.read();
        }
        fileOut.close();


    }
}