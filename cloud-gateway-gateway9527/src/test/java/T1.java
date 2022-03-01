import java.time.ZonedDateTime;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/1 11:11
 */
public class T1 {
    public static void main(String[] args) {

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now); //2022-03-01T11:12:29.724+08:00[Asia/Shanghai]
    }
}
