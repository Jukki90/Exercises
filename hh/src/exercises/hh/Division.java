package exercises.hh;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Division {
	
public static void main(String [] args){
	System.out.println("��������� ������� �����: "+ getDivisionResult("Division.txt"));

}


	
	public static String getDivisionResult(String filepath){
		String outcome="";
		ArrayList<String> alphaNum = new ArrayList<String>();
		
		try{
			// ���������� ������ �� �����
			File file = new File(filepath); 
			Scanner sc = new Scanner(file);
			String str = sc.nextLine();
			sc.close();
			String[] strArr = str.split(" ");

			// ����� A/B � ������� ��������� scale
			long A = Long.parseLong(strArr[0]);
			long B = Long.parseLong(strArr[1]);
			int scale = Integer.parseInt(strArr[2]);

			// �������� ������
			if (scale <2 || scale>36) {
					System.out.println("Please use a number between 2 and 36 as a radix");
			} 
			
			else {
				if (scale>10){
					// ������������� ������� ��� ����� ������ 9 � ��������������� ������� ���������
					setAlphabetNumberSystem(alphaNum, scale);
			}
			
			// ��������� ����� � ������� ������	
			long integerPart = (long)Math.abs(A/B);
			double fractioalPart = Math.abs((double) A / B) - integerPart; 
			
			// ���������� ������ ��� ���������� ������� � ����������� �����
			periodCalcHelper period = new periodCalcHelper();
			period.getPeriodParameters(Math.abs(B), scale);
			
			if (((double)A/B)<0) outcome="-"; // ������������ �����
				outcome+=convertIntegerPart(integerPart,scale, alphaNum)+"."+convertFractionalPart(fractioalPart, scale, alphaNum, period.predPeriod, period.period);

			}
		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return   outcome;
	}



	//-- ��������������� ������ --

	
	// ����������� ����� ����� � ������ ������� ���������

	private static String convertIntegerPart(long dec, int scale, ArrayList<String> alphaNum){
		String res="";
		String predRes="";
		int num=0;
		
		while (dec>0){
			num=(int)dec % scale;
		if (num>9){
			predRes+=alphaNum.get(num-10);
		}
		else{
			predRes+=num;
		}
		dec = dec / scale;
		}
		
		// convert to reverse order
		int length = predRes.length();
		for(int i=0; i<length; i++){
			res+=predRes.charAt(length-i-1);
		}

		if (res.length()==0) res="0";
			return res;
		
	}
		
		


	// ����������� ������� ����� � ������ ������� ���������
	private static String convertFractionalPart(double dec, int scale, ArrayList<String> alphaNum, long predPeriod, long period){
		String res="";
		int num=0;
		
		for (int i=0; i<predPeriod; i++){
			dec=scale*dec;
			num =(int)dec;
			if (num>9){
				res+=alphaNum.get(num-10);
			}
			else{
				res+=num;
			}
			dec = dec-(int)(dec);
		}
		
		if (period!=0){
			res+="(";
			dec=scale*dec;
			num =(int)dec;
			if (num>9){
				res+=alphaNum.get(num-10);
			}
			else{
				res+=num;
			}
			dec = dec-(int)(dec);
			
			
			res+=")";
		}
			return res;
	}


	
	
	// ���������� ������� ���������� ������� ��� ������ ��������� 10+
	private static void setAlphabetNumberSystem(ArrayList<String> alphaNum, int scale) {
		int N= scale-10;
		int ii=0;
		for (char ch = 'A';ii < N; ch++, ii++) {
			String s = new String();
			s +=ch;
			alphaNum.add(s);
		}
	}

}




