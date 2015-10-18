package exercises.hh;

import java.util.ArrayList;

public class periodCalcHelper {
	int predPeriod;
	long period;
	
	
	// �������� getter. �������� �������� ������� � �����������	
	public  periodCalcHelper getPeriodParameters(long divider, int scale){
		ArrayList<Long> pList = new ArrayList<Long>();
		ArrayList<Long> qList = new ArrayList<Long>();
		ArrayList<Long> pModuleList =new ArrayList<Long>();

		findDelimeters(divider, scale, pList, qList); // ���������� �������� �� ���������
		calcPeriod(scale, qList, pModuleList);
		calcPredPeriod(pList);
		
		return this;
	}

	
	
	/* ���������� ����� B �� ���������:
	 * B = (p1^a1)*...*(pn^an)*(q1^a1)*...*(qn^an)
	 * ��������� 2 ������� ����������: 
	 * pList - ��������� ��������, ������ ��������� ������� ��������� ������� �� ��� ���������
	 * qList -��������� ��������, ������ ��������� ������� ��������� �� ������� �� ��� ���������
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
			fillmultOrderArray(qList, pModuleList, scale); // ���������� ���������������� ������� ��� ���������� �������
		
		if (qList.size()!=0){
			period = massLcm(pModuleList);
		}
		
		else period=0;
	}
		
	
	//���������� ���������������� �������, ���������� �� ����������� ����� �� ������
	private static void fillmultOrderArray(ArrayList<Long> qList, ArrayList<Long> pModuleList, int scale){
		for (int i=0; i<qList.size(); i++){
			pModuleList.add(multiplicativeOrder(qList.get(i), scale));
		}
	}

	

	
	/* ���������� ������� ����� A/B � ������� ��������� scale */ 
	
	// ���������� ���������� ����� �� ������
	private static long multiplicativeOrder(long b, int scale){
		long i=1;
		while((Math.pow(scale, i)%b)!=1){
			i++;
		}
		
		return i;
	}
	

	// ���
	private static long gcd(long a,long b){
		return b == 0 ? a : gcd(b,a % b);		
	}
	
	//��� (���������� ����� �������)
	private static long lcm(long a,long b){
		return a / gcd(a,b) * b;
	}
	
	// ��� ��� �������� ���-�� ������ (ArrayList)
	public static long massLcm(ArrayList<Long> list){
		long res=1;
		for(int i=0; i<list.size();i++)
			res = lcm(res,list.get(i));

		return res;
	}
	
	
	
	// ���������� �����������

	private void calcPredPeriod(ArrayList<Long> pList) {
		if (pList.size()!=0){
			predPeriod=maxGrade(pList);
		}
		else {predPeriod=0;}
	}

	
	/* ������������ ���-�� ������������� ��������� 
	*(��� ��������� ������������ ������� ak � ���������� number = (p1^a1)*...*(pn^an)*(q1^a1)*...*(qn^an)
	* � ��������� �����������
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
