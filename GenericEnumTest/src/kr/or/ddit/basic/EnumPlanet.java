package kr.or.ddit.basic;

public class EnumPlanet {
	public enum Planet{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);

		private int i;
		
		Planet(int data) {
			i = data;
		}
		public int getI() {
			return i;
		}
	}
	public static void main(String[] args) {
		Planet[] enumArr = Planet.values();
		for(int i = 0; i<enumArr.length; i++) {
			System.out.println(enumArr[i].name() + "의 겉넓이:" + 4 * Math.PI * enumArr[i].getI()*enumArr[i].getI());
		}
	}
}
