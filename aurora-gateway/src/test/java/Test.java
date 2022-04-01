
import com.alibaba.fastjson.JSON;
import lombok.Data;
import xyz.xcye.common.entity.result.ExceptionResultEntity;
import xyz.xcye.common.entity.result.R;
import xyz.xcye.common.enums.ResultStatusCode;

/**
 * @author qsyyke
 */

@Data
public class Test {
    public static void main(String[] args) {

        ExceptionResultEntity result = new ExceptionResultEntity();
        result.setMessage("sdfkjhdsf");
        result.setErrorUrl("/login");
        result.setData(null);

        R failure = R.failure(ResultStatusCode.PERMISSION_DENIED.getCode(), ResultStatusCode.PERMISSION_DENIED.getMessage());


        failure.setData(result);

        String s1 = JSON.toJSONString(failure);

        String s = JSON.toJSONString(result);

        System.out.println(s1);
        System.out.println(s);

    }
}
