package basic;

import java.math.BigInteger;

public class Karatsuba {


    public void multiplication(BigInteger numero1,BigInteger numero2,int digitos){


        System.out.println(numero1.toString());
    }


    public  static  void main(String [] args){
        Karatsuba k = new Karatsuba();

        BigInteger numero1 = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger numero2 = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        //k.multiplication();
    }

}
