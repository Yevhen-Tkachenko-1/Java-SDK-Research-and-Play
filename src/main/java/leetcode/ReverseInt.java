package leetcode;

public class ReverseInt {

    static final int MAX_ORDER = 10;
    public int reverse(int x) {
        if(x == Integer.MIN_VALUE){
            return 0;
        }
        boolean negative = x < 0;
        x = Math.abs(x);
        int res = 0;
        for(int i = 0; i<MAX_ORDER; i++){
            if(x < 10){
                res = safeSum(res, x);
                if(res<0){
                    return 0;
                }
                break;
            }
            res = safeSum(res, x%10);
            if(res<0){
                return 0;
            }
            if(x >= 10){
                res = increaseOrder(res);
                if(res<0){
                    return 0;
                }
                x/=10;
            }
        }
        return negative ? -res : res;
    }

    int safeSum(int a, int b) {
        long r = (long)a + b;
        if (r >>> 32 != 0) {
            return -1;
        }
        return (int)r;
    }
    int increaseOrder(int a) {
        long r = (long)a * 10;
        if (r >>> 32 != 0) {
            return -1;
        }
        return (int)r;
    }
}
