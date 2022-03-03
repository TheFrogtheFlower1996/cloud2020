import org.junit.Test;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/3 13:18
 */
public class addDigits {


    @Test
    public int addDigits1(int num) {
        String s = Integer.toString(num);
        String[] split = s.split("");
        int m=0;
        if (split.length != 1){
            for (int i = 0; i < split.length; i++) {
                m = m + Integer.valueOf(split[i]);
            }
            return addDigits1(m);
        }else{
            return m;
        }
    }


}
