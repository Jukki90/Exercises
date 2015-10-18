package exercises.hh;

import java.util.ArrayList;

public class periodCalcHelper {
	int predPeriod;
	long period;
	
	
	// Основной getter. Получаем значения периода и предпериода	
	public  periodCalcHelper getPeriodParameters(long divider, int scale){
		ArrayList<Long> pList = new ArrayList<Long>();
		ArrayList<Long> qList = new ArrayList<Long>();
		ArrayList<Long> pModuleList =new ArrayList<Long>();

		findDelimeters(divider, scale, pList, qList); // разложение делителя на множители
		calcPeriod(scale, qList, pModuleList);
		calcPredPeriod(pList);
		
		return this;
	}

	
	
	/* Разложение числа B на множители:
	 * B = (p1^a1)*...*(pn^an)*(q1^a1)*...*(qn^an)
	 * Заполняем 2 массива значениями: 
	 * pList - множители делителя, причем основание системы счисления делится на эти множители
	 * qList -множители делителя, причем основание системы счисления не делится на эти множители
	 */
	private static void findDelimeters(long B, int scale, ArrayList<Long> pList, ArrayList<Long> qList){
		for (long i = 2; i <= B; i++) {
			for (long j = 2; j <= i; j++) {
				if (B % j == 0) {
					if (scale%j==0){
						pList.add(j);
					}
					else{
						qList.add(j);
					}
					B = B / j;
				}
 
			}
		}
	}

	
	



	private void calcPeriod(int scale, ArrayList<Long> qList,
			ArrayList<Long> pModuleList) {
			fillmultOrderArray(qList, pModuleList, scale); // заполнение вспомогательного массива для вычисления периода
		
		if (qList.size()!=0){
			period = massLcm(pModuleList);
		}
		
		else period=0;
	}
		
	
	//Заполнение вспомогательного массива, состоящего из показателей чисел по модулю
	private static void fillmultOrderArray(ArrayList<Long> qList, ArrayList<Long> pModuleList, int scale){
		for (int i=0; i<qList.size(); i++){
			pModuleList.add(multiplicativeOrder(qList.get(i), scale));
		}
	}

	

	
	/* Вычисление периода дроби A/B в системе счисления scale */ 
	
	// Вычисление показателя числа по модулю
	private static long multiplicativeOrder(long b, int scale){
		long i=1;
		while((Math.pow(scale, i)%b)!=1){
			i++;
		}
		
		return i;
	}
	

	// НОД
	private static long gcd(long a,long b){
		return b == 0 ? a : gcd(b,a % b);		
	}
	
	//НОК (наименьшее общее кратное)
	private static long lcm(long a,long b){
		return a / gcd(a,b) * b;
	}
	
	// НОК для большого кол-ва данных (ArrayList)
	public static long massLcm(ArrayList<Long> list){
		long res=1;
		for(int i=0; i<list.size();i++)
			res = lcm(res,list.get(i));

		return res;
	}
	
	
	
	// Вычисление предпериода
	private void calcPredPeriod(ArrayList<Long> pList) {
		if (pList.size()!=0){
			predPeriod=maxGrade(pList);
		}
		else {predPeriod=0;}
	}

	
	/* Максимальное кол-во повторяющихся элементов 
	*(для получения максимальной степени ak в разложении number = (p1^a1)*...*(pn^an)*(q1^a1)*...*(qn^an)
	* и получения предпериода
	*/
	public  static int maxGrade(ArrayList<Long> list){
		int count=0;
		int maxCount=0;
		for(int i=0; i<list.size();i++){
			count=0;
			for(int j=0; j<list.size(); j++){
				
				if (list.get(i)==list.get(j)){
					count++;
				}
			}
			if (maxCount<count) {
				maxCount = count;
				}
		}
		return maxCount;
	}
	

}
